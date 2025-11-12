package com.edgestackers.pgi.services.execution.component;

import com.edgestackers.pgi.services.execution.controller.PgiExecutionGatewayRestController;
import com.edgestackers.pgi.services.execution.controller.request.HealthCheckRequestHandler;
import com.edgestackers.pgi.services.execution.controller.request.StrategyOrderRequestHandler;
import com.edgestackers.pgi.services.execution.order.StrategyOrderRequestProcessor;
import dagger.Module;
import dagger.Provides;
import io.javalin.Javalin;

import javax.inject.Singleton;

@Module
public class RestModule {
    private final int httpPort;
    private final Javalin server;

    public RestModule(int httpPort, Javalin server) {
        this.httpPort = httpPort;
        this.server = server;
    }

    @Provides
    @Singleton
    public HealthCheckRequestHandler healthCheckRequestHandler() {
        return new HealthCheckRequestHandler();
    }

    @Provides
    @Singleton
    public StrategyOrderRequestHandler strategyOrderRequestHandler(StrategyOrderRequestProcessor strategyOrderRequestProcessor) {
        return new StrategyOrderRequestHandler(strategyOrderRequestProcessor);
    }

    @Provides
    @Singleton
    public PgiExecutionGatewayRestController pgiExecutionGatewayRestController(HealthCheckRequestHandler healthCheckRequestHandler,
                                                                               StrategyOrderRequestHandler strategyOrderRequestHandler) {
        return new PgiExecutionGatewayRestController(
                httpPort,
                server,
                healthCheckRequestHandler,
                strategyOrderRequestHandler
        );
    }
}
