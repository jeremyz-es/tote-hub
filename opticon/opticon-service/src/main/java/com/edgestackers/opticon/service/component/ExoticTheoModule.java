package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.message.ExoticTheoMessageProcessor;
import com.edgestackers.opticon.service.metadata.RaceEventUpdateRegistry;
import com.edgestackers.opticon.service.metadata.RaceMetadataCache;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCache;
import com.edgestackers.opticon.service.run.OpticonRunSummaryCache;
import com.edgestackers.opticon.service.run.OpticonRunTheoCache;
import com.edgestackers.opticon.service.turbo.OpticonTheoUpdateCache;
import com.edgestackers.opticon.service.turbo.OpticonTheoUpdateFactory;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ExoticTheoModule {

    @Provides
    @Singleton
    public OpticonTheoUpdateCache opticonTheoUpdateCache() {
        return new OpticonTheoUpdateCache();
    }

    @Provides
    @Singleton
    public OpticonTheoUpdateFactory opticonTheoUpdateFactory(OpticonTheoUpdateCache opticonTheoUpdateCache,
                                                             RaceMetadataCache raceMetadataCache) {
        return new OpticonTheoUpdateFactory(opticonTheoUpdateCache, raceMetadataCache);
    }

    @Provides
    @Singleton
    public ExoticTheoMessageProcessor exoticTheoMessageProcessor(OpticonTheoUpdateCache opticonTheoUpdateCache,
                                                                 OpticonTheoUpdateFactory opticonTheoUpdateFactory,
                                                                 OpticonRunSummaryCache runSummaryCache,
                                                                 RaceEventUpdateRegistry raceEventUpdateRegistry,
                                                                 OpticonRunTheoCache opticonRunTheoCache,
                                                                 OpticonStrategyContextSummaryCache exoticContextSummaryCache,
                                                                 OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher)
    {
        return new ExoticTheoMessageProcessor(
                opticonTheoUpdateFactory,
                opticonTheoUpdateCache,
                runSummaryCache,
                raceEventUpdateRegistry,
                opticonRunTheoCache,
                exoticContextSummaryCache,
                opticonMessageWebsocketPublisher
        );
    }
}
