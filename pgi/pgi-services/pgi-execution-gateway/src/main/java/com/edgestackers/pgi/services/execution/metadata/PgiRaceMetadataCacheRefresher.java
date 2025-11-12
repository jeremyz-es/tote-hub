package com.edgestackers.pgi.services.execution.metadata;

import com.edgestackers.pgi.common.datamodel.metadata.PgiRaceMetadata;
import com.edgestackers.pgi.common.metadata.PgiMetadataApiClient;
import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PgiRaceMetadataCacheRefresher {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiRaceMetadataCacheRefresher.class);
    private static final String TODAY = LocalDate.now(ZoneId.of("Australia/Sydney")).toString();
    private final PgiRaceMetadataCache pgiRaceMetadataCache;
    private final PgiMetadataApiClient pgiMetadataApiClient;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public PgiRaceMetadataCacheRefresher(PgiRaceMetadataCache pgiRaceMetadataCache,
                                         PgiMetadataApiClient pgiMetadataApiClient) {
        this.pgiRaceMetadataCache = pgiRaceMetadataCache;
        this.pgiMetadataApiClient = pgiMetadataApiClient;
    }

    public void start() {
        executorService.scheduleWithFixedDelay(this::refresh, 1L, 10L, TimeUnit.SECONDS);
        LOGGER.info("[{}] has started!", getClass().getSimpleName());
    }

    private void refresh() {
        Arrays.stream(RaceCode.values()).forEach(this::refresh);
    }

    private void refresh(RaceCode raceType) {
        try {
            List<PgiRaceMetadata> metadatas = pgiMetadataApiClient.retrievePgiRaceMetadata(raceType, TODAY);
            metadatas.forEach(pgiRaceMetadataCache::put);
        }
        catch (Exception e) {
            LOGGER.error("Failed to refresh {} for {} due to {} - {}", PgiRaceMetadata.class.getSimpleName(), raceType, e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
