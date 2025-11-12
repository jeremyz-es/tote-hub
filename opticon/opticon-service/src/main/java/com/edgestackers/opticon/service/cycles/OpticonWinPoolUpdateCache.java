package com.edgestackers.opticon.service.cycles;

import com.edgestackers.opticon.common.datamodel.OpticonWinPoolUpdate;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpticonWinPoolUpdateCache {
    private final Map<String, OpticonWinPoolUpdate> cache;

    public OpticonWinPoolUpdateCache() {
        this.cache = new HashMap<>();
    }

    public void put(OpticonWinPoolUpdate opticonWinPoolUpdate) {
        cache.put(opticonWinPoolUpdate.esRaceId(), opticonWinPoolUpdate);
    }

    @Nullable
    public OpticonWinPoolUpdate get(String esRaceId) {
        return cache.get(esRaceId);
    }

    public List<OpticonWinPoolUpdate> getAll() {
        return new ArrayList<>(cache.values());
    }
}
