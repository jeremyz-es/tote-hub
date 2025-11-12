package com.edgestackers.pgi.services.feed.component;

import com.edgestackers.pgi.common.metadata.PgiMetadataApiClient;
import com.edgestackers.pgi.services.feed.metadata.PgiRaceMetadataCache;
import com.edgestackers.pgi.services.feed.metadata.PgiRaceMetadataCacheRefresher;
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
    public PgiRaceMetadataCacheRefresher pgiRaceMetadataCacheRefresher(PgiRaceMetadataCache pgiRaceMetadataCache,
                                                                       PgiMetadataApiClient pgiMetadataApiClient) {
        return new PgiRaceMetadataCacheRefresher(pgiMetadataApiClient, pgiRaceMetadataCache);
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
}
