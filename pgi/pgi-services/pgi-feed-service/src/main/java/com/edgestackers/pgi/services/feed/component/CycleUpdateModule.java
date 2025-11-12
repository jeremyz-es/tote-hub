package com.edgestackers.pgi.services.feed.component;

import com.edgestackers.pgi.services.feed.cycle.PgiCycleUpdateCache;
import com.edgestackers.pgi.services.feed.cycle.PgiCycleUpdateProcessor;
import com.edgestackers.pgi.services.feed.cycle.tote.ToteMarketCycleUpdateProcessor;
import com.edgestackers.pgi.services.feed.cycle.tote.ToteWinMarketCycleUpdateProcessor;
import com.edgestackers.pgi.services.feed.metadata.PgiRaceMetadataCache;
import com.edgestackers.pgi.services.feed.publisher.PgiMessageNatsPublisher;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class CycleUpdateModule {

    @Provides
    @Singleton
    public PgiCycleUpdateCache pgiCycleUpdateCache() {
        return new PgiCycleUpdateCache();
    }

    @Provides
    @Singleton
    public ToteMarketCycleUpdateProcessor toteMarketCycleUpdateProcessor(PgiMessageNatsPublisher pgiMessageNatsPublisher) {
        return new ToteMarketCycleUpdateProcessor(pgiMessageNatsPublisher);
    }

    @Provides
    @Singleton
    public ToteWinMarketCycleUpdateProcessor toteWinMarketCycleUpdateProcessor(PgiMessageNatsPublisher pgiMessageNatsPublisher) {
        return new ToteWinMarketCycleUpdateProcessor(pgiMessageNatsPublisher);
    }

    @Provides
    @Singleton
    public PgiCycleUpdateProcessor pgiCycleUpdateProcessor(PgiCycleUpdateCache pgiCycleUpdateCache,
                                                           PgiRaceMetadataCache pgiRaceMetadataCache,
                                                           PgiMessageNatsPublisher pgiMessageNatsPublisher,
                                                           ToteMarketCycleUpdateProcessor toteMarketCycleUpdateProcessor,
                                                           ToteWinMarketCycleUpdateProcessor toteWinMarketCycleUpdateProcessor) {
        return new PgiCycleUpdateProcessor(
                pgiCycleUpdateCache,
                pgiRaceMetadataCache,
                pgiMessageNatsPublisher,
                toteMarketCycleUpdateProcessor,
                toteWinMarketCycleUpdateProcessor
        );
    }
}
