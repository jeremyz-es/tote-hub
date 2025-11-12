package com.edgestackers.opticon.service.turbo;

import com.edgestackers.tote.hub.core.datamodel.core.*;

import java.util.Objects;

public record OpticonDividendPredictionUpdateKey(ToteProvider toteProvider,
                                                 TotePoolJurisdiction totePoolJurisdiction,
                                                 RaceType raceType,
                                                 ToteBetType toteBetType,
                                                 String esRaceId) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OpticonDividendPredictionUpdateKey that = (OpticonDividendPredictionUpdateKey) object;
        return Objects.equals(esRaceId, that.esRaceId) && raceType == that.raceType && toteBetType == that.toteBetType && toteProvider == that.toteProvider && totePoolJurisdiction == that.totePoolJurisdiction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toteProvider, totePoolJurisdiction, raceType, toteBetType, esRaceId);
    }
}
