package com.edgestackers.pgi.services.event.controller.publisher;

import com.edgestackers.pgi.services.common.datamodel.PgiEventServiceNotificationMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class PgiEventServiceWebsocketPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiEventServiceWebsocketPublisher.class);
    private static final ObjectMapper SERIALIZER = new ObjectMapper();
    private final Set<WebSocket> connections;

    public PgiEventServiceWebsocketPublisher(Set<WebSocket> connections) {
        this.connections = connections;
    }

    public void publish(PgiEventServiceNotificationMessage message) {
        try {
            String serialized = SERIALIZER.writeValueAsString(message);
            connections.forEach(webSocket -> webSocket.send(serialized));
        }
        catch (JsonProcessingException e) {
            LOGGER.error("Failed to publish [{}] due to [{}] - {}", message.getClass().getSimpleName(), e.getMessage(), e.getMessage());
        }
    }
}
