package com.edgestackers.pgi.services.event.component;

import com.edgestackers.pgi.services.event.controller.PgiEventServiceRestController;
import com.edgestackers.pgi.services.event.controller.request.NotificationRequestHandler;
import com.edgestackers.pgi.services.event.controller.request.PingRequestHandler;
import com.edgestackers.pgi.services.event.handler.PgiEventMessageProcessor;
import dagger.Module;
import dagger.Provides;
import io.javalin.Javalin;

import javax.inject.Singleton;

@Module
public class RestModule {
    private final Javalin server;
    private final int httpPort;

    public RestModule(Javalin server, int httpPort) {
        this.server = server;
        this.httpPort = httpPort;
    }

    @Provides
    @Singleton
    public NotificationRequestHandler notificationRequestHandler(PgiEventMessageProcessor pgiEventMessageProcessor) {
        return new NotificationRequestHandler(pgiEventMessageProcessor);
    }

    @Provides
    @Singleton
    public PingRequestHandler pingRequestHandler() {
        return new PingRequestHandler();
    }

    @Provides
    @Singleton
    public PgiEventServiceRestController providePgiEventServiceRestController(NotificationRequestHandler notificationRequestHandler,
                                                                              PingRequestHandler pingRequestHandler) {
        return new PgiEventServiceRestController(server, httpPort, notificationRequestHandler, pingRequestHandler);
    }
}
