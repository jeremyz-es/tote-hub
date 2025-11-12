package com.edgestackers.pgi.services.feed.notification;

import com.edgestackers.pgi.common.datamodel.message.PgiScratchingMessage;
import com.edgestackers.pgi.common.datamodel.metadata.PgiRaceMetadata;
import com.edgestackers.pgi.services.common.datamodel.RunnerScratchedNotificationMessage;
import com.edgestackers.pgi.services.feed.datamodel.PgiRaceInformationKey;
import com.edgestackers.pgi.services.feed.metadata.PgiRaceMetadataCache;
import com.edgestackers.pgi.services.feed.publisher.PgiMessageNatsPublisher;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;
import com.edgestackers.tote.hub.core.datamodel.message.scratching.ToteScratchingMessage;
import jakarta.annotation.Nullable;

import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;

public class RunnerScratchedNotificationMessageProcessor {
    private final PgiRaceMetadataCache pgiRaceMetadataCache;
    private final PgiMessageNatsPublisher natsPublisher;
    private final boolean enablePublishing;

    public RunnerScratchedNotificationMessageProcessor(PgiRaceMetadataCache pgiRaceMetadataCache,
                                                       PgiMessageNatsPublisher natsPublisher,
                                                       boolean enablePublishing)
    {
        this.pgiRaceMetadataCache = pgiRaceMetadataCache;
        this.natsPublisher = natsPublisher;
        this.enablePublishing = enablePublishing;
    }

    public void process(RunnerScratchedNotificationMessage runnerScratchedNotificationMessage) {
        if (!enablePublishing) return;
        String programName = runnerScratchedNotificationMessage.programName();
        int race = runnerScratchedNotificationMessage.race();
        int runnerNumber = runnerScratchedNotificationMessage.runnerNumber();
        PgiScratchingMessage pgiScratchingMessage = new PgiScratchingMessage(
                true,
                generateEpochNanosTimestamp(),
                programName,
                race,
                runnerNumber
        );
        natsPublisher.publish(pgiScratchingMessage);
        PgiRaceInformationKey pgiRaceInformationKey = new PgiRaceInformationKey(programName, race);
        @Nullable PgiRaceMetadata pgiRaceMetadata = pgiRaceMetadataCache.getBy(pgiRaceInformationKey);
        if (pgiRaceMetadata == null) return;
        createAndPublishToteScratchingFor(pgiRaceMetadata, runnerNumber);
    }

    private void createAndPublishToteScratchingFor(PgiRaceMetadata pgiRaceMetadata, int runnerNumber) {
        ToteScratchingMessage toteScratchingMessage = new ToteScratchingMessage(
                true,
                generateEpochNanosTimestamp(),
                pgiRaceMetadata.esRaceId(),
                runnerNumber,
                ToteProvider.PGI.name()
        );
        natsPublisher.publish(toteScratchingMessage);
    }
}
