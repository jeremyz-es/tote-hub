package com.edgestackers.tote.hub.core.datamodel.official;


import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;

import java.util.List;

public record ToteRaceOfficial(String esRaceId,
                               String track,
                               int race,
                               ToteBetType toteBetType,
                               TotePoolJurisdiction totePoolJurisdiction,
                               List<Integer> winningSelection,
                               double payout) {
}
