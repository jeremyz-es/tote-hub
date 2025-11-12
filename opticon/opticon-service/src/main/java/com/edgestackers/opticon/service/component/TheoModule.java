package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.run.OpticonRunTheoCache;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class TheoModule {

    @Provides
    @Singleton
    public OpticonRunTheoCache runTheoCache() {
        return new OpticonRunTheoCache();
    }
}
