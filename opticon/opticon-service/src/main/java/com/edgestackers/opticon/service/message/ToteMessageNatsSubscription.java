package com.edgestackers.opticon.service.message;

import com.edgestackers.tote.hub.core.datamodel.message.ToteMessage;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class ToteMessageNatsSubscription {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToteMessageNatsSubscription.class);
    private static final ObjectMapper DESERIALIZER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final Connection natsConnection;
    private final NatsTopicContext topicContext;
    private final ToteMessageProcessor toteMessageProcessor;

    public ToteMessageNatsSubscription(Connection natsConnection,
                                       NatsTopicContext natsTopicContext,
                                       ToteMessageProcessor toteMessageProcessor) {
        this.natsConnection = natsConnection;
        this.topicContext = natsTopicContext;
        this.toteMessageProcessor = toteMessageProcessor;
    }

    public void start() {
        Dispatcher totesTopicDispatcher = natsConnection.createDispatcher(this::handle);
        Dispatcher nitroTopicDispatcher = natsConnection.createDispatcher(this::handle);
        Dispatcher turboTopicDispatcher = natsConnection.createDispatcher(this::handle);
        totesTopicDispatcher.subscribe(topicContext.totesTopic());
        nitroTopicDispatcher.subscribe(topicContext.nitroTopic());
        turboTopicDispatcher.subscribe(topicContext.turboTopic());
        LOGGER.info("[{}] Subscribed to tote execution messages on Topics: {} {} {}", getClass().getSimpleName(), topicContext.totesTopic(), topicContext.nitroTopic(), topicContext.turboTopic());
    }

    private void handle(Message natsMessage) {
        String message = new String(natsMessage.getData(), StandardCharsets.UTF_8);
        try {
            ToteMessage toteMessage = DESERIALIZER.readValue(message, ToteMessage.class);
            toteMessageProcessor.process(toteMessage);
        }
        catch (Exception e) {
            LOGGER.debug("Could not deserialize NATS message into {} due to {}: {} - RAW: {}",
                    ToteMessage.class.getSimpleName(),
                    e.getClass().getSimpleName(),
                    e.getMessage(),
                    message
            );
        }
    }
}
