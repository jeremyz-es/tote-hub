package com.edgestackers.opticon.gui.datamodel.filter;

import com.edgestackers.tote.hub.core.datamodel.core.RaceType;

import java.util.Objects;

public record RaceViewFilter(RaceType raceType, String track, int race) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        RaceViewFilter that = (RaceViewFilter) object;
        return race == that.race && Objects.equals(track, that.track) && raceType == that.raceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(raceType, track, race);
    }
}
