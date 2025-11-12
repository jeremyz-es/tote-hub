package com.edgestackers.pgi.services.feed.datamodel;

import com.edgestackers.pgi.common.datamodel.PgiBetType;
import com.edgestackers.pgi.common.datamodel.PgiCountryCode;
import com.edgestackers.pgi.common.datamodel.PgiRaceType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public record PgiRaceInformation(String programName,
                                 String programLongName,
                                 String programDateYmd,
                                 PgiRaceType pgiRaceType,
                                 PgiCountryCode pgiCountryCode,
                                 int race,
                                 List<Integer> liveRunners,
                                 List<Integer> scratchedRunners,
                                 List<PgiBetType> openBetTypes) {

    @JsonIgnore
    public String getDescription() {
        return String.format("Program Name (Short/Long): [%s/%s] (%s) | %s | %s | Race: %s", programName, programLongName, programDateYmd, pgiRaceType, pgiCountryCode, race);
    }
}
