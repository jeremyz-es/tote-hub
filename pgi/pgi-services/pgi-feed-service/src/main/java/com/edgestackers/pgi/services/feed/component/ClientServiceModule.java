package com.edgestackers.pgi.services.feed.component;

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

@Module
public class ClientServiceModule {
    private final GwsClientServiceConnectionConfig connectionConfig;
    private final GwsClientServiceProxyConfig proxyConfig;

    public ClientServiceModule(GwsClientServiceConnectionConfig clientServiceConnectionConfig, String proxyServer, int proxyPort) {
        this.connectionConfig = clientServiceConnectionConfig;
        this.proxyConfig = new GwsClientServiceProxyConfig(proxyServer, proxyPort);
    }

    @Provides
    @Singleton
    public GwsSoap gwsSoap() {
        return createGwsClientService(proxyConfig, connectionConfig);
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
}
