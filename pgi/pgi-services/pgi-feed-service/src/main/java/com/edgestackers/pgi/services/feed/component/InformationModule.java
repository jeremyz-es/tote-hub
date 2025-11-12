package com.edgestackers.pgi.services.feed.component;

import com.amtote.gws.GwsSoap;
import com.edgestackers.pgi.common.client.GwsClientInfoProvider;
import com.edgestackers.pgi.services.feed.information.PgiInformationRefresher;
import com.edgestackers.pgi.services.feed.information.PgiProgramInformationCache;
import com.edgestackers.pgi.services.feed.information.PgiRaceInformationCache;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class InformationModule {

    @Provides
    @Singleton
    public PgiProgramInformationCache pgiProgramMetadataCache() {
        return new PgiProgramInformationCache();
    }


    @Provides
    @Singleton
    public PgiRaceInformationCache pgiRaceMetadataCache() {
        return new PgiRaceInformationCache();
    }

    @Provides
    @Singleton
    public PgiInformationRefresher pgiProgramMetadataRefresher(GwsSoap gwsSoap,
                                                               GwsClientInfoProvider gwsClientInfoProvider,
                                                               PgiProgramInformationCache programMetadataCache,
                                                               PgiRaceInformationCache raceMetadataCache) {
        return new PgiInformationRefresher(
                gwsSoap,
                gwsClientInfoProvider,
                programMetadataCache,
                raceMetadataCache
        );
    }
}
