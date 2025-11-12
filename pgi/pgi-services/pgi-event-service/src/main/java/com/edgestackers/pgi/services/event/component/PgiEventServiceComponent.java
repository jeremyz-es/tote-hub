package com.edgestackers.pgi.services.event.component;

import com.edgestackers.pgi.services.event.PgiEventService;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        EventMessageModule.class,
        EventServiceModule.class,
        NatsModule.class,
        RestModule.class,
        WebsocketModule.class,
})
public interface PgiEventServiceComponent {
    PgiEventService pgiEventService();
}
