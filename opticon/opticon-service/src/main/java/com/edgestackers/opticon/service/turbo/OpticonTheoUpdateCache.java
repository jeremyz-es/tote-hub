package com.edgestackers.opticon.service.turbo;

import com.edgestackers.opticon.common.datamodel.OpticonTheoUpdate;
import jakarta.annotation.Nullable;

import java.util.*;

import static com.edgestackers.opticon.service.turbo.OpticonTheoUpdateKeyFactory.createKey;

public class OpticonTheoUpdateCache {
    private final Map<OpticonTheoUpdateKey, List<OpticonTheoUpdate>> cache;

    public OpticonTheoUpdateCache() {
        this.cache = new HashMap<>();
    }

    public void put(OpticonTheoUpdate update) {
        OpticonTheoUpdateKey key = createKey(update);
        cache.putIfAbsent(key, new ArrayList<>());
        List<OpticonTheoUpdate> updates = cache.get(key);
        updates.add(update);
    }

    @Nullable
    public List<OpticonTheoUpdate> get(OpticonTheoUpdateKey updateKey) {
        return cache.get(updateKey);
    }

    public List<OpticonTheoUpdate> getAll() {
        return cache.values().stream().flatMap(Collection::stream).toList();
    }
}
