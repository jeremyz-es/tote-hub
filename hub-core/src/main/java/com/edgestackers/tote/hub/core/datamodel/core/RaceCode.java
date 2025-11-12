package com.edgestackers.tote.hub.core.datamodel.core;

public enum RaceCode {
    GALLOP,
    GREYHOUNDS,
    HARNESS,
    ;

    public static RaceType convertToRaceType(RaceCode raceCode) {
        return switch (raceCode) {
            case GALLOP     -> RaceType.GALLOPS;
            case HARNESS    -> RaceType.HARNESS;
            case GREYHOUNDS -> RaceType.GREYHOUNDS;
        };
    }

    public static RaceCode convertFromRaceType(RaceType raceType) {
        return switch (raceType) {
            case GALLOPS    -> GALLOP;
            case GREYHOUNDS -> GREYHOUNDS;
            case HARNESS    -> HARNESS;
        };
    }
}
