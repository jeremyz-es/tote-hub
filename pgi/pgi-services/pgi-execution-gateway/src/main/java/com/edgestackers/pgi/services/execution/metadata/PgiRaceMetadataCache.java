package com.edgestackers.pgi.services.execution.metadata;

import com.edgestackers.pgi.common.datamodel.metadata.PgiRaceMetadata;
import jakarta.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class PgiRaceMetadataCache {
    private final Map<String, PgiRaceMetadata> cache;

    public PgiRaceMetadataCache() {
        this.cache = new HashMap<>();
    }

    public void put(PgiRaceMetadata pgiRaceMetadata) {
        cache.put(pgiRaceMetadata.esRaceId(), pgiRaceMetadata);
    }

    @Nullable
    public PgiRaceMetadata get(String esRaceId) {
        return cache.get(esRaceId);
    }
}
