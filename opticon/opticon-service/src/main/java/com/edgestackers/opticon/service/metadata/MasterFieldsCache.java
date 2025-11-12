package com.edgestackers.opticon.service.metadata;

import com.edgestackers.tote.hub.core.metadata.MasterField;
import com.edgestackers.tote.hub.core.metadata.MasterFieldKey;
import com.edgestackers.tote.hub.core.metadata.MasterFieldRunnerIdKey;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterFieldsCache {
    private final Map<MasterFieldKey, MasterField> cache;
    private final Map<MasterFieldRunnerIdKey, MasterField> byRunnerId;

    public MasterFieldsCache() {
        this.cache = new HashMap<>();
        this.byRunnerId = new HashMap<>();
    }

    public void put(MasterField masterField) {
        cache.put(new MasterFieldKey(masterField.esRaceId(), masterField.tab()), masterField);
        byRunnerId.put(new MasterFieldRunnerIdKey(masterField.esRaceId(), masterField.esRunnerId()), masterField);
    }

    @Nullable
    public MasterField get(MasterFieldKey masterFieldKey) {
        return cache.get(masterFieldKey);
    }

    @Nullable
    public MasterField get(MasterFieldRunnerIdKey masterFieldRunnerIdKey) {
        return byRunnerId.get(masterFieldRunnerIdKey);
    }


    public List<MasterField> getAll() {
        return new ArrayList<>(cache.values());
    }
}
