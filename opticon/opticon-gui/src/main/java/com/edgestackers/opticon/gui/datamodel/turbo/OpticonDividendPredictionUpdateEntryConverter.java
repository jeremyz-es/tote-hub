package com.edgestackers.opticon.gui.datamodel.turbo;

import com.edgestackers.opticon.common.datamodel.OpticonDividendPredictionUpdate;

import static com.edgestackers.tote.hub.core.util.TimeUtil.*;


public enum OpticonDividendPredictionUpdateEntryConverter {
    ;

    public static OpticonDividendPredictionUpdateEntry convertOpticonDividendPredictionUpdateToEntry(OpticonDividendPredictionUpdate update) {
        return new OpticonDividendPredictionUpdateEntry(
                update.generatedAtEpochNanos(),
                qldTimeFor(update.generatedAtEpochNanos(), HMS_FORMATTER),
                update.cyclePoolUpdateId(),
                update.raceType(),
                update.jurisdiction(),
                update.provider(),
                update.date(),
                update.esRaceId(),
                update.track(),
                update.race(),
                String.format("%.2f", update.marketPoolTotal()),
                String.format("%.2f", update.predictedPoolTotal()),
                update.betType(),
                update.exoticCombinationSummaries(),
                update.exoticDividendPredictionId(),
                update.dividendModel(),
                String.format("%.2f", update.calculationTimeMillis()),
                update.lastDividendDeltaSeconds()
        );
    }
}
