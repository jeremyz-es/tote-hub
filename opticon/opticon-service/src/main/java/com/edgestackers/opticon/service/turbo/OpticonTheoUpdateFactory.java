package com.edgestackers.opticon.service.turbo;

import com.edgestackers.opticon.common.datamodel.ExoticCombinationSummary;
import com.edgestackers.opticon.common.datamodel.OpticonTheoUpdate;
import com.edgestackers.opticon.service.metadata.RaceMetadataCache;
import com.edgestackers.tote.hub.core.datamodel.message.turbo.ExoticTheoMessage;
import com.edgestackers.tote.hub.core.metadata.EsRaceMetadata;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.edgestackers.opticon.service.turbo.OpticonTheoUpdateKeyFactory.createKey;
import static com.edgestackers.tote.hub.core.datamodel.core.RaceCode.convertToRaceType;
import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;

public class OpticonTheoUpdateFactory {
    private static final boolean PARSE_COMBINATIONS = false;
    private static final String PROBABILITY_STRING_DELIMITER = ",";
    private final OpticonTheoUpdateCache updateCache;
    private final RaceMetadataCache raceMetadataCache;

    public OpticonTheoUpdateFactory(OpticonTheoUpdateCache updateCache,
                                    RaceMetadataCache raceMetadataCache)
    {
        this.updateCache = updateCache;
        this.raceMetadataCache = raceMetadataCache;
    }

    @Nullable
    public OpticonTheoUpdate createUpdate(ExoticTheoMessage message) {
        @Nullable EsRaceMetadata raceMetadata = raceMetadataCache.get(message.esRaceId());
        if (raceMetadata == null) return null;
        OpticonTheoUpdateKey key = createKey(message);
        @Nullable List<OpticonTheoUpdate> updates = updateCache.get(key);
        @Nullable Long lastDividendEpochNanos = (updates == null || updates.isEmpty()) ? null : updates.getLast().generatedAtEpochNanos();
        long nowEpochNanos = generateEpochNanosTimestamp();

        return new OpticonTheoUpdate(
                message.generatedAtEpochNanos(),
                convertToRaceType(raceMetadata.raceCode()),
                message.esRaceId(),
                raceMetadata.track(),
                raceMetadata.race(),
                message.betType(),
                convertExoticCombinations(message.probabilities()),
                message.exoticTheoId(),
                message.exoticTheoModel(),
                message.theoModelMajorVersion(),
                message.theoModelMinorVersion(),
                message.calculationTimeMillis(),
                lastDividendEpochNanos == null ? null : (nowEpochNanos - lastDividendEpochNanos) / 1_000_000_000
        );
    }

    private static List<ExoticCombinationSummary> convertExoticCombinations(Map<String, Double> probabilities) {
        if (!PARSE_COMBINATIONS) {
            return new ArrayList<>();
        }
        try {
            return probabilities.entrySet().stream().map(probEntry -> new ExoticCombinationSummary(Arrays.stream(probEntry.getKey().split(PROBABILITY_STRING_DELIMITER)).map(Integer::parseInt).toList(), probEntry.getValue(), 1 / probEntry.getValue())).toList();
        }
        catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
