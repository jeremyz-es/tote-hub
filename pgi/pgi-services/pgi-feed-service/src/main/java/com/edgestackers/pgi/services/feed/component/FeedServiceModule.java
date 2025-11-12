package com.edgestackers.pgi.services.feed.component;

import com.edgestackers.pgi.services.common.datamodel.PgiTotePoolId;
import com.edgestackers.pgi.services.feed.PgiFeedService;
import com.edgestackers.pgi.services.feed.controller.PgiFeedServiceRestController;
import com.edgestackers.pgi.services.feed.information.PgiInformationRefresher;
import com.edgestackers.pgi.services.feed.metadata.PgiRaceMetadataCacheRefresher;
import com.edgestackers.pgi.services.feed.notification.EventNotificationSubscriber;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class FeedServiceModule {
    private final PgiTotePoolId pgiTotePoolId;

    public FeedServiceModule(PgiTotePoolId pgiTotePoolId) {
        this.pgiTotePoolId = pgiTotePoolId;
    }

    @Provides
    @Singleton
    public PgiFeedService pgiFeedService(PgiFeedServiceRestController pgiFeedServiceRestController,
                                         PgiInformationRefresher metadataRefresher,
                                         EventNotificationSubscriber eventNotificationSubscriber,
                                         PgiRaceMetadataCacheRefresher pgiRaceMetadataCacheRefresher) {
        return new PgiFeedService(
                pgiFeedServiceRestController,
                metadataRefresher,
                eventNotificationSubscriber,
                pgiRaceMetadataCacheRefresher
        );
    }

    @Provides
    @Singleton
    public PgiTotePoolId pgiTotePoolId() {
        return pgiTotePoolId;
    }
}
