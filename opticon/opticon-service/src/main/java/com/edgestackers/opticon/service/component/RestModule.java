package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.control.OpticonAccountBalanceSummaryCache;
import com.edgestackers.opticon.service.controller.OpticonServiceRestController;
import com.edgestackers.opticon.service.controller.request.HealthCheckRequestHandler;
import com.edgestackers.opticon.service.controller.request.StartupContextRequestHandler;
import com.edgestackers.opticon.service.cycles.OpticonMarketCycleUpdateCache;
import com.edgestackers.opticon.service.metadata.RaceMetadataCache;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCache;
import com.edgestackers.opticon.service.order.OpticonOrderUpdateCache;
import com.edgestackers.opticon.service.pacman.PacmanRaceFlucsUpdateCache;
import com.edgestackers.opticon.service.run.OpticonRunSummaryCache;
import com.edgestackers.opticon.service.turbo.OpticonDividendPredictionUpdateCache;
import com.edgestackers.opticon.service.turbo.OpticonTheoUpdateCache;
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
    public StartupContextRequestHandler startupContextRequestHandler(RaceMetadataCache raceMetadataCache,
                                                                     OpticonRunSummaryCache opticonRunSummaryCache,
                                                                     OpticonMarketCycleUpdateCache opticonMarketCycleUpdateCache,
                                                                     OpticonAccountBalanceSummaryCache opticonAccountBalanceSummaryCache,
                                                                     OpticonDividendPredictionUpdateCache dividendPredictionUpdateCache,
                                                                     OpticonTheoUpdateCache theoUpdateCache,
                                                                     OpticonOrderUpdateCache orderUpdateCache,
                                                                     PacmanRaceFlucsUpdateCache pacmanRaceFlucsUpdateCache,
                                                                     OpticonStrategyContextSummaryCache exoticContextSummaryCache) {
        return new StartupContextRequestHandler(
                raceMetadataCache,
                opticonRunSummaryCache,
                opticonMarketCycleUpdateCache,
                opticonAccountBalanceSummaryCache,
                dividendPredictionUpdateCache,
                theoUpdateCache,
                orderUpdateCache,
                pacmanRaceFlucsUpdateCache,
                exoticContextSummaryCache
        );
    }

    @Provides
    @Singleton
    public OpticonServiceRestController opticonServiceRestController(HealthCheckRequestHandler healthCheckRequestHandler,
                                                                     StartupContextRequestHandler startupContextRequestHandler) {
        return new OpticonServiceRestController(
                server,
                httpPort,
                healthCheckRequestHandler,
                startupContextRequestHandler
        );
    }
}
