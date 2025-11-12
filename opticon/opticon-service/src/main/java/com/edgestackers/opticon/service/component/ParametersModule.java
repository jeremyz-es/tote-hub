package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.parameters.ToteStrategyParametersCache;
import com.edgestackers.opticon.service.parameters.ToteStrategyParametersCacheRefresher;
import com.edgestackers.tote.hub.core.parameters.CaesarApiClient;
import com.edgestackers.tote.hub.core.parameters.CaesarApiProvider;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ParametersModule {
    private final String caesarBridgeBaseEndpoint;

    public ParametersModule(String caesarBridgeBaseEndpoint) {
        this.caesarBridgeBaseEndpoint = caesarBridgeBaseEndpoint;
    }

    @Provides
    @Singleton
    public CaesarApiClient caesarApiClient() {
        return new CaesarApiClient(new CaesarApiProvider(caesarBridgeBaseEndpoint));
    }

    @Provides
    @Singleton
    public ToteStrategyParametersCache toteStrategyParametersCache() {
        return new ToteStrategyParametersCache();
    }

    @Provides
    @Singleton
    public ToteStrategyParametersCacheRefresher toteStrategyParametersCacheRefresher(ToteStrategyParametersCache toteStrategyParametersCache,
                                                                                     CaesarApiClient caesarApiClient)
    {
        return new ToteStrategyParametersCacheRefresher(toteStrategyParametersCache, caesarApiClient);
    }
}
