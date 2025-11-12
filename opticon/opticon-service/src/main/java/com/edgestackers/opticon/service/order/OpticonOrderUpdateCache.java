package com.edgestackers.opticon.service.order;

import com.edgestackers.opticon.common.datamodel.OpticonOrderUpdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpticonOrderUpdateCache {
    private final Map<String, OpticonOrderUpdate> cache;

    public OpticonOrderUpdateCache() {
        this.cache = new HashMap<>();
    }

    public void put(OpticonOrderUpdate update) {
        cache.put(update.clientOrderId(), update);
    }

    public List<OpticonOrderUpdate> getAll() {
        return new ArrayList<>(cache.values());
    }
}
