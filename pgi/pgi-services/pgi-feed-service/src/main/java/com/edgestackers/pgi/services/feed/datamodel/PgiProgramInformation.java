package com.edgestackers.pgi.services.feed.datamodel;

import com.edgestackers.pgi.common.datamodel.PgiCountryCode;
import com.edgestackers.pgi.common.datamodel.PgiRaceType;

public record PgiProgramInformation(String programName,
                                    String programLongName,
                                    String programDateYmd,
                                    PgiRaceType pgiRaceType,
                                    PgiCountryCode pgiCountryCode) {
}
