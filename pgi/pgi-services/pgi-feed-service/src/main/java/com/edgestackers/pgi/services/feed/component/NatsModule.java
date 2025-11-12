package com.edgestackers.pgi.services.feed.component;

import com.edgestackers.pgi.services.feed.publisher.PgiMessageNatsPublisher;
import com.edgestackers.pgi.services.feed.publisher.PgiNatsTopicContext;
import dagger.Module;
import dagger.Provides;
import io.nats.client.Connection;

import javax.inject.Singleton;

@Module
public class NatsModule {
    private final Connection connection;
    private final PgiNatsTopicContext pgiNatsTopicContext;

    public NatsModule(Connection connection, PgiNatsTopicContext pgiNatsTopicContext) {
        this.connection = connection;
        this.pgiNatsTopicContext = pgiNatsTopicContext;
    }

    @Provides
    @Singleton
    public PgiMessageNatsPublisher pgiCycleUpdateNatsPublisher() {
        return new PgiMessageNatsPublisher(connection, pgiNatsTopicContext);
    }
}
