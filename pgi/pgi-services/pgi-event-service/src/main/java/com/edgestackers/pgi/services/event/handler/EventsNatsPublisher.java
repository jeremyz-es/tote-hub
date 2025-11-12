package com.edgestackers.pgi.services.event.handler;

import com.edgestackers.pgi.services.event.datamodel.api.PgiEventMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventsNatsPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventsNatsPublisher.class);
    private static final ObjectMapper SERIALIZER = new ObjectMapper();
    private final Connection connection;
    private final String topic;

    public EventsNatsPublisher(Connection connection, String topic) {
        this.connection = connection;
        this.topic = topic;
    }

    public void publish(PgiEventMessage pgiEventMessage) {
        try {
            String message = SERIALIZER.writeValueAsString(pgiEventMessage);
            connection.publish(topic, message.getBytes());
        }
        catch (JsonProcessingException e) {
            LOGGER.error("Could not publish {} as serialization failed.", pgiEventMessage.getClass().getSimpleName());
        }
    }
}
