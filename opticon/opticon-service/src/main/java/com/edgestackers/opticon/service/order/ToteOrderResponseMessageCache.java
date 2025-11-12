package com.edgestackers.opticon.service.order;

import com.edgestackers.tote.hub.core.datamodel.message.order.ToteOrderResponseMessage;
import jakarta.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ToteOrderResponseMessageCache {
    private final Map<String, ToteOrderResponseMessage> cache;

    public ToteOrderResponseMessageCache() {
        this.cache = new HashMap<>();
    }

    public void put(ToteOrderResponseMessage message) {
        cache.put(message.clientOrderId(), message);
    }

    @Nullable
    public ToteOrderResponseMessage get(String clientOrderId) {
        return cache.get(clientOrderId);
    }
}
