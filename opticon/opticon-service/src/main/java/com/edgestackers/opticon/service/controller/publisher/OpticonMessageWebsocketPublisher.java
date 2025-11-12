package com.edgestackers.opticon.service.controller.publisher;

import com.edgestackers.opticon.common.message.OpticonMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class OpticonMessageWebsocketPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpticonMessageWebsocketPublisher.class);
    private static final ObjectMapper SERIALIZER = new ObjectMapper();
    private final Set<WebSocket> connections;

    public OpticonMessageWebsocketPublisher(Set<WebSocket> connections) {
        this.connections = connections;
    }

    public void publish(OpticonMessage opticonMessage) {
        try {
            String message = SERIALIZER.writeValueAsString(opticonMessage);
            connections.forEach(webSocket -> webSocket.send(message));
        }
        catch (JsonProcessingException e) {
            LOGGER.error("Failed to serialize {} due to {} - {}", opticonMessage.getClass().getSimpleName(), e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
