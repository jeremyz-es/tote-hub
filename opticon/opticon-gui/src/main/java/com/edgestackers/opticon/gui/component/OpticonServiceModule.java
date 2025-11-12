package com.edgestackers.opticon.gui.component;

import com.edgestackers.opticon.common.client.OpticonApiClient;
import com.edgestackers.opticon.gui.message.OpticonMessageCacheRefresher;
import com.edgestackers.opticon.gui.message.OpticonMessageProcessor;
import com.edgestackers.opticon.gui.message.OpticonMessageSubscription;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class OpticonServiceModule {
    private final String subscriptionEndpoint;
    private final String baseEndpoint;

    public OpticonServiceModule(String subscriptionEndpoint,
                                String baseEndpoint) {
        this.subscriptionEndpoint = subscriptionEndpoint;
        this.baseEndpoint = baseEndpoint;
    }

    @Provides
    @Singleton
    public OpticonApiClient opticonApiClient() {
        return new OpticonApiClient(baseEndpoint);
    }

    @Provides
    @Singleton
    public OpticonMessageSubscription opticonMessageSubscription(OpticonMessageCacheRefresher opticonMessageCacheRefresher,
                                                                 OpticonMessageProcessor opticonMessageProcessor) {
        return new OpticonMessageSubscription(
                subscriptionEndpoint,
                opticonMessageCacheRefresher,
                opticonMessageProcessor
        );
    }
}
