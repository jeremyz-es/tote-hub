package com.edgestackers.opticon.service.controller;

import com.edgestackers.opticon.service.controller.request.HealthCheckRequestHandler;
import com.edgestackers.opticon.service.controller.request.StartupContextRequestHandler;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.edgestackers.opticon.common.service.OpticonServiceUriProvider.INIT_CONTEXT_URI;
import static com.edgestackers.opticon.common.service.OpticonServiceUriProvider.PING_URI;

public class OpticonServiceRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpticonServiceRestController.class);
    private final Javalin server;
    private final int httpPort;
    private final HealthCheckRequestHandler healthCheckRequestHandler;
    private final StartupContextRequestHandler startupContextRequestHandler;

    public OpticonServiceRestController(Javalin server,
                                        int httpPort,
                                        HealthCheckRequestHandler healthCheckRequestHandler,
                                        StartupContextRequestHandler startupContextRequestHandler) {
        this.server = server;
        this.httpPort = httpPort;
        this.healthCheckRequestHandler = healthCheckRequestHandler;
        this.startupContextRequestHandler = startupContextRequestHandler;
    }

    public void start() {
        server.start(httpPort);
        server.get(PING_URI, healthCheckRequestHandler::handle);
        server.get(INIT_CONTEXT_URI, startupContextRequestHandler::handle);
        LOGGER.info("{} has started on Port {}.", getClass().getSimpleName(), httpPort);
    }
}
