package com.edgestackers.pgi.services.feed.information;

import com.edgestackers.pgi.services.feed.datamodel.PgiProgramInformation;
import com.edgestackers.pgi.services.feed.datamodel.PgiProgramInformationKey;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PgiProgramInformationCache {
    private final Map<PgiProgramInformationKey, PgiProgramInformation> cache;

    public PgiProgramInformationCache() {
        this.cache = new HashMap<>();
    }

    public void put(PgiProgramInformation metadata) {
        cache.put(new PgiProgramInformationKey(metadata.programName(), metadata.programDateYmd()), metadata);
    }

    @Nullable
    public PgiProgramInformation get(PgiProgramInformationKey key) {
        return cache.get(key);
    }

    public List<PgiProgramInformation> getAll() {
        return new ArrayList<>(cache.values());
    }
}
