package com.edgestackers.opticon.service.cycles;


import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;

public record OpticonMarketCycleUpdateKey(ToteProvider toteProvider,
                                          TotePoolJurisdiction totePoolJurisdiction,
                                          RaceType raceType,
                                          ToteBetType toteBetType,
                                          String esRaceId) {


}
