package com.edgestackers.opticon.service.turbo;

import com.edgestackers.opticon.common.datamodel.ExoticCombinationSummary;
import com.edgestackers.opticon.common.datamodel.OpticonDividendPredictionUpdate;
import com.edgestackers.opticon.service.metadata.RaceMetadataCache;
import com.edgestackers.tote.hub.core.datamodel.message.turbo.ExoticDividendPredictionMessage;
import com.edgestackers.tote.hub.core.metadata.EsRaceMetadata;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.edgestackers.opticon.service.turbo.OpticonDividendPredictionUpdateKeyFactory.createKey;
import static com.edgestackers.tote.hub.core.datamodel.core.RaceCode.convertToRaceType;
import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;

public class OpticonDividendPredictionUpdateFactory {
    private static final boolean PARSE_COMBINATIONS = false;
    private static final String PROBABILITY_STRING_DELIMITER = ",";
    private final OpticonDividendPredictionUpdateCache updateCache;
    private final RaceMetadataCache raceMetadataCache;

    public OpticonDividendPredictionUpdateFactory(OpticonDividendPredictionUpdateCache updateCache,
                                                  RaceMetadataCache raceMetadataCache)
    {
        this.updateCache = updateCache;
        this.raceMetadataCache = raceMetadataCache;
    }

    @Nullable
    public OpticonDividendPredictionUpdate createUpdate(ExoticDividendPredictionMessage message) {
        @Nullable EsRaceMetadata raceMetadata = raceMetadataCache.get(message.esRaceId());
        if (raceMetadata == null) return null;
        OpticonDividendPredictionUpdateKey key = createKey(message);
        @Nullable List<OpticonDividendPredictionUpdate> updates = updateCache.get(key);
        @Nullable Long lastDividendEpochNanos = updates == null  || updates.isEmpty() ? null : updates.getLast().generatedAtEpochNanos();
        long nowEpochNanos = generateEpochNanosTimestamp();

        return new OpticonDividendPredictionUpdate(
                message.generatedAtEpochNanos(),
                message.cyclePoolUpdateId(),
                convertToRaceType(message.raceCode()),
                message.jurisdiction(),
                message.provider(),
                message.date(),
                message.esTrackId(),
                message.esRaceId(),
                raceMetadata.track(),
                raceMetadata.race(),
                message.marketPoolTotal(),
                message.predictedPoolTotal(),
                convertExoticCombinations(message.predictedProbabilities()),
                message.betType(),
                message.exoticDividendPredictionId(),
                message.dividendModel(),
                message.dividendModelMajorVersion(),
                message.dividendModelMinorVersion(),
                message.pricingModelSource(),
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
