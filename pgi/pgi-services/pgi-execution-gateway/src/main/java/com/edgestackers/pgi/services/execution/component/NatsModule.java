package com.edgestackers.pgi.services.execution.component;

import com.edgestackers.pgi.services.execution.nats.NatsPublisher;
import com.edgestackers.pgi.services.execution.nats.PgiExecutionGatewayNatsTopicContext;
import dagger.Module;
import dagger.Provides;
import io.nats.client.Connection;

import javax.inject.Singleton;

@Module
public class NatsModule {
    private final Connection natsConnection;
    private final PgiExecutionGatewayNatsTopicContext topicContext;

    public NatsModule(Connection natsConnection,
                      String orderResponseTopic,
                      String accountBalanceTopic,
                      String fileBetStatusCheckTopic,
                      String toteOrderResponseTopic) {
        this.natsConnection = natsConnection;
        this.topicContext = new PgiExecutionGatewayNatsTopicContext(orderResponseTopic, accountBalanceTopic, fileBetStatusCheckTopic, toteOrderResponseTopic);
    }

    @Provides
    @Singleton
    public NatsPublisher natsPublisher() {
        return new NatsPublisher(natsConnection, topicContext);
    }
}
