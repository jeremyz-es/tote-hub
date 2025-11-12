package com.edgestackers.opticon.service.turbo;

import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;

import java.util.Objects;

public record OpticonTheoUpdateKey(ToteBetType toteBetType, String esRaceId) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OpticonTheoUpdateKey that = (OpticonTheoUpdateKey) object;
        return Objects.equals(esRaceId, that.esRaceId) && toteBetType == that.toteBetType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toteBetType, esRaceId);
    }
}
