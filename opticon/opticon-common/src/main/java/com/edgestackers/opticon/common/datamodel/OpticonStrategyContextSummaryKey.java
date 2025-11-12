package com.edgestackers.opticon.common.datamodel;

import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;

import java.util.Objects;

public record OpticonStrategyContextSummaryKey(String strategyId,
                                               String esRaceId,
                                               ToteBetType toteBetType,
                                               TotePoolJurisdiction jurisdiction) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OpticonStrategyContextSummaryKey that = (OpticonStrategyContextSummaryKey) object;
        return Objects.equals(esRaceId, that.esRaceId) && Objects.equals(strategyId, that.strategyId) && toteBetType == that.toteBetType && jurisdiction == that.jurisdiction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strategyId, esRaceId, toteBetType, jurisdiction);
    }
}
