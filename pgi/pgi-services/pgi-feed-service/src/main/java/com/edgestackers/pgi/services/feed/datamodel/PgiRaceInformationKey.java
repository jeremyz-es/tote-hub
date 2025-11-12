package com.edgestackers.pgi.services.feed.datamodel;

import java.util.Objects;

public record PgiRaceInformationKey(String programName, int race) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PgiRaceInformationKey that = (PgiRaceInformationKey) object;
        return race == that.race && Objects.equals(programName, that.programName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programName, race);
    }
}
