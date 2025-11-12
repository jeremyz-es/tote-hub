package com.edgestackers.opticon.common.client;

import com.edgestackers.opticon.common.message.OpticonMessage;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class OpticonServiceWebsocketClient extends WebSocketClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpticonServiceWebsocketClient.class);
    private static final ObjectMapper DESERIALIZER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final OpticonMessageHandler opticonMessageHandler;
    private final OpticonWebsocketDisconnectionHandler opticonWebsocketDisconnectionHandler;

    public OpticonServiceWebsocketClient(String subscriptionEndpoint,
                                         OpticonMessageHandler opticonMessageHandler,
                                         OpticonWebsocketDisconnectionHandler opticonWebsocketDisconnectionHandler)
    {
        super(URI.create(subscriptionEndpoint));
        this.opticonMessageHandler = opticonMessageHandler;
        this.opticonWebsocketDisconnectionHandler = opticonWebsocketDisconnectionHandler;
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        LOGGER.info("{} has connected to Opticon!", getClass().getSimpleName());
    }

    @Override
    public void onMessage(String message) {
        try {
            OpticonMessage opticonMessage = DESERIALIZER.readValue(message, OpticonMessage.class);
            opticonMessageHandler.handle(opticonMessage);
        }
        catch (Exception e) {
            LOGGER.error("Failed to deserialize WS message into {} due to {} - {}: Raw Message: {}",
                    OpticonMessage.class.getSimpleName(),
                    e.getClass().getSimpleName(),
                    e.getMessage(),
                    message
            );
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        LOGGER.info("{} has closed websocket connection to Opticon.", getClass().getSimpleName());
        // opticonWebsocketDisconnectionHandler.onDisconnection();
    }

    @Override
    public void onError(Exception ex) {
        LOGGER.error("{} has run into {} - {}", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex.getMessage());
    }
}
