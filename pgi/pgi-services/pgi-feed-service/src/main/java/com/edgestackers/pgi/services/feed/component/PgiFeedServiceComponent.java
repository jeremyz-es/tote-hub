package com.edgestackers.pgi.services.feed.component;

import com.edgestackers.pgi.services.feed.PgiFeedService;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        ClientServiceModule.class,
        CycleUpdateModule.class,
        EventNotificationModule.class,
        FeedServiceModule.class,
        InformationModule.class,
        MetadataModule.class,
        NatsModule.class,
        RestModule.class,
})
public interface PgiFeedServiceComponent {
    PgiFeedService pgiFeedService();
}
