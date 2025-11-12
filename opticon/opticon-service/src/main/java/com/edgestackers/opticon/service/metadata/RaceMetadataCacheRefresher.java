package com.edgestackers.opticon.service.metadata;

import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;
import com.edgestackers.tote.hub.core.metadata.EsRaceMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RaceMetadataCacheRefresher {
    private static final Logger LOGGER = LoggerFactory.getLogger(RaceMetadataCacheRefresher.class);
    private static final String RACE_TYPE_FILTER = "FLAT";
    private static final long REFRESH_PERIOD_SECONDS = 15L;
    private final String TODAY = LocalDate.now(ZoneId.of("Australia/Sydney")).toString();
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final RaceMetadataCache raceMetadataCache;
    private final RaceMetadataApiClient raceMetadataApiClient;

    public RaceMetadataCacheRefresher(RaceMetadataCache raceMetadataCache,
                                      RaceMetadataApiClient raceMetadataApiClient)
    {
        this.raceMetadataCache = raceMetadataCache;
        this.raceMetadataApiClient = raceMetadataApiClient;
    }

    public void start() {
        refresh();
        executorService.scheduleWithFixedDelay(this::refresh, REFRESH_PERIOD_SECONDS, REFRESH_PERIOD_SECONDS, TimeUnit.SECONDS);
        LOGGER.info("{} has started! Refreshing {} every {}s.", getClass().getSimpleName(), EsRaceMetadata.class.getSimpleName(), REFRESH_PERIOD_SECONDS);
    }

    private void refresh() {
        Arrays.stream(RaceCode.values()).forEach(this::refreshFor);
    }

    private void refreshFor(RaceCode raceType) {
        try {
            List<EsRaceMetadata> esRaceMetadata = raceMetadataApiClient.retrieveEsRaceMetadataFor(raceType, TODAY);
            esRaceMetadata.stream().filter(raceMetadata -> raceMetadata.raceType().equals(RACE_TYPE_FILTER)).forEach(raceMetadataCache::put);
        }
        catch (Exception e) {
            LOGGER.error("Failed to refresh {} for {} {} due to {} - {}",
                    EsRaceMetadata.class.getSimpleName(),
                    raceType,
                    TODAY,
                    e.getClass().getSimpleName(),
                    e.getMessage()
            );
        }
    }
}
