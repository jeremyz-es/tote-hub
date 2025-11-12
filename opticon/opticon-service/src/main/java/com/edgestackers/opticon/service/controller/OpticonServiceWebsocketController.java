package com.edgestackers.opticon.service.controller;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Set;

public class OpticonServiceWebsocketController extends WebSocketServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpticonServiceWebsocketController.class);
    private final int port;
    private final Set<WebSocket> connections;

    public OpticonServiceWebsocketController(int port, Set<WebSocket> connections) {
        super(new InetSocketAddress(port));
        this.port = port;
        this.connections = connections;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        connections.add(conn);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        connections.remove(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {

    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        LOGGER.error("Websocket connection error: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
    }

    @Override
    public void onStart() {
        LOGGER.info("{} has started, bound to Port {}.", getClass().getSimpleName(), port);
    }
}
