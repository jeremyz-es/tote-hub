package com.edgestackers.pgi.services.execution.component;

import com.amtote.filebet.IService;
import com.amtote.gws.GwsSoap;
import com.edgestackers.pgi.common.client.GwsAccountInfo;
import com.edgestackers.pgi.common.client.GwsClientInfoProvider;
import com.edgestackers.pgi.common.client.GwsClientServiceConnectionConfig;
import com.edgestackers.pgi.common.client.GwsClientServiceProxyConfig;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import static com.edgestackers.pgi.common.client.GpHeaderProvider.createGpHeader;
import static com.edgestackers.pgi.common.client.GwsClientServiceFactory.createGwsClientService;
import static com.edgestackers.pgi.common.client.GwsClientServiceFactory.createIService;

@Module
public class GwsModule {
    private final GwsClientServiceConnectionConfig connectionConfig;

    public GwsModule(GwsClientServiceConnectionConfig clientServiceConnectionConfig) {
        this.connectionConfig = clientServiceConnectionConfig;
    }

    @Provides
    @Singleton
    public GwsSoap gwsSoap() {
        return createGwsClientService(null, connectionConfig);
    }

    @Provides
    @Singleton
    public GwsClientInfoProvider gwsClientInfoProvider() {
        return new GwsClientInfoProvider(
                createGpHeader(connectionConfig),
                new GwsAccountInfo(connectionConfig.masterAccountNumber(), connectionConfig.masterAccountPin()),
                new GwsAccountInfo(connectionConfig.rebateAccountNumber(), connectionConfig.rebateAccountPin())
        );
    }

    @Provides
    @Singleton
    public IService iService() {
        return createIService(null, connectionConfig);
    }
}
