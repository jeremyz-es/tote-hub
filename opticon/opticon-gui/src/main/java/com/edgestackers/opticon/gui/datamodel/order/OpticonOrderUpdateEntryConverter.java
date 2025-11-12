package com.edgestackers.opticon.gui.datamodel.order;

import com.edgestackers.opticon.common.datamodel.OpticonBetUpdate;
import com.edgestackers.opticon.common.datamodel.OpticonOrderUpdate;

import java.util.ArrayList;
import java.util.List;

import static com.edgestackers.core.util.TimeUtil.HMS_FORMATTER;
import static com.edgestackers.core.util.TimeUtil.qldTimeFor;

public enum OpticonOrderUpdateEntryConverter {
    ;

    public static OpticonOrderUpdateEntry convertOpticonOrderUpdateToEntry(OpticonOrderUpdate update) {
        return new OpticonOrderUpdateEntry(
                update.orderGenerationEpochMillisTimestamp(),
                update.clientOrderId(),
                update.strategyId(),
                update.strategyName(),
                update.jurisdiction(),
                update.betType(),
                update.esRaceId(),
                update.toteProvider(),
                update.track(),
                update.race(),
                convertOpticonBetUpdatesToEntries(update.betUpdates(), update.predictedTotalPoolSize()),
                String.format("%.2f", update.marketTotalPoolSize()),
                String.format("%.2f", update.predictedTotalPoolSize()),
                String.format("%.2f", update.totalOrderAmount()),
                String.format("%.2f", update.totalAcceptedOrderAmount()),
                qldTimeFor(update.orderGenerationEpochMillisTimestamp() * 1_000_000, HMS_FORMATTER),
                update.raceEventTrigger(),
                update.exoticTheoId(),
                update.exoticDividendPredictionId(),
                update.opticonOrderStatus()
        );
    }

    private static List<OpticonBetUpdateEntry> convertOpticonBetUpdatesToEntries(List<OpticonBetUpdate> betUpdates, double poolTotal) {
        List<OpticonBetUpdateEntry> entries = new ArrayList<>();
        betUpdates.forEach(betUpdate -> entries.add(convertOpticonBetUpdateToEntry(betUpdate, poolTotal)));
        return entries;
    }

    private static OpticonBetUpdateEntry convertOpticonBetUpdateToEntry(OpticonBetUpdate update, double poolTotal) {
        return new OpticonBetUpdateEntry(
                String.join(", ", update.selections().stream().map(String::valueOf).toList()),
                String.format("%.2f", poolTotal / update.predictedMarketPrice()),
                String.format("%.2f", update.betAmount()),
                String.format("%.6f", update.predictedMarketProb()),
                String.format("%.2f", update.predictedMarketPrice()),
                String.format("%.6f", update.theoProb()),
                String.format("%.2f", update.theoPrice()),
                update.clientBetId(),
                update.opticonBetStatus()
        );
    }
}
