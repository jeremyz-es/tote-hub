package com.edgestackers.opticon.common.datamodel;

import com.edgestackers.opticon.common.message.OpticonMessage;
import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;

public record OpticonWinPoolUpdate(ToteProvider toteProvider,
                                   RaceType raceType,
                                   TotePoolJurisdiction totePoolJurisdiction,
                                   String esRaceId,
                                   String track,
                                   int race,
                                   double winPoolSize) implements OpticonMessage {
}
