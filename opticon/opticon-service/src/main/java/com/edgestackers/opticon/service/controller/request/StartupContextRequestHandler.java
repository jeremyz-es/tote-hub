package com.edgestackers.opticon.service.controller.request;

import com.edgestackers.opticon.common.datamodel.*;
import com.edgestackers.opticon.service.control.OpticonAccountBalanceSummaryCache;
import com.edgestackers.opticon.service.cycles.OpticonMarketCycleUpdateCache;
import com.edgestackers.opticon.service.metadata.RaceMetadataCache;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCache;
import com.edgestackers.opticon.service.order.OpticonOrderUpdateCache;
import com.edgestackers.opticon.service.pacman.PacmanRaceFlucsUpdateCache;
import com.edgestackers.opticon.service.run.OpticonRunSummaryCache;
import com.edgestackers.opticon.service.turbo.OpticonDividendPredictionUpdateCache;
import com.edgestackers.opticon.service.turbo.OpticonTheoUpdateCache;
import com.edgestackers.tote.hub.core.metadata.EsRaceMetadata;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StartupContextRequestHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartupContextRequestHandler.class);
    private static final ObjectMapper SERIALIZER = new ObjectMapper();
    private final RaceMetadataCache raceMetadataCache;
    private final OpticonRunSummaryCache runSummaryCache;
    private final OpticonMarketCycleUpdateCache opticonMarketCycleUpdateCache;
    private final OpticonAccountBalanceSummaryCache opticonAccountBalanceSummaryCache;
    private final OpticonDividendPredictionUpdateCache dividendPredictionUpdateCache;
    private final OpticonTheoUpdateCache theoUpdateCache;
    private final OpticonOrderUpdateCache orderUpdateCache;
    private final PacmanRaceFlucsUpdateCache pacmanRaceFlucsUpdateCache;
    private final OpticonStrategyContextSummaryCache opticonStrategyContextSummaryCache;

    public StartupContextRequestHandler(RaceMetadataCache raceMetadataCache,
                                        OpticonRunSummaryCache runSummaryCache,
                                        OpticonMarketCycleUpdateCache opticonMarketCycleUpdateCache,
                                        OpticonAccountBalanceSummaryCache opticonAccountBalanceSummaryCache,
                                        OpticonDividendPredictionUpdateCache dividendPredictionUpdateCache,
                                        OpticonTheoUpdateCache theoUpdateCache,
                                        OpticonOrderUpdateCache orderUpdateCache,
                                        PacmanRaceFlucsUpdateCache pacmanRaceFlucsUpdateCache,
                                        OpticonStrategyContextSummaryCache opticonStrategyContextSummaryCache)
    {
        this.raceMetadataCache = raceMetadataCache;
        this.runSummaryCache = runSummaryCache;
        this.opticonMarketCycleUpdateCache = opticonMarketCycleUpdateCache;
        this.opticonAccountBalanceSummaryCache = opticonAccountBalanceSummaryCache;
        this.dividendPredictionUpdateCache = dividendPredictionUpdateCache;
        this.theoUpdateCache = theoUpdateCache;
        this.orderUpdateCache = orderUpdateCache;
        this.pacmanRaceFlucsUpdateCache = pacmanRaceFlucsUpdateCache;
        this.opticonStrategyContextSummaryCache = opticonStrategyContextSummaryCache;
    }

    public void handle(Context context) {
        try {
            List<EsRaceMetadata> esRaceMetadata = raceMetadataCache.getAll();
            List<OpticonRunSummary> runSummaries = runSummaryCache.getAll();
            List<OpticonMarketCycleUpdate> cycleUpdates = opticonMarketCycleUpdateCache.getAll();
            List<OpticonAccountBalanceSummary> balanceSummaries = opticonAccountBalanceSummaryCache.getAll();
            List<OpticonDividendPredictionUpdate> dividendPredictionUpdates = dividendPredictionUpdateCache.getAll();
            List<OpticonTheoUpdate> theoUpdates = theoUpdateCache.getAll();
            List<OpticonOrderUpdate> orderUpdates = orderUpdateCache.getAll();
            List<PacmanRaceFlucsUpdate> pacmanRaceFlucsUpdates = pacmanRaceFlucsUpdateCache.getAll();
            List<OpticonStrategyContextSummary> exoticContextSummaries = opticonStrategyContextSummaryCache.getAll();
            OpticonInitContext opticonInitContext = new OpticonInitContext(
                    esRaceMetadata,
                    runSummaries,
                    cycleUpdates,
                    balanceSummaries,
                    dividendPredictionUpdates,
                    theoUpdates,
                    orderUpdates,
                    pacmanRaceFlucsUpdates,
                    exoticContextSummaries
            );
            String response = SERIALIZER.writeValueAsString(opticonInitContext);
            context.result(response);
        }
        catch (Exception e) {
            LOGGER.error("Failed to process start up context retrieval request due to {} - {}", e.getClass().getSimpleName(), e.getMessage());
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
