package com.edgestackers.pgi.services.event.controller;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Set;

public class PgiEventServiceWebsocketController extends WebSocketServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiEventServiceWebsocketController.class);
    private final int port;
    private final Set<WebSocket> connections;

    public PgiEventServiceWebsocketController(int port, Set<WebSocket> connections) {
        super(new InetSocketAddress(port));
        this.port = port;
        this.connections = connections;
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        connections.add(webSocket);
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        connections.remove(webSocket);
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {}

    @Override
    public void onError(WebSocket webSocket, Exception e) {}

    @Override
    public void onStart() {
        LOGGER.info("[{}] has started successfully! Bound to Port=[{}]", this.getClass().getSimpleName(), port);
    }
}
