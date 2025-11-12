package com.edgestackers.opticon.service.turbo;

import com.edgestackers.opticon.common.datamodel.OpticonDividendPredictionUpdate;

import java.util.*;

import static com.edgestackers.opticon.service.turbo.OpticonDividendPredictionUpdateKeyFactory.createKey;

public class OpticonDividendPredictionUpdateCache {

    private final Map<OpticonDividendPredictionUpdateKey, List<OpticonDividendPredictionUpdate>> cache;

    public OpticonDividendPredictionUpdateCache() {
        this.cache = new HashMap<>();
    }

    public void put(OpticonDividendPredictionUpdate update) {
        OpticonDividendPredictionUpdateKey key = createKey(update);
        cache.putIfAbsent(key, new ArrayList<>());
        List<OpticonDividendPredictionUpdate> updates = cache.get(key);
        updates.add(update);
    }

    public List<OpticonDividendPredictionUpdate> get(OpticonDividendPredictionUpdateKey updateKey) {
        return cache.get(updateKey);
    }

    public List<OpticonDividendPredictionUpdate> getAll() {
        return cache.values().stream().flatMap(Collection::stream).toList();
    }
}
