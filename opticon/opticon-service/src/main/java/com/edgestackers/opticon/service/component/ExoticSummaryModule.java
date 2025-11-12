package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.metadata.RaceMetadataCache;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCache;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCacheRefresher;
import com.edgestackers.opticon.service.parameters.ToteStrategyParametersCache;
import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.util.Set;

@Module
public class ExoticSummaryModule {
    private final Set<ToteBetType> betTypes = Set.of(ToteBetType.WIN, ToteBetType.PLACE, ToteBetType.QUINELLA, ToteBetType.EXACTA, ToteBetType.TRIFECTA, ToteBetType.FIRST_FOUR);
    private final Set<RaceType> raceTypes = Set.of(RaceType.GALLOPS);

    public ExoticSummaryModule() {
    }

    @Provides
    @Singleton
    public OpticonStrategyContextSummaryCacheRefresher opticonExoticContextSummaryCacheInitializer(OpticonStrategyContextSummaryCache cache,
                                                                                                   RaceMetadataCache raceMetadataCache,
                                                                                                   ToteStrategyParametersCache toteStrategyParametersCache) {
        return new OpticonStrategyContextSummaryCacheRefresher(
                cache,
                raceMetadataCache,
                toteStrategyParametersCache,
                betTypes,
                raceTypes
        );
    }
}
