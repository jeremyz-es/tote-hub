package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.message.NitroOrderGeneratorStateMessageProcessor;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCache;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class NitroModule {

    @Provides
    @Singleton
    public NitroOrderGeneratorStateMessageProcessor nitroOrderGeneratorStateMessageProcessor(OpticonStrategyContextSummaryCache cache,
                                                                                             OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher)
    {
        return new NitroOrderGeneratorStateMessageProcessor(cache, opticonMessageWebsocketPublisher);
    }
}
