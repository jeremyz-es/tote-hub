package com.edgestackers.pgi.services.feed.component;

import com.edgestackers.pgi.services.feed.controller.PgiFeedServiceRestController;
import com.edgestackers.pgi.services.feed.controller.request.CycleNotifierRequestHandler;
import com.edgestackers.pgi.services.feed.controller.request.PgiCycleUpdateRequestHandler;
import com.edgestackers.pgi.services.feed.controller.request.PgiProgramMetadataRequestHandler;
import com.edgestackers.pgi.services.feed.controller.request.PgiRaceMetadataRequestHandler;
import com.edgestackers.pgi.services.feed.cycle.PgiCycleUpdateCache;
import com.edgestackers.pgi.services.feed.information.PgiProgramInformationCache;
import com.edgestackers.pgi.services.feed.information.PgiRaceInformationCache;
import com.edgestackers.pgi.services.feed.notification.CycleDataUpdatedNotificationProcessor;
import dagger.Module;
import dagger.Provides;
import io.javalin.Javalin;

import javax.inject.Singleton;

@Module
public class RestModule {
    private final int httpPort;
    private final Javalin server;

    public RestModule(int httpPort, Javalin server) {
        this.httpPort = httpPort;
        this.server = server;
    }

    @Provides
    @Singleton
    public PgiProgramMetadataRequestHandler pgiProgramMetadataRequestHandler(PgiProgramInformationCache pgiProgramInformationCache) {
        return new PgiProgramMetadataRequestHandler(pgiProgramInformationCache);
    }

    @Provides
    @Singleton
    public PgiCycleUpdateRequestHandler pgiCycleUpdateRequestHandler(PgiCycleUpdateCache pgiCycleUpdateCache) {
        return new PgiCycleUpdateRequestHandler(pgiCycleUpdateCache);
    }

    @Provides
    @Singleton
    public CycleNotifierRequestHandler cycleNotifierRequestHandler(CycleDataUpdatedNotificationProcessor notificationProcessor) {
        return new CycleNotifierRequestHandler(notificationProcessor);
    }

    @Provides
    @Singleton
    public PgiRaceMetadataRequestHandler pgiRaceMetadataRequestHandler(PgiRaceInformationCache pgiRaceInformationCache) {
        return new PgiRaceMetadataRequestHandler(pgiRaceInformationCache);
    }

    @Provides
    @Singleton
    public PgiFeedServiceRestController pgiFeedServiceRestController(PgiProgramMetadataRequestHandler pgiProgramMetadataRequestHandler,
                                                                     PgiCycleUpdateRequestHandler pgiCycleUpdateRequestHandler,
                                                                     CycleNotifierRequestHandler cycleNotifierRequestHandler,
                                                                     PgiRaceMetadataRequestHandler pgiRaceMetadataRequestHandler) {
        return new PgiFeedServiceRestController(
                server,
                httpPort,
                pgiProgramMetadataRequestHandler,
                pgiCycleUpdateRequestHandler,
                cycleNotifierRequestHandler,
                pgiRaceMetadataRequestHandler
        );
    }
}
