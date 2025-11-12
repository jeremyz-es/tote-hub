package com.edgestackers.opticon.service.turbo;

import com.edgestackers.opticon.common.datamodel.OpticonDividendPredictionUpdate;
import com.edgestackers.tote.hub.core.datamodel.message.turbo.ExoticDividendPredictionMessage;

import static com.edgestackers.tote.hub.core.datamodel.core.RaceCode.convertToRaceType;

public enum OpticonDividendPredictionUpdateKeyFactory {
    ;

    public static OpticonDividendPredictionUpdateKey createKey(OpticonDividendPredictionUpdate update) {
        return new OpticonDividendPredictionUpdateKey(
                update.provider(),
                update.jurisdiction(),
                update.raceType(),
                update.betType(),
                update.esRaceId()
        );
    }

    public static OpticonDividendPredictionUpdateKey createKey(ExoticDividendPredictionMessage message) {
        return new OpticonDividendPredictionUpdateKey(
                message.provider(),
                message.jurisdiction(),
                convertToRaceType(message.raceCode()),
                message.betType(),
                message.esRaceId()
        );
    }
}
