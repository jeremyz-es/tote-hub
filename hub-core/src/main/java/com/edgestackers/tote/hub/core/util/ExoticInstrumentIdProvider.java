package com.edgestackers.tote.hub.core.util;

import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;

public enum ExoticInstrumentIdProvider {
    ;

    public static String createExoticInstrumentId(RaceCode raceCode,
                                                  String date,
                                                  TotePoolJurisdiction totePoolJurisdiction,
                                                  ToteBetType toteBetType,
                                                  String track,
                                                  int raceNumber)
    {
        return String.format("%s|%s|%s|%s|%s|%s", raceCode, date, totePoolJurisdiction, toteBetType, track, raceNumber);
    }
}
