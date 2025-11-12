package com.edgestackers.pgi.services.event.controller;

import com.edgestackers.pgi.services.event.controller.request.NotificationRequestHandler;
import com.edgestackers.pgi.services.event.controller.request.PingRequestHandler;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.edgestackers.pgi.services.event.controller.PgiEventServiceRestUriProvider.EVENT_LISTENER_URI;
import static com.edgestackers.pgi.services.event.controller.PgiEventServiceRestUriProvider.PING_URI;

public class PgiEventServiceRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiEventServiceRestController.class);
    private final Javalin server;
    private final int httpPort;
    private final NotificationRequestHandler notificationRequestHandler;
    private final PingRequestHandler pingRequestHandler;

    public PgiEventServiceRestController(Javalin server,
                                         int httpPort,
                                         NotificationRequestHandler notificationRequestHandler,
                                         PingRequestHandler pingRequestHandler) {
        this.server = server;
        this.httpPort = httpPort;
        this.notificationRequestHandler = notificationRequestHandler;
        this.pingRequestHandler = pingRequestHandler;
    }

    public void start() {
        server.post(EVENT_LISTENER_URI, notificationRequestHandler::handle);
        server.get(PING_URI, pingRequestHandler::handle);
        server.start(httpPort);
        LOGGER.info("[{}] has started! Bound to Port = [{}]", getClass().getSimpleName(), httpPort);
    }
}
