package com.edgestackers.pgi.services.execution.nats;

import com.edgestackers.pgi.common.datamodel.message.PgiOrderResponse;
import com.edgestackers.pgi.services.execution.filebet.FileBetStatusCheck;
import com.edgestackers.tote.hub.core.datamodel.message.ToteAccountBalanceSummary;
import com.edgestackers.tote.hub.core.datamodel.message.order.ToteOrderResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NatsPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(NatsPublisher.class);
    private static final ObjectMapper SERIALIZER = new ObjectMapper();
    private final Connection natsConnection;
    private final PgiExecutionGatewayNatsTopicContext natsTopicContext;

    public NatsPublisher(Connection natsConnection, PgiExecutionGatewayNatsTopicContext pgiExecutionGatewayNatsTopicContext) {
        this.natsConnection = natsConnection;
        this.natsTopicContext = pgiExecutionGatewayNatsTopicContext;
    }

    public void publish(PgiOrderResponse summary) {
        publish(summary, natsTopicContext.orderResponseTopic());
    }

    public void publish(ToteAccountBalanceSummary toteAccountBalanceSummary) {
        publish(toteAccountBalanceSummary, natsTopicContext.accountBalanceTopic());
    }

    public void publish(FileBetStatusCheck fileBetStatusCheck) {
        publish(fileBetStatusCheck, natsTopicContext.fileBetStatusCheckTopic());
    }

    public void publish(ToteOrderResponseMessage toteOrderResponseMessage) {
        publish(toteOrderResponseMessage, natsTopicContext.toteOrderResponseTopic());
    }

    private void publish(Object object, String topic) {
        try {
            String message = SERIALIZER.writeValueAsString(object);
            natsConnection.publish(topic, message.getBytes());
        }
        catch (JsonProcessingException e) {
            LOGGER.error("Could not publish [{}] due to [{}] - {}", object.getClass().getSimpleName(), e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
