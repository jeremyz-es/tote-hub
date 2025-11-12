package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.cycles.OpticonMarketCycleUpdateCache;
import com.edgestackers.opticon.service.cycles.OpticonMarketCycleUpdateFactory;
import com.edgestackers.opticon.service.cycles.OpticonWinPoolUpdateCache;
import com.edgestackers.opticon.service.message.ToteMarketCycleUpdateProcessor;
import com.edgestackers.opticon.service.message.ToteWinMarketCycleUpdateProcessor;
import com.edgestackers.opticon.service.metadata.RaceEventUpdateRegistry;
import com.edgestackers.opticon.service.metadata.RaceMetadataCache;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCache;
import com.edgestackers.opticon.service.run.OpticonRunSummaryCache;
import com.edgestackers.opticon.service.run.OpticonWinMarketSummaryCache;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class CycleUpdateModule {

    @Provides
    @Singleton
    public OpticonWinMarketSummaryCache opticonWinMarketSummaryCache() {
        return new OpticonWinMarketSummaryCache();
    }

    @Provides
    @Singleton
    public OpticonMarketCycleUpdateCache opticonMarketCycleUpdateCache() {
        return new OpticonMarketCycleUpdateCache();
    }

    @Provides
    @Singleton
    public OpticonMarketCycleUpdateFactory opticonMarketCycleUpdateFactory(OpticonMarketCycleUpdateCache opticonMarketCycleUpdateCache,
                                                                           RaceEventUpdateRegistry raceEventUpdateRegistry,
                                                                           RaceMetadataCache raceMetadataCache)
    {
        return new OpticonMarketCycleUpdateFactory(
                opticonMarketCycleUpdateCache,
                raceEventUpdateRegistry,
                raceMetadataCache
        );
    }

    @Provides
    @Singleton
    public ToteMarketCycleUpdateProcessor toteMarketCycleUpdateProcessor(OpticonMarketCycleUpdateFactory opticonMarketCycleUpdateFactory,
                                                                         OpticonMarketCycleUpdateCache opticonMarketCycleUpdateCache,
                                                                         OpticonStrategyContextSummaryCache opticonStrategyContextSummaryCache,
                                                                         OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher) {
        return new ToteMarketCycleUpdateProcessor(
                opticonMarketCycleUpdateFactory,
                opticonMarketCycleUpdateCache,
                opticonStrategyContextSummaryCache,
                opticonMessageWebsocketPublisher
        );
    }

    @Provides
    @Singleton
    public OpticonWinPoolUpdateCache opticonWinPoolUpdateCache() {
        return new OpticonWinPoolUpdateCache();
    }

    @Provides
    @Singleton
    public ToteWinMarketCycleUpdateProcessor toteWinMarketCycleUpdateProcessor(OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher,
                                                                               OpticonRunSummaryCache opticonRunSummaryCache,
                                                                               OpticonWinMarketSummaryCache winMarketSummaryCache,
                                                                               OpticonWinPoolUpdateCache opticonWinPoolUpdateCache) {
        return new ToteWinMarketCycleUpdateProcessor(
                opticonMessageWebsocketPublisher,
                opticonRunSummaryCache,
                winMarketSummaryCache,
                opticonWinPoolUpdateCache
        );
    }
}
