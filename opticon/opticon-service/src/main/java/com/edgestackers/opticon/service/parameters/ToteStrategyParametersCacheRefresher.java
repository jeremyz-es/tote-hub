package com.edgestackers.opticon.service.parameters;

import com.edgestackers.tote.hub.core.parameters.CaesarApiClient;
import com.edgestackers.tote.hub.core.parameters.ToteStrategyParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ToteStrategyParametersCacheRefresher {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToteStrategyParametersCacheRefresher.class);
    private final ToteStrategyParametersCache cache;
    private final CaesarApiClient caesarApiClient;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public ToteStrategyParametersCacheRefresher(ToteStrategyParametersCache cache,
                                                CaesarApiClient caesarApiClient)
    {
        this.cache = cache;
        this.caesarApiClient = caesarApiClient;
    }

    public void start() {
        executorService.scheduleWithFixedDelay(this::refresh, 1L, 30L, TimeUnit.SECONDS);
        LOGGER.info("{} has started!", getClass().getSimpleName());
    }

    private void refresh() {
        try {
            List<ToteStrategyParameters> strategyParameters = caesarApiClient.retrieveToteStrategyParameters();
            cache.clearAndPutAll(strategyParameters);
        }
        catch (Exception e) {
            LOGGER.error("Failed to refresh {} due to {} - {}", ToteStrategyParameters.class.getSimpleName(), e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
