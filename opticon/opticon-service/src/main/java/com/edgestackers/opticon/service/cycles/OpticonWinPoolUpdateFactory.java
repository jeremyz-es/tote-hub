package com.edgestackers.opticon.service.cycles;

import com.edgestackers.opticon.common.datamodel.OpticonWinPoolUpdate;
import com.edgestackers.tote.hub.core.datamodel.message.ToteWinMarketCycleUpdate;

import static com.edgestackers.tote.hub.core.datamodel.core.RaceCode.convertToRaceType;

public enum OpticonWinPoolUpdateFactory {
    ;

    public static OpticonWinPoolUpdate createOpticonWinPoolUpdate(ToteWinMarketCycleUpdate toteWinMarketCycleUpdate) {
        return new OpticonWinPoolUpdate(
                toteWinMarketCycleUpdate.toteProvider(),
                convertToRaceType(toteWinMarketCycleUpdate.raceCode()),
                toteWinMarketCycleUpdate.totePoolJurisdiction(),
                toteWinMarketCycleUpdate.esRaceId(),
                toteWinMarketCycleUpdate.track(),
                toteWinMarketCycleUpdate.race(),
                toteWinMarketCycleUpdate.poolTotal()
        );
    }
}
