package com.edgestackers.opticon.gui.datamodel.nitro;

import com.edgestackers.core.datamodel.common.trading.RaceType;
import com.edgestackers.core.datamodel.tote.ToteBetType;
import com.edgestackers.core.datamodel.tote.TotePoolJurisdiction;

public record NitroStatusSummaryKey(ToteBetType toteBetType,
                                    TotePoolJurisdiction jurisdiction,
                                    RaceType raceType,
                                    String track,
                                    int race) {
}
