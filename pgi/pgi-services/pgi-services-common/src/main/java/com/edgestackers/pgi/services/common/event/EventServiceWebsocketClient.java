package com.edgestackers.pgi.services.common.event;

import com.edgestackers.pgi.services.common.datamodel.PgiEventServiceNotificationMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class EventServiceWebsocketClient extends WebSocketClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceWebsocketClient.class);
    private static final ObjectMapper DESERIALIZER = new ObjectMapper();
    private final PgiEventServiceNotificationMessageHandler handler;

    public EventServiceWebsocketClient(String subscriptionEndpoint,
                                       PgiEventServiceNotificationMessageHandler handler)
    {
        super(URI.create(subscriptionEndpoint));
        this.handler = handler;
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        LOGGER.info("[{}] has connected to [PgiEventService]!", getClass().getSimpleName());
    }

    @Override
    public void onMessage(String message) {
        try {
            PgiEventServiceNotificationMessage notificationMessage = DESERIALIZER.readValue(message, PgiEventServiceNotificationMessage.class);
            handler.handleNotification(notificationMessage);
        }
        catch (Exception e) {
            LOGGER.error("Failed to deserialize ws message {} into [{}] due to [{}] - {}", PgiEventServiceNotificationMessage.class.getSimpleName(), message, e.getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        LOGGER.info("{} websocket connection to PgiEventService has closed.", getClass().getSimpleName());
    }

    @Override
    public void onError(Exception ex) {

    }
}
