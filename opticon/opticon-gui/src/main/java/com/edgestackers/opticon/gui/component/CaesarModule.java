package com.edgestackers.opticon.gui.component;

import com.edgestackers.tote.hub.core.parameters.*;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class CaesarModule {
    private final CaesarApiProvider caesarApiProvider;

    public CaesarModule(CaesarApiProvider caesarApiProvider) {
        this.caesarApiProvider = caesarApiProvider;
    }

    @Provides
    @Singleton
    public CaesarApiClient caesarApiClient() {
        return new CaesarApiClient(caesarApiProvider);
    }
}
