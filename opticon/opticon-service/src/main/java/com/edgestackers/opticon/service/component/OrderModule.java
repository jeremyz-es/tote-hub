package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.message.NitroOrderExecutionMessageProcessor;
import com.edgestackers.opticon.service.message.ToteOrderResponseMessageProcessor;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCache;
import com.edgestackers.opticon.service.order.*;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class OrderModule {

    @Provides
    @Singleton
    public NitroOrderExecutionMessageCache nitroOrderExecutionMessageCache() {
        return new NitroOrderExecutionMessageCache();
    }

    @Provides
    @Singleton
    public ToteOrderResponseMessageCache toteOrderResponseMessageCache() {
        return new ToteOrderResponseMessageCache();
    }

    @Provides
    @Singleton
    public OpticonOrderUpdateCache opticonOrderUpdateCache() {
        return new OpticonOrderUpdateCache();
    }

    @Provides
    @Singleton
    public OpticonOrderUpdateFactory opticonOrderUpdateFactory(NitroOrderExecutionMessageCache nitroOrderExecutionMessageCache,
                                                               ToteOrderResponseMessageCache toteOrderResponseMessageCache)
    {
        return new OpticonOrderUpdateFactory(nitroOrderExecutionMessageCache, toteOrderResponseMessageCache);
    }

    @Provides
    @Singleton
    public ToteOrderResponseMessageProcessor toteOrderResponseMessageProcessor(ToteOrderResponseMessageCache orderResponseCache,
                                                                               OpticonOrderUpdateFactory opticonOrderUpdateFactory,
                                                                               OpticonOrderUpdateProcessor opticonOrderUpdateProcessor,
                                                                               OpticonStrategyContextSummaryCache opticonStrategyContextSummaryCache,
                                                                               NitroOrderExecutionMessageCache nitroOrderExecutionMessageCache,
                                                                               OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher)
    {
        return new ToteOrderResponseMessageProcessor(
                orderResponseCache,
                opticonOrderUpdateFactory,
                opticonOrderUpdateProcessor,
                opticonStrategyContextSummaryCache,
                nitroOrderExecutionMessageCache,
                opticonMessageWebsocketPublisher
        );
    }

    @Provides
    @Singleton
    public NitroOrderExecutionMessageProcessor nitroOrderExecutionMessageProcessor(NitroOrderExecutionMessageCache nitroOrderExecutionMessageCache,
                                                                                   ToteOrderResponseMessageCache toteOrderResponseMessageCache,
                                                                                   OpticonOrderUpdateFactory opticonOrderUpdateFactory,
                                                                                   OpticonOrderUpdateProcessor opticonOrderUpdateProcessor,
                                                                                   OpticonStrategyContextSummaryCache opticonStrategyContextSummaryCache,
                                                                                   OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher) {
        return new NitroOrderExecutionMessageProcessor(
                nitroOrderExecutionMessageCache,
                toteOrderResponseMessageCache,
                opticonOrderUpdateFactory,
                opticonOrderUpdateProcessor,
                opticonStrategyContextSummaryCache,
                opticonMessageWebsocketPublisher
        );
    }

    @Provides
    @Singleton
    public OpticonOrderUpdateProcessor opticonOrderUpdateProcessor(OpticonOrderUpdateCache cache,
                                                                   OpticonMessageWebsocketPublisher messageWebsocketPublisher) {
        return new OpticonOrderUpdateProcessor(cache, messageWebsocketPublisher);
    }
}
