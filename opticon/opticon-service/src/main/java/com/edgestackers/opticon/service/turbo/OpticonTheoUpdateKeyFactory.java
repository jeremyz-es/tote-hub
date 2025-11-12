package com.edgestackers.opticon.service.turbo;

import com.edgestackers.opticon.common.datamodel.OpticonTheoUpdate;
import com.edgestackers.tote.hub.core.datamodel.message.turbo.ExoticTheoMessage;

public enum OpticonTheoUpdateKeyFactory {
    ;

    public static OpticonTheoUpdateKey createKey(OpticonTheoUpdate update) {
        return new OpticonTheoUpdateKey(
                update.toteBetType(),
                update.esRaceId()
        );
    }

    public static OpticonTheoUpdateKey createKey(ExoticTheoMessage message) {
        return new OpticonTheoUpdateKey(
                message.betType(),
                message.esRaceId()
        );
    }
}
