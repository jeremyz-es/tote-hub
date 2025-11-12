package com.edgestackers.opticon.service.operations;

import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummaryKey;
import com.edgestackers.opticon.service.metadata.RaceMetadataCache;
import com.edgestackers.opticon.service.parameters.ToteStrategyParametersCache;
import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.metadata.EsRaceMetadata;
import com.edgestackers.tote.hub.core.parameters.ToteStrategyParameters;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.edgestackers.core.datamodel.common.trading.RaceType.convertFrom;
import static com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryFactory.createDefaultOpticonExoticContextSummary;
import static com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryKeyFactory.createKey;
import static com.edgestackers.tote.hub.core.datamodel.core.RaceCode.convertFromRaceType;
import static com.edgestackers.tote.hub.core.datamodel.core.RaceCode.convertToRaceType;

public class OpticonStrategyContextSummaryCacheRefresher {
    private final OpticonStrategyContextSummaryCache cache;
    private final RaceMetadataCache raceMetadataCache;
    private final ToteStrategyParametersCache toteStrategyParametersCache;
    private final Set<ToteBetType> toteBetTypes;
    private final Set<RaceType> raceTypes;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public OpticonStrategyContextSummaryCacheRefresher(OpticonStrategyContextSummaryCache cache,
                                                       RaceMetadataCache raceMetadataCache,
                                                       ToteStrategyParametersCache toteStrategyParametersCache,
                                                       Set<ToteBetType> toteBetTypes,
                                                       Set<RaceType> raceTypes)
    {
        this.cache = cache;
        this.raceMetadataCache = raceMetadataCache;
        this.toteStrategyParametersCache = toteStrategyParametersCache;
        this.toteBetTypes = toteBetTypes;
        this.raceTypes = raceTypes;
    }

    public void start() {
        executorService.scheduleWithFixedDelay(this::createExoticContextSummaries, 1L, 10L, TimeUnit.SECONDS);
    }

    private void createExoticContextSummaries() {
        List<EsRaceMetadata> esRaceMetadata = raceMetadataCache.getAll();
        Collection<ToteStrategyParameters> toteStrategyParameters = toteStrategyParametersCache.getAll();
        for (EsRaceMetadata raceMetadata : esRaceMetadata) {
            if (raceMetadata.abandoned()) continue;
            if (!raceTypes.contains(convertToRaceType(raceMetadata.raceCode()))) continue;
            for (ToteStrategyParameters parameters : toteStrategyParameters) {
                createExoticContextSummariesFor(parameters, raceMetadata);
            }
        }
    }

    private void createExoticContextSummariesFor(ToteStrategyParameters strategyParameters, EsRaceMetadata raceMetadata) {
        if (!toteBetTypes.contains(strategyParameters.toteBetType())) return;
        OpticonStrategyContextSummaryKey key = createKey(strategyParameters, raceMetadata.esRaceId());
        if (cache.contains(key)) {
            return;
        }
        cache.put(createDefaultOpticonExoticContextSummary(
                raceMetadata.raceStartTimeUtcNanos(),
                strategyParameters,
                convertToRaceType(raceMetadata.raceCode()),
                raceMetadata.esRaceId(),
                raceMetadata.track(),
                raceMetadata.race())
        );
    }
}
