package com.edgestackers.opticon.service.metadata;

import com.edgestackers.opticon.common.datamodel.OpticonRunSummary;
import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;
import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import com.edgestackers.tote.hub.core.metadata.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MasterFieldsCacheRefresher {
    private static final Logger LOGGER = LoggerFactory.getLogger(MasterFieldsCacheRefresher.class);
    private static final String DATE = LocalDate.now(ZoneId.of("Australia/Sydney")).toString();
    private final MasterFieldsCache masterFieldsCache;
    private final MasterFieldsApiClient masterFieldsApiClient;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final Set<RaceCode> loggedErrors = new HashSet<>();

    public MasterFieldsCacheRefresher(MasterFieldsCache masterFieldsCache,
                                      MasterFieldsApiClient masterFieldsApiClient)
    {
        this.masterFieldsCache = masterFieldsCache;
        this.masterFieldsApiClient = masterFieldsApiClient;
    }

    public void start() {
        executorService.scheduleWithFixedDelay(this::refresh, 1L, 15L, TimeUnit.SECONDS);
        LOGGER.info("{} has started!", getClass().getSimpleName());
    }

    private void refresh() {
        Arrays.stream(RaceCode.values()).forEach(this::refreshFor);
    }

    private void refreshFor(RaceCode raceType) {
        try {
            List<MasterField> masterFields = masterFieldsApiClient.retrieveMasterFields(raceType, DATE);
            masterFields.forEach(masterFieldsCache::put);
        }
        catch (Exception e) {
            if (loggedErrors.contains(raceType)) return;
            loggedErrors.add(raceType);
            LOGGER.error("Failed to refresh {} for {} {} due to {} - {}",
                    OpticonRunSummary.class.getSimpleName(),
                    raceType,
                    DATE,
                    e.getClass().getSimpleName(),
                    e.getMessage()
            );
        }
    }
}
