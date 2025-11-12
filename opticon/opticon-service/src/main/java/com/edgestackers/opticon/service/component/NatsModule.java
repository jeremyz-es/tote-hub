package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.message.ToteMessageNatsSubscription;
import com.edgestackers.opticon.service.message.NatsTopicContext;
import com.edgestackers.opticon.service.message.ToteMessageProcessor;
import dagger.Module;
import dagger.Provides;
import io.nats.client.Connection;

import javax.inject.Singleton;

@Module
public class NatsModule {
    private final Connection connection;
    private final NatsTopicContext natsTopicContext;

    public NatsModule(Connection connection, NatsTopicContext natsTopicContext) {
        this.connection = connection;
        this.natsTopicContext = natsTopicContext;
    }

    @Provides
    @Singleton
    public ToteMessageNatsSubscription natsSubscription(ToteMessageProcessor toteMessageProcessor) {
        return new ToteMessageNatsSubscription(connection, natsTopicContext, toteMessageProcessor);
    }
}
