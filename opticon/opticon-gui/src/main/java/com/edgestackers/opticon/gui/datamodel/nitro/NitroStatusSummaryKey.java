package com.edgestackers.opticon.gui.datamodel.nitro;

import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;

public record NitroStatusSummaryKey(ToteBetType toteBetType,
                                    TotePoolJurisdiction jurisdiction,
                                    RaceType raceType,
                                    String track,
                                    int race) {
}
