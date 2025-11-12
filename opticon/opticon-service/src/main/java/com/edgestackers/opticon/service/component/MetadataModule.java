package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.metadata.*;
import com.edgestackers.tote.hub.core.metadata.MasterFieldsApiClient;
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
    public MasterFieldsApiClient masterFieldsApiClient() {
        return new MasterFieldsApiClient(esApiBaseUrl);
    }

    @Provides
    @Singleton
    public MasterFieldsCache masterFieldsCache() {
        return new MasterFieldsCache();
    }

    @Provides
    @Singleton
    public MasterFieldsCacheRefresher masterFieldsCacheRefresher(MasterFieldsCache masterFieldsCache,
                                                                 MasterFieldsApiClient masterFieldsApiClient)
    {
        return new MasterFieldsCacheRefresher(masterFieldsCache, masterFieldsApiClient);
    }

    @Provides
    @Singleton
    public RaceMetadataApiClient raceMetadataApiClient() {
        return new RaceMetadataApiClient(esApiBaseUrl);
    }

    @Provides
    @Singleton
    public RaceMetadataCacheRefresher raceMetadataCacheRefresher(RaceMetadataCache raceMetadataCache,
                                                                 RaceMetadataApiClient raceMetadataApiClient) {
        return new RaceMetadataCacheRefresher(raceMetadataCache, raceMetadataApiClient);
    }

    @Provides
    @Singleton
    public RaceMetadataCache raceMetadataCache() {
        return new RaceMetadataCache();
    }
}
