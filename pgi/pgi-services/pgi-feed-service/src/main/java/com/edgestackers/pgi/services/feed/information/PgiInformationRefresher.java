package com.edgestackers.pgi.services.feed.information;

import com.amtote.gws.*;
import com.edgestackers.pgi.common.client.GwsClientInfoProvider;
import com.edgestackers.pgi.services.feed.datamodel.PgiProgramInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.edgestackers.pgi.services.feed.information.GpProgramDetailConverter.convertToPgiProgramMetadata;
import static com.edgestackers.pgi.services.feed.information.PgiRaceInformationFactory.convertToPgiRaceMetadatas;

public class PgiInformationRefresher {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiInformationRefresher.class);
    private static final long METADATA_REFRESH_PERIOD_SECONDS = 10L;
    private final GwsSoap gwsSoap;
    private final GwsClientInfoProvider clientInfoProvider;
    private final PgiProgramInformationCache programMetadataCache;
    private final PgiRaceInformationCache raceMetadataCache;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public PgiInformationRefresher(GwsSoap gwsSoap,
                                   GwsClientInfoProvider clientInfoProvider,
                                   PgiProgramInformationCache programMetadataCache,
                                   PgiRaceInformationCache raceMetadataCache)
    {
        this.gwsSoap = gwsSoap;
        this.clientInfoProvider = clientInfoProvider;
        this.programMetadataCache = programMetadataCache;
        this.raceMetadataCache = raceMetadataCache;
    }

    public void start() {
        executorService.scheduleWithFixedDelay(this::refresh, 1L, METADATA_REFRESH_PERIOD_SECONDS, TimeUnit.SECONDS);
        LOGGER.info("[{}] has started! Refreshing [{}] every {} seconds.", getClass().getSimpleName(), PgiProgramInformation.class.getSimpleName(), METADATA_REFRESH_PERIOD_SECONDS);
    }

    private void refresh() {
        GpAvailablePrograms availablePrograms = gwsSoap.amTmcRequestAvailablePrograms(clientInfoProvider.gpHeader(), clientInfoProvider.masterAccountInfo().accountNumber());
        availablePrograms.getProgramList().getProgramInfo().forEach(this::handle);
    }

    private void handle(ProgramInfo programInfo) {
        try {
            GpProgramDetail gpProgramDetail = gwsSoap.amTrequestProgramDetail(clientInfoProvider.gpHeader(), programInfo.getProgramName());
            GpProgramDefinition gpProgramDefinition = gwsSoap.amTrequestProgramDefinition(clientInfoProvider.gpHeader(), programInfo.getProgramName(), true);
            PgiProgramInformation pgiProgramInformation = convertToPgiProgramMetadata(gpProgramDetail);
            programMetadataCache.put(pgiProgramInformation);
            convertToPgiRaceMetadatas(gpProgramDefinition, pgiProgramInformation).forEach(raceMetadataCache::put);
            // TODO: NULL HANDLING
        }
        catch (Exception e) {
            LOGGER.error("Failed to handle [{}] due to [{}] - {}", ProgramInfo.class.getSimpleName(), e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
