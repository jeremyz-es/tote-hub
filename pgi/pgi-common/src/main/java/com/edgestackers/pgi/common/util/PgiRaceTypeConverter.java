package com.edgestackers.pgi.common.util;

import com.edgestackers.pgi.common.datamodel.PgiRaceType;
import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;

public enum PgiRaceTypeConverter {
    ;

    public static RaceCode convertToRaceCode(PgiRaceType pgiRaceType) {
        return switch (pgiRaceType) {
            case THOROUGHBRED -> RaceCode.GALLOP;
            case DOG -> RaceCode.GREYHOUNDS;
            case HARNESS -> RaceCode.HARNESS;
        };
    }

    public static RaceCode convertToRaceType(PgiRaceType pgiRaceType) {
        return switch (pgiRaceType) {
            case THOROUGHBRED -> RaceCode.GALLOP;
            case DOG -> RaceCode.GREYHOUNDS;
            case HARNESS -> RaceCode.HARNESS;
        };
    }
}
