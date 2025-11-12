package com.edgestackers.opticon.service.run;

import com.edgestackers.opticon.common.datamodel.OpticonRunSummary;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpticonRunSummaryCache {
    private final Map<OpticonRunSummaryKey, OpticonRunSummary> cache;

    public OpticonRunSummaryCache() {
        this.cache = new HashMap<>();
    }

    public void put(OpticonRunSummary opticonRunSummary) {
        OpticonRunSummaryKey key = new OpticonRunSummaryKey(opticonRunSummary.getEsRaceId(), opticonRunSummary.getTab());
        cache.put(key, opticonRunSummary);
    }

    @Nullable
    public OpticonRunSummary get(OpticonRunSummaryKey key) {
        return cache.get(key);
    }

    public boolean contains(OpticonRunSummaryKey key) {
        return cache.containsKey(key);
    }

    public List<OpticonRunSummary> getAll() {
        return new ArrayList<>(cache.values());
    }
}
