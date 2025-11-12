package com.edgestackers.pgi.services.feed.information;

import com.edgestackers.pgi.services.feed.datamodel.PgiRaceInformation;
import com.edgestackers.pgi.services.feed.datamodel.PgiRaceInformationKey;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PgiRaceInformationCache {
    private final Map<PgiRaceInformationKey, PgiRaceInformation> cache;

    public PgiRaceInformationCache() {
        this.cache = new HashMap<>();
    }

    public void put(PgiRaceInformation metadata) {
        cache.put(new PgiRaceInformationKey(metadata.programName(), metadata.race()), metadata);
    }

    @Nullable
    public PgiRaceInformation get(PgiRaceInformationKey key) {
        return cache.get(key);
    }

    public List<PgiRaceInformation> getAll() {
        return new ArrayList<>(cache.values());
    }
}
