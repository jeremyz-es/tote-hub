package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.metadata.MasterFieldsCache;
import com.edgestackers.opticon.service.run.*;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class RunSummaryModule {

    @Provides
    @Singleton
    public OpticonRunSummaryCache opticonRunSummaryCache() {
        return new OpticonRunSummaryCache();
    }

    @Provides
    @Singleton
    public OpticonRunSummaryFactory opticonRunSummaryFactory(OpticonRunSummaryCache runSummaryCache,
                                                             OpticonRunTheoCache runTheoCache,
                                                             OpticonWinMarketSummaryCache winMarketSummaryCache)
    {
        return new OpticonRunSummaryFactory(
                runSummaryCache,
                runTheoCache,
                winMarketSummaryCache
        );
    }

    @Provides
    @Singleton
    public OpticonRunSummaryProcessor opticonRunSummaryProcessor(OpticonRunSummaryCache runSummaryCache,
                                                                 OpticonMessageWebsocketPublisher messageWebsocketPublisher)
    {
        return new OpticonRunSummaryProcessor(runSummaryCache, messageWebsocketPublisher);
    }

    @Provides
    @Singleton
    public OpticonRunSummaryCacheRefresher opticonRunSummaryCacheRefresher(OpticonRunSummaryFactory runSummaryFactory,
                                                                           OpticonRunSummaryProcessor runSummaryProcessor,
                                                                           MasterFieldsCache masterFieldsCache) {
        return new OpticonRunSummaryCacheRefresher(
                runSummaryFactory,
                runSummaryProcessor,
                masterFieldsCache
        );
    }
}
