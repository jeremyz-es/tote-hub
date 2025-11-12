package com.edgestackers.pgi.services.execution.component;

import com.edgestackers.pgi.common.metadata.PgiMetadataApiClient;
import com.edgestackers.pgi.services.execution.metadata.PgiMetadataService;
import com.edgestackers.pgi.services.execution.metadata.PgiRaceMetadataCache;
import com.edgestackers.pgi.services.execution.metadata.PgiRaceMetadataCacheRefresher;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class MetadataModule {
    private final String esApiBaseUrl;

    public MetadataModule(String esApiBaseUrl) {
        this.esApiBaseUrl = esApiBaseUrl;
    }

    @Provides
    @Singleton
    public PgiRaceMetadataCache pgiRaceMetadataCache() {
        return new PgiRaceMetadataCache();
    }

    @Provides
    @Singleton
    public PgiMetadataApiClient pgiMetadataApiClient() {
        return new PgiMetadataApiClient(esApiBaseUrl);
    }

    @Provides
    @Singleton
    public PgiRaceMetadataCacheRefresher pgiRaceMetadataCacheRefresher(PgiRaceMetadataCache pgiRaceMetadataCache,
                                                                       PgiMetadataApiClient pgiMetadataApiClient) {
        return new PgiRaceMetadataCacheRefresher(pgiRaceMetadataCache, pgiMetadataApiClient);
    }

    @Provides
    @Singleton
    public PgiMetadataService pgiMetadataService(PgiRaceMetadataCacheRefresher pgiRaceMetadataCacheRefresher) {
        return new PgiMetadataService(pgiRaceMetadataCacheRefresher);
    }
}
