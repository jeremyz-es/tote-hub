package com.edgestackers.pgi.common.datamodel.cycle;

import com.edgestackers.pgi.common.datamodel.PgiBetType;
import com.edgestackers.pgi.common.datamodel.PgiCountryCode;
import com.edgestackers.pgi.common.datamodel.PgiRaceType;
import com.edgestackers.pgi.common.datamodel.message.PgiFeedServiceMessage;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.cycle.SelectionPool;
import com.edgestackers.tote.hub.core.datamodel.cycle.ToteCycleType;

import java.util.List;

public record PgiCycleUpdate(String feedServiceCycleUpdateId,
                             String feedServiceCyclePoolUpdateId,
                             long receivedAtEpochNanos,
                             String programName,
                             String programLongName,
                             String programDateYmd,
                             PgiRaceType pgiRaceType,
                             PgiCountryCode pgiCountryCode,
                             int race,
                             PgiBetType pgiBetType,
                             double poolTotal,
                             ToteCycleType toteCycleType,
                             List<String> rawMoneys,
                             TotePoolJurisdiction totePoolJurisdiction,
                             List<SelectionPool> selectionPools) implements PgiFeedServiceMessage {
}
