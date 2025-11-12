package com.edgestackers.pgi.services.execution.controller;

import com.edgestackers.pgi.services.execution.controller.request.HealthCheckRequestHandler;
import com.edgestackers.pgi.services.execution.controller.request.StrategyOrderRequestHandler;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.edgestackers.pgi.services.execution.controller.PgiExecutionGatewayRestUrlProvider.HEALTH_CHECK_URI;
import static com.edgestackers.pgi.services.execution.controller.PgiExecutionGatewayRestUrlProvider.ORDER_SUBMISSION_URI;

public class PgiExecutionGatewayRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiExecutionGatewayRestController.class);
    private final int httpPort;
    private final Javalin server;
    private final HealthCheckRequestHandler healthCheckRequestHandler;
    private final StrategyOrderRequestHandler strategyOrderRequestHandler;

    public PgiExecutionGatewayRestController(int httpPort,
                                             Javalin server,
                                             HealthCheckRequestHandler healthCheckRequestHandler,
                                             StrategyOrderRequestHandler strategyOrderRequestHandler) {
        this.httpPort = httpPort;
        this.server = server;
        this.healthCheckRequestHandler = healthCheckRequestHandler;
        this.strategyOrderRequestHandler = strategyOrderRequestHandler;
    }

    public void start() {
        server.get(HEALTH_CHECK_URI, healthCheckRequestHandler::handle);
        server.post(ORDER_SUBMISSION_URI, strategyOrderRequestHandler::handle);
        server.start(httpPort);
        LOGGER.info("[{}] has started! Bound to Port [{}]", getClass().getSimpleName(), httpPort);
    }
}
