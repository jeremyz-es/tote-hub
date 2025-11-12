package com.edgestackers.pgi.services.feed.metadata;

import com.edgestackers.pgi.common.datamodel.metadata.PgiRaceMetadata;
import com.edgestackers.pgi.services.feed.datamodel.PgiRaceInformationKey;
import jakarta.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class PgiRaceMetadataCache {
    private final Map<PgiRaceInformationKey, PgiRaceMetadata> byInfoKey;

    public PgiRaceMetadataCache() {
        byInfoKey = new HashMap<>();
    }

    public void put(PgiRaceMetadata pgiRaceMetadata) {
        byInfoKey.put(new PgiRaceInformationKey(pgiRaceMetadata.programName(), pgiRaceMetadata.raceNumber()), pgiRaceMetadata);
    }

    @Nullable
    public PgiRaceMetadata getBy(PgiRaceInformationKey key) {
        return byInfoKey.get(key);
    }
}
