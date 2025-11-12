package com.edgestackers.opticon.service.cycles;

import com.edgestackers.opticon.common.datamodel.OpticonMarketCycleUpdate;

import java.util.*;

import static com.edgestackers.opticon.service.cycles.OpticonMarketCycleUpdateKeyFactory.createKey;

public class OpticonMarketCycleUpdateCache {
    private static final int MAX_SIZE = 30;
    private final Map<OpticonMarketCycleUpdateKey, List<OpticonMarketCycleUpdate>> cache;

    public OpticonMarketCycleUpdateCache() {
        this.cache = new HashMap<>();
    }

    public void put(OpticonMarketCycleUpdate update) {
        OpticonMarketCycleUpdateKey key = createKey(update);
        cache.putIfAbsent(key, new ArrayList<>());
        List<OpticonMarketCycleUpdate> marketCycleUpdates = cache.get(key);
        marketCycleUpdates.add(update);
        while (marketCycleUpdates.size() > MAX_SIZE) {
            marketCycleUpdates.removeFirst();
        }
    }

    public List<OpticonMarketCycleUpdate> get(OpticonMarketCycleUpdateKey key) {
        return cache.getOrDefault(key, new ArrayList<>());
    }

    public List<OpticonMarketCycleUpdate> getAll() {
        return cache.values().stream().flatMap(Collection::stream).toList();
    }
}
