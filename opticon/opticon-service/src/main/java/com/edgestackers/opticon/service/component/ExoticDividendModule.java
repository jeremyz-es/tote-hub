package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.message.ExoticDividendMessageProcessor;
import com.edgestackers.opticon.service.metadata.RaceMetadataCache;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCache;
import com.edgestackers.opticon.service.turbo.OpticonDividendPredictionUpdateCache;
import com.edgestackers.opticon.service.turbo.OpticonDividendPredictionUpdateFactory;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ExoticDividendModule {

    @Provides
    @Singleton
    public OpticonDividendPredictionUpdateCache opticonDividendPredictionUpdateCache() {
        return new OpticonDividendPredictionUpdateCache();
    }

    @Provides
    @Singleton
    public OpticonDividendPredictionUpdateFactory opticonDividendPredictionUpdateFactory(OpticonDividendPredictionUpdateCache updateCache,
                                                                                         RaceMetadataCache raceMetadataCache) {
        return new OpticonDividendPredictionUpdateFactory(updateCache, raceMetadataCache);
    }

    @Provides
    @Singleton
    public ExoticDividendMessageProcessor exoticDividendMessageProcessor(OpticonDividendPredictionUpdateCache updateCache,
                                                                         OpticonDividendPredictionUpdateFactory updateFactory,
                                                                         OpticonStrategyContextSummaryCache exoticContextSummaryCache,
                                                                         OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher) {
        return new ExoticDividendMessageProcessor(
                updateFactory,
                updateCache,
                exoticContextSummaryCache,
                opticonMessageWebsocketPublisher
        );
    }
}
