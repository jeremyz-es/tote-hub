package com.edgestackers.opticon.common.datamodel;

import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;

import java.util.Objects;

public record NitroEngineAgentKey(ToteBetType toteBetType,
                                  TotePoolJurisdiction jurisdiction,
                                  RaceType raceType,
                                  String esRaceId) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        NitroEngineAgentKey that = (NitroEngineAgentKey) object;
        return Objects.equals(esRaceId, that.esRaceId) && raceType == that.raceType && toteBetType == that.toteBetType && jurisdiction == that.jurisdiction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toteBetType, jurisdiction, raceType, esRaceId);
    }
}
