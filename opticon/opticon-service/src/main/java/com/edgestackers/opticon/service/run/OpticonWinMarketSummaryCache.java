package com.edgestackers.opticon.service.run;

import com.edgestackers.opticon.common.datamodel.OpticonWinMarketSummary;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import jakarta.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class OpticonWinMarketSummaryCache {
    private final Map<OpticonRunSummaryKey, Map<TotePoolJurisdiction, OpticonWinMarketSummary>> cache;

    public OpticonWinMarketSummaryCache() {
        this.cache = new HashMap<>();
    }

    public void put(OpticonRunSummaryKey key, TotePoolJurisdiction jurisdiction, OpticonWinMarketSummary summary) {
        cache.putIfAbsent(key, new HashMap<>());
        cache.get(key).put(jurisdiction, summary);
    }

    @Nullable
    public Map<TotePoolJurisdiction, OpticonWinMarketSummary> get(OpticonRunSummaryKey key) {
        return cache.get(key);
    }
}
