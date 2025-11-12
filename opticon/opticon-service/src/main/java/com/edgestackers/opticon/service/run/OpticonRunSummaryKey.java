package com.edgestackers.opticon.service.run;

import java.util.Objects;

public record OpticonRunSummaryKey(String esRaceId, int tab) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OpticonRunSummaryKey key = (OpticonRunSummaryKey) object;
        return tab == key.tab && Objects.equals(esRaceId, key.esRaceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(esRaceId, tab);
    }
}
