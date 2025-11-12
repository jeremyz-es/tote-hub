package com.edgestackers.pgi.common.datamodel;

import jakarta.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public enum PgiRaceType {
    THOROUGHBRED("Thoroughbred"),
    DOG("Dog"),
    HARNESS("Harness"),
    ;

    private static final Map<String, PgiRaceType> API_MAPPINGS = getApiMappings();

    PgiRaceType(String apiName) {
        this.apiName = apiName;
    }

    @Nullable
    public static PgiRaceType getApiMapping(String apiName) {
        return API_MAPPINGS.get(apiName);
    }

    public String getApiName() { return apiName; }

    private final String apiName;

    private static Map<String, PgiRaceType> getApiMappings() {
        Map<String, PgiRaceType> map = new HashMap<>();
        for (PgiRaceType type : PgiRaceType.values()) {
            map.put(type.getApiName(), type);
        }
        return map;
    }
}
