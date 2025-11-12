package com.edgestackers.opticon.service.metadata;

import com.edgestackers.tote.hub.core.metadata.EsRaceMetadata;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RaceMetadataCache {
    private final Map<String, EsRaceMetadata> cache;

    public RaceMetadataCache() {
        this.cache = new HashMap<>();
    }

    public void put(EsRaceMetadata raceMetadata) {
        cache.put(raceMetadata.esRaceId(), raceMetadata);
    }

    @Nullable
    public EsRaceMetadata get(String raceId) {
        return cache.get(raceId);
    }

    public List<EsRaceMetadata> getAll() {
        return new ArrayList<>(cache.values());
    }
}
