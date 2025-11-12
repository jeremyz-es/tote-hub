package com.edgestackers.pgi.services.feed.cycle;

import com.edgestackers.pgi.common.datamodel.cycle.PgiCycleUpdate;
import com.edgestackers.pgi.common.datamodel.cycle.PgiCycleUpdateKey;
import com.edgestackers.pgi.common.datamodel.metadata.PgiRaceMetadata;
import com.edgestackers.pgi.services.feed.cycle.tote.ToteMarketCycleUpdateProcessor;
import com.edgestackers.pgi.services.feed.cycle.tote.ToteWinMarketCycleUpdateProcessor;
import com.edgestackers.pgi.services.feed.datamodel.PgiRaceInformationKey;
import com.edgestackers.pgi.services.feed.metadata.PgiRaceMetadataCache;
import com.edgestackers.pgi.services.feed.publisher.PgiMessageNatsPublisher;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.message.ToteMarketCycleUpdate;
import com.edgestackers.tote.hub.core.datamodel.message.ToteWinMarketCycleUpdate;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

import static com.edgestackers.pgi.common.util.PgiBetTypeConverter.convertToToteBetType;
import static com.edgestackers.pgi.services.feed.cycle.tote.ToteMarketCycleUpdateConverter.createToteCycleMarketUpdate;
import static com.edgestackers.pgi.services.feed.cycle.tote.ToteWinMarketCycleUpdateConverter.createToteWinMarketCycleUpdate;

public class PgiCycleUpdateProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiCycleUpdateProcessor.class);
    private final PgiCycleUpdateCache pgiCycleUpdateCache;
    private final PgiRaceMetadataCache pgiRaceMetadataCache;
    private final PgiMessageNatsPublisher natsPublisher;
    private final ToteMarketCycleUpdateProcessor toteMarketCycleUpdateProcessor;
    private final ToteWinMarketCycleUpdateProcessor toteWinMarketCycleUpdateProcessor;
    private final Set<PgiCycleUpdateKey> loggedFailures = new HashSet<>();

    public PgiCycleUpdateProcessor(PgiCycleUpdateCache pgiCycleUpdateCache,
                                   PgiRaceMetadataCache pgiRaceMetadataCache,
                                   PgiMessageNatsPublisher natsPublisher,
                                   ToteMarketCycleUpdateProcessor toteMarketCycleUpdateProcessor,
                                   ToteWinMarketCycleUpdateProcessor toteWinMarketCycleUpdateProcessor) {
        this.pgiCycleUpdateCache = pgiCycleUpdateCache;
        this.pgiRaceMetadataCache = pgiRaceMetadataCache;
        this.natsPublisher = natsPublisher;
        this.toteMarketCycleUpdateProcessor = toteMarketCycleUpdateProcessor;
        this.toteWinMarketCycleUpdateProcessor = toteWinMarketCycleUpdateProcessor;
    }

    public void process(PgiCycleUpdate pgiCycleUpdate) {
        pgiCycleUpdateCache.put(pgiCycleUpdate);
        natsPublisher.publish(pgiCycleUpdate);
        createAndProcessToteMarketUpdate(pgiCycleUpdate);
    }

    private void createAndProcessToteMarketUpdate(PgiCycleUpdate pgiCycleUpdate) {
        PgiRaceInformationKey pgiRaceInformationKey = new PgiRaceInformationKey(pgiCycleUpdate.programName(), pgiCycleUpdate.race());
        @Nullable PgiRaceMetadata pgiRaceMetadata = pgiRaceMetadataCache.getBy(pgiRaceInformationKey);
        if (pgiRaceMetadata == null) {
            PgiCycleUpdateKey key = new PgiCycleUpdateKey(pgiCycleUpdate.programName(), pgiCycleUpdate.race(), pgiCycleUpdate.pgiBetType());
            if (loggedFailures.contains(key)) return;
            loggedFailures.add(key);
            LOGGER.error("Failed to create {} as {} was not found for [{} {} {} {}]",
                    ToteMarketCycleUpdate.class.getSimpleName(),
                    PgiRaceMetadata.class.getSimpleName(),
                    pgiCycleUpdate.programDateYmd(),
                    pgiCycleUpdate.programName(),
                    pgiCycleUpdate.programLongName(),
                    pgiCycleUpdate.race()
            );
            return;
        }
        createAndProcessToteMarketUpdate(pgiCycleUpdate, pgiRaceMetadata);
    }

    private void createAndProcessToteMarketUpdate(PgiCycleUpdate pgiCycleUpdate, PgiRaceMetadata pgiRaceMetadata) {
        @Nullable ToteBetType toteBetType = convertToToteBetType(pgiCycleUpdate.pgiBetType());
        if (toteBetType == null) return;
        ToteMarketCycleUpdate marketUpdate = createToteCycleMarketUpdate(pgiCycleUpdate, pgiRaceMetadata, toteBetType);
        toteMarketCycleUpdateProcessor.process(marketUpdate);
        if (toteBetType == ToteBetType.WIN) {
            ToteWinMarketCycleUpdate winMarketUpdate = createToteWinMarketCycleUpdate(pgiCycleUpdate, pgiRaceMetadata);
            toteWinMarketCycleUpdateProcessor.process(winMarketUpdate);
        }
    }
}
