package com.edgestackers.opticon.service.order;

import com.edgestackers.tote.hub.core.datamodel.message.nitro.NitroOrderExecutionMessage;
import jakarta.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class NitroOrderExecutionMessageCache {
    private final Map<String, NitroOrderExecutionMessage> cache;

    public NitroOrderExecutionMessageCache() {
        this.cache = new HashMap<>();
    }

    public void put(NitroOrderExecutionMessage message) {
        cache.put(message.clientOrderId(), message);
    }

    @Nullable
    public NitroOrderExecutionMessage get(String clientOrderId) {
        return cache.get(clientOrderId);
    }
}
