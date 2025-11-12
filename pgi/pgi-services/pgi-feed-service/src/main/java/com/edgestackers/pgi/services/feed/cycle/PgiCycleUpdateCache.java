package com.edgestackers.pgi.services.feed.cycle;

import com.edgestackers.pgi.common.datamodel.cycle.PgiCycleUpdate;
import com.edgestackers.pgi.common.datamodel.cycle.PgiCycleUpdateKey;

import java.util.*;

public class PgiCycleUpdateCache {
    private static final int MAX_SIZE = 30;
    private final Map<PgiCycleUpdateKey, List<PgiCycleUpdate>> cache;

    public PgiCycleUpdateCache() {
        this.cache = new HashMap<>();
    }

    public void put(PgiCycleUpdate update) {
        PgiCycleUpdateKey key = new PgiCycleUpdateKey(update.programName(), update.race(), update.pgiBetType());
        cache.computeIfAbsent(key, k -> new ArrayList<>());
        List<PgiCycleUpdate> updates = cache.get(key);
        updates.add(update);
        while (updates.size() > MAX_SIZE) {
            updates.removeFirst();
        }
    }

    public List<PgiCycleUpdate> getAll() {
        return cache.values().stream().flatMap(Collection::stream).toList();
    }
}
