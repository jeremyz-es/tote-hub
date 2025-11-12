package com.edgestackers.opticon.service.control;

import com.edgestackers.opticon.common.datamodel.OpticonAccountBalanceSummary;
import com.edgestackers.opticon.common.datamodel.OpticonAccountBalanceSummaryKey;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpticonAccountBalanceSummaryCache {
    private final Map<OpticonAccountBalanceSummaryKey, OpticonAccountBalanceSummary> cache;

    public OpticonAccountBalanceSummaryCache() {
        this.cache = new HashMap<>();
    }

    public void put(OpticonAccountBalanceSummary summary) {
        cache.put(new OpticonAccountBalanceSummaryKey(summary.toteProvider(), summary.jurisdiction(), summary.toteAccountType()), summary);
    }

    public List<OpticonAccountBalanceSummary> getAll() {
        return new ArrayList<>(cache.values());
    }

    @Nullable
    public OpticonAccountBalanceSummary get(OpticonAccountBalanceSummaryKey key) {
        return cache.get(key);
    }
}
