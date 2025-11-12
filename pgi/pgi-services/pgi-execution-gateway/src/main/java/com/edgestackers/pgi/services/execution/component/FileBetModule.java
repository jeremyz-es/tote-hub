package com.edgestackers.pgi.services.execution.component;

import com.amtote.filebet.IService;
import com.edgestackers.pgi.common.client.GwsClientInfoProvider;
import com.edgestackers.pgi.services.execution.filebet.FileBetBalanceRefresher;
import com.edgestackers.pgi.services.execution.filebet.FileBetService;
import com.edgestackers.pgi.services.execution.filebet.FileBetServiceInitializer;
import com.edgestackers.pgi.services.execution.filebet.FileBetStatusChecker;
import com.edgestackers.pgi.services.execution.nats.NatsPublisher;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class FileBetModule {
    private final TotePoolJurisdiction totePoolJurisdiction;

    public FileBetModule(TotePoolJurisdiction totePoolJurisdiction) {
        this.totePoolJurisdiction = totePoolJurisdiction;
    }

    @Provides
    @Singleton
    public FileBetServiceInitializer fileBetServiceInitializer(IService iService) {
        return new FileBetServiceInitializer(iService);
    }

    @Provides
    @Singleton
    public FileBetBalanceRefresher fileBetBalanceRefresher(IService iService,
                                                           GwsClientInfoProvider gwsClientInfoProvider,
                                                           NatsPublisher natsPublisher) {
        return new FileBetBalanceRefresher(
                iService,
                totePoolJurisdiction,
                gwsClientInfoProvider.masterAccountInfo(),
                gwsClientInfoProvider.rebateAccountInfo(),
                natsPublisher
        );
    }

    @Provides
    @Singleton
    public FileBetStatusChecker fileBetStatusChecker(IService iService, NatsPublisher natsPublisher) {
        return new FileBetStatusChecker(iService, natsPublisher);
    }

    @Provides
    @Singleton
    public FileBetService fileBetService(FileBetServiceInitializer fileBetServiceInitializer,
                                         FileBetBalanceRefresher fileBetBalanceRefresher,
                                         FileBetStatusChecker fileBetStatusChecker) {
        return new FileBetService(
                fileBetServiceInitializer,
                fileBetBalanceRefresher,
                fileBetStatusChecker
        );
    }
}
