package com.edgestackers.opticon.service.cycles;

import com.edgestackers.opticon.common.datamodel.OpticonMarketCycleUpdate;
import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import com.edgestackers.tote.hub.core.datamodel.message.ToteMarketCycleUpdate;

import static com.edgestackers.tote.hub.core.datamodel.core.RaceCode.convertToRaceType;

enum OpticonMarketCycleUpdateKeyFactory {
    ;

    static OpticonMarketCycleUpdateKey createKey(OpticonMarketCycleUpdate update) {
        return new OpticonMarketCycleUpdateKey(
                update.toteProvider(),
                update.totePoolJurisdiction(),
                update.raceType(),
                update.toteBetType(),
                update.esRaceId()
        );
    }

    static OpticonMarketCycleUpdateKey createKey(ToteMarketCycleUpdate update) {
        RaceType raceType = convertToRaceType(update.raceCode());
        return new OpticonMarketCycleUpdateKey(
                update.toteProvider(),
                update.totePoolJurisdiction(),
                raceType,
                update.toteBetType(),
                update.esRaceId()
        );
    }
}
