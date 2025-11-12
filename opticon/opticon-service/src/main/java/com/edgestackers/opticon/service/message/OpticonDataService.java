package com.edgestackers.opticon.service.message;

import com.edgestackers.opticon.service.metadata.MasterFieldsCacheRefresher;
import com.edgestackers.opticon.service.metadata.RaceMetadataCacheRefresher;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCacheRefresher;
import com.edgestackers.opticon.service.parameters.ToteStrategyParametersCacheRefresher;
import com.edgestackers.opticon.service.run.OpticonRunSummaryCacheRefresher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpticonDataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpticonDataService.class);
    private final ToteMessageNatsSubscription toteMessageNatsSubscription;
    private final RaceMetadataCacheRefresher raceMetadataCacheRefresher;
    private final OpticonRunSummaryCacheRefresher opticonRunSummaryCacheRefresher;
    private final MasterFieldsCacheRefresher masterFieldsCacheRefresher;
    private final OpticonStrategyContextSummaryCacheRefresher opticonStrategyContextSummaryCacheRefresher;
    private final ToteStrategyParametersCacheRefresher toteStrategyParametersCacheRefresher;

    public OpticonDataService(ToteMessageNatsSubscription toteMessageNatsSubscription,
                              RaceMetadataCacheRefresher raceMetadataCacheRefresher,
                              OpticonRunSummaryCacheRefresher opticonRunSummaryCacheRefresher,
                              MasterFieldsCacheRefresher masterFieldsCacheRefresher,
                              OpticonStrategyContextSummaryCacheRefresher opticonStrategyContextSummaryCacheRefresher,
                              ToteStrategyParametersCacheRefresher toteStrategyParametersCacheRefresher)
    {
        this.toteMessageNatsSubscription = toteMessageNatsSubscription;
        this.raceMetadataCacheRefresher = raceMetadataCacheRefresher;
        this.opticonRunSummaryCacheRefresher = opticonRunSummaryCacheRefresher;
        this.masterFieldsCacheRefresher = masterFieldsCacheRefresher;
        this.opticonStrategyContextSummaryCacheRefresher = opticonStrategyContextSummaryCacheRefresher;
        this.toteStrategyParametersCacheRefresher = toteStrategyParametersCacheRefresher;
    }

    public void start() {
        toteMessageNatsSubscription.start();
        raceMetadataCacheRefresher.start();
        opticonRunSummaryCacheRefresher.start();
        masterFieldsCacheRefresher.start();
        opticonStrategyContextSummaryCacheRefresher.start();
        toteStrategyParametersCacheRefresher.start();
        LOGGER.info("{} has started!", getClass().getSimpleName());
    }
}
