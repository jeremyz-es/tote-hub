package com.edgestackers.pgi.services.event.component;

import com.edgestackers.pgi.services.event.PgiEventService;
import com.edgestackers.pgi.services.event.controller.PgiEventServiceRestController;
import com.edgestackers.pgi.services.event.controller.PgiEventServiceWebsocketController;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class EventServiceModule {

    @Provides
    @Singleton
    public PgiEventService pgiEventService(PgiEventServiceRestController restController,
                                           PgiEventServiceWebsocketController websocketController)
    {
        return new PgiEventService(restController, websocketController);
    }
}
