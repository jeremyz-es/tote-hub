package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.message.RaceEventUpdateMessageProcessor;
import com.edgestackers.opticon.service.metadata.RaceEventUpdateRegistry;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCache;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class RaceEventModule {

    @Provides
    @Singleton
    public RaceEventUpdateRegistry raceEventUpdateRegistry() {
        return new RaceEventUpdateRegistry();
    }

    @Provides
    @Singleton
    public RaceEventUpdateMessageProcessor raceEventUpdateMessageProcessor(RaceEventUpdateRegistry raceEventUpdateRegistry,
                                                                           OpticonStrategyContextSummaryCache opticonStrategyContextSummaryCache,
                                                                           OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher)
    {
        return new RaceEventUpdateMessageProcessor(
                raceEventUpdateRegistry,
                opticonStrategyContextSummaryCache,
                opticonMessageWebsocketPublisher
        );
    }
}
