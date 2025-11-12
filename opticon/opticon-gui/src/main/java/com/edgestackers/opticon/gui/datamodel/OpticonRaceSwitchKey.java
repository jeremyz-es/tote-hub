package com.edgestackers.opticon.gui.datamodel;


import com.edgestackers.tote.hub.core.datamodel.core.RaceType;

import java.util.Objects;

public record OpticonRaceSwitchKey(RaceType raceType, String track, int race) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OpticonRaceSwitchKey that = (OpticonRaceSwitchKey) object;
        return race == that.race && Objects.equals(track, that.track) && raceType == that.raceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(raceType, track, race);
    }
}
