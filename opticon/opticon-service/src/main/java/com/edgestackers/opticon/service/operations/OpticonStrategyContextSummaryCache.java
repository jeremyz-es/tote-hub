package com.edgestackers.opticon.service.operations;

import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummary;
import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummaryKey;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryKeyFactory.createKey;

public class OpticonStrategyContextSummaryCache {
    private final Map<OpticonStrategyContextSummaryKey, OpticonStrategyContextSummary> cache;

    public OpticonStrategyContextSummaryCache() {
        this.cache = new HashMap<>();
    }

    public void put(OpticonStrategyContextSummary summary) {
        cache.put(createKey(summary), summary);
    }

    @Nullable
    public OpticonStrategyContextSummary get(OpticonStrategyContextSummaryKey key) {
        return cache.get(key);
    }

    public List<OpticonStrategyContextSummary> getAll() {
        return new ArrayList<>(cache.values());
    }

    public boolean contains(OpticonStrategyContextSummaryKey key) {
        return cache.containsKey(key);
    }
}
