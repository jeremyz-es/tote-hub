package com.edgestackers.tote.hub.core.metadata;

import java.util.Objects;

public record MasterFieldKey(String esRaceId, int tab) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MasterFieldKey that = (MasterFieldKey) object;
        return tab == that.tab && Objects.equals(esRaceId, that.esRaceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(esRaceId, tab);
    }
}
