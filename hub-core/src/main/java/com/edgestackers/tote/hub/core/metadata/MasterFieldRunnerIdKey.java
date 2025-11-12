package com.edgestackers.tote.hub.core.metadata;

import java.util.Objects;

public record MasterFieldRunnerIdKey(String esRaceId, String esRunnerId) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MasterFieldRunnerIdKey that = (MasterFieldRunnerIdKey) object;
        return Objects.equals(esRaceId, that.esRaceId) && Objects.equals(esRunnerId, that.esRunnerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(esRaceId, esRunnerId);
    }
}
