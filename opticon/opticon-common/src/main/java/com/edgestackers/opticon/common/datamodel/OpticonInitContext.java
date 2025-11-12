package com.edgestackers.opticon.common.datamodel;

import com.edgestackers.tote.hub.core.metadata.EsRaceMetadata;

import java.util.List;

public record OpticonInitContext(List<EsRaceMetadata> esRaceMetadatas,
                                 List<OpticonRunSummary> runSummaries,
                                 List<OpticonMarketCycleUpdate> cycleUpdates,
                                 List<OpticonAccountBalanceSummary> balanceSummaries,
                                 List<OpticonDividendPredictionUpdate> dividendPredictionUpdates,
                                 List<OpticonTheoUpdate> theoUpdates,
                                 List<OpticonOrderUpdate> orderUpdates,
                                 List<PacmanRaceFlucsUpdate> pacmanRaceFlucsUpdates,
                                 List<OpticonStrategyContextSummary> exoticContextSummaries) {
}
