package com.edgestackers.opticon.service.run;

import jakarta.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class OpticonRunTheoCache {
    private final Map<OpticonRunSummaryKey, Map<String, Double>> theos;

    public OpticonRunTheoCache() {
        theos = new HashMap<>();
    }

    public void put(OpticonRunSummaryKey runSummaryKey, String identifierKey, double theo) {
        theos.putIfAbsent(runSummaryKey, new HashMap<>());
        theos.get(runSummaryKey).put(identifierKey, theo);
    }

    @Nullable
    public Map<String, Double> get(OpticonRunSummaryKey key) {
        return theos.get(key);
    }
}
