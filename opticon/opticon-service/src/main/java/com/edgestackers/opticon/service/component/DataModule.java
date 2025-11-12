package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.control.OpticonAccountBalanceSummaryCache;
import com.edgestackers.opticon.service.control.OpticonAccountBalanceSummaryFactory;
import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.message.*;
import com.edgestackers.opticon.service.metadata.MasterFieldsCacheRefresher;
import com.edgestackers.opticon.service.metadata.RaceMetadataCacheRefresher;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCacheRefresher;
import com.edgestackers.opticon.service.parameters.ToteStrategyParametersCacheRefresher;
import com.edgestackers.opticon.service.run.OpticonRunSummaryCacheRefresher;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DataModule {

    @Provides
    @Singleton
    public OpticonAccountBalanceSummaryCache provideAccountBalanceSummaryCache() {
        return new OpticonAccountBalanceSummaryCache();
    }

    @Provides
    @Singleton
    public OpticonAccountBalanceSummaryFactory opticonAccountBalanceSummaryFactory(OpticonAccountBalanceSummaryCache opticonAccountBalanceSummaryCache) {
        return new OpticonAccountBalanceSummaryFactory(opticonAccountBalanceSummaryCache);
    }

    @Provides
    @Singleton
    public ToteAccountBalanceSummaryProcessor toteAccountBalanceSummaryProcessor(OpticonAccountBalanceSummaryFactory opticonAccountBalanceSummaryFactory,
                                                                                 OpticonAccountBalanceSummaryCache opticonAccountBalanceSummaryCache,
                                                                                 OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher)
    {
        return new ToteAccountBalanceSummaryProcessor(
                opticonAccountBalanceSummaryFactory,
                opticonAccountBalanceSummaryCache,
                opticonMessageWebsocketPublisher
        );
    }

    @Provides
    @Singleton
    public ToteMessageProcessor toteMessageProcessor(ToteMarketCycleUpdateProcessor toteMarketCycleUpdateProcessor,
                                                     RaceEventUpdateMessageProcessor raceEventUpdateMessageProcessor,
                                                     ToteWinMarketCycleUpdateProcessor toteWinMarketCycleUpdateProcessor,
                                                     ToteAccountBalanceSummaryProcessor toteAccountBalanceSummaryProcessor,
                                                     ExoticDividendMessageProcessor exoticDividendMessageProcessor,
                                                     ExoticTheoMessageProcessor exoticTheoMessageProcessor,
                                                     ToteOrderResponseMessageProcessor toteOrderResponseMessageProcessor,
                                                     NitroOrderExecutionMessageProcessor nitroOrderExecutionMessageProcessor,
                                                     PacmanRaceFlucsMessageProcessor pacmanRaceFlucsMessageProcessor,
                                                     NitroOrderGeneratorStateMessageProcessor nitroOrderGeneratorStateMessageProcessor)
    {
        return new ToteMessageProcessor(
                toteMarketCycleUpdateProcessor,
                raceEventUpdateMessageProcessor,
                toteWinMarketCycleUpdateProcessor,
                toteAccountBalanceSummaryProcessor,
                exoticDividendMessageProcessor,
                exoticTheoMessageProcessor,
                toteOrderResponseMessageProcessor,
                nitroOrderExecutionMessageProcessor,
                pacmanRaceFlucsMessageProcessor,
                nitroOrderGeneratorStateMessageProcessor
        );
    }

    @Provides
    @Singleton
    public OpticonDataService opticonDataService(ToteMessageNatsSubscription toteMessageNatsSubscription,
                                                 RaceMetadataCacheRefresher raceMetadataCacheRefresher,
                                                 OpticonRunSummaryCacheRefresher opticonRunSummaryCacheRefresher,
                                                 MasterFieldsCacheRefresher masterFieldsCacheRefresher,
                                                 OpticonStrategyContextSummaryCacheRefresher opticonStrategyContextSummaryCacheRefresher,
                                                 ToteStrategyParametersCacheRefresher toteStrategyParametersCacheRefresher)
    {
        return new OpticonDataService(
                toteMessageNatsSubscription,
                raceMetadataCacheRefresher,
                opticonRunSummaryCacheRefresher,
                masterFieldsCacheRefresher,
                opticonStrategyContextSummaryCacheRefresher,
                toteStrategyParametersCacheRefresher
        );
    }
}
