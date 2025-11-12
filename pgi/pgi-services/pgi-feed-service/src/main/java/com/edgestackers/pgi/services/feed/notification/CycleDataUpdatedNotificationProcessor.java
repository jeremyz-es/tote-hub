package com.edgestackers.pgi.services.feed.notification;

import com.amtote.gws.GpCycleData;
import com.amtote.gws.GpDisplayRequest;
import com.amtote.gws.GwsSoap;
import com.edgestackers.pgi.common.client.GwsClientInfoProvider;
import com.edgestackers.pgi.services.feed.datamodel.PgiRaceInformation;
import com.edgestackers.pgi.services.feed.datamodel.PgiRaceInformationKey;
import com.edgestackers.pgi.common.datamodel.cycle.PgiCycleUpdate;
import com.edgestackers.pgi.services.common.datamodel.CycleDataUpdatedNotificationMessage;
import com.edgestackers.pgi.services.common.datamodel.PgiTotePoolId;
import com.edgestackers.pgi.services.feed.cycle.PgiCycleUpdateProcessor;
import com.edgestackers.pgi.services.feed.information.PgiRaceInformationCache;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.edgestackers.pgi.services.feed.cycle.PgiCycleUpdateFactory.convertToPgiCycleUpdates;
import static com.edgestackers.pgi.services.feed.notification.GpDisplayRequestFactory.createDisplayRequestFor;

public class CycleDataUpdatedNotificationProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CycleDataUpdatedNotificationProcessor.class);
    private final GwsSoap gwsSoap;
    private final GwsClientInfoProvider gwsClientInfoProvider;
    private final PgiRaceInformationCache raceMetadataCache;
    private final PgiCycleUpdateProcessor pgiCycleUpdateProcessor;
    private final PgiTotePoolId pgiTotePoolId;

    public CycleDataUpdatedNotificationProcessor(GwsSoap gwsSoap,
                                                 GwsClientInfoProvider gwsClientInfoProvider,
                                                 PgiRaceInformationCache raceMetadataCache,
                                                 PgiCycleUpdateProcessor pgiCycleUpdateProcessor,
                                                 PgiTotePoolId pgiTotePoolId) {
        this.gwsSoap = gwsSoap;
        this.gwsClientInfoProvider = gwsClientInfoProvider;
        this.raceMetadataCache = raceMetadataCache;
        this.pgiCycleUpdateProcessor = pgiCycleUpdateProcessor;
        this.pgiTotePoolId = pgiTotePoolId;
    }

    public void handle(CycleDataUpdatedNotificationMessage notification) {
        if (pgiTotePoolId != notification.toteId()) {
            return;
        }
        @Nullable PgiRaceInformation pgiRaceInformation = raceMetadataCache.get(new PgiRaceInformationKey(notification.programName(), notification.race()));
        if (pgiRaceInformation == null) {
            LOGGER.error("Could not handle [{}] for Program Name/Race: [{}/{}] as no matching {} was found.",
                    CycleDataUpdatedNotificationMessage.class.getSimpleName(),
                    notification.programName(),
                    notification.race(),
                    PgiRaceInformation.class.getSimpleName()
            );
            return;
        }
        GpDisplayRequest gpDisplayRequest = createDisplayRequestFor(notification, pgiRaceInformation);
        GpCycleData gpCycleData = gwsSoap.amTrequestCycleData(gwsClientInfoProvider.gpHeader(), gpDisplayRequest);
        List<PgiCycleUpdate> pgiCycleUpdates = convertToPgiCycleUpdates(gpCycleData, pgiRaceInformation, pgiTotePoolId);
        pgiCycleUpdates.forEach(pgiCycleUpdateProcessor::process);
    }
}
