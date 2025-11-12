package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCache;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class OperationsModule {

    @Provides
    @Singleton
    public OpticonStrategyContextSummaryCache opticonExoticContextSummaryCache() {
        return new OpticonStrategyContextSummaryCache();
    }
}
