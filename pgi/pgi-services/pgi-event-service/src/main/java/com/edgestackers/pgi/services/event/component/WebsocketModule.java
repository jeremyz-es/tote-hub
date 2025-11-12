package com.edgestackers.pgi.services.event.component;

import com.edgestackers.pgi.services.event.controller.PgiEventServiceWebsocketController;
import com.edgestackers.pgi.services.event.controller.publisher.PgiEventServiceWebsocketPublisher;
import dagger.Module;
import dagger.Provides;
import org.java_websocket.WebSocket;

import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

@Module
public class WebsocketModule {
    private final Set<WebSocket> connections = new HashSet<>();
    private final int websocketPort;

    public WebsocketModule(int websocketPort) {
        this.websocketPort = websocketPort;
    }

    @Provides
    @Singleton
    public PgiEventServiceWebsocketController pgiEventServiceWebsocketController() {
        return new PgiEventServiceWebsocketController(websocketPort, connections);
    }

    @Provides
    @Singleton
    public PgiEventServiceWebsocketPublisher pgiEventServiceWebsocketPublisher() {
        return new PgiEventServiceWebsocketPublisher(connections);
    }
}
