package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.controller.OpticonServiceWebsocketController;
import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import dagger.Module;
import dagger.Provides;
import org.java_websocket.WebSocket;

import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

@Module
public class WebsocketModule {
    private final Set<WebSocket> connectionCache = new HashSet<>();
    private final int websocketPort;

    public WebsocketModule(int websocketPort) {
        this.websocketPort = websocketPort;
    }

    @Provides
    @Singleton
    public OpticonServiceWebsocketController opticonServiceWebsocketController() {
        return new OpticonServiceWebsocketController(websocketPort, connectionCache);
    }

    @Provides
    @Singleton
    public OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher() {
        return new OpticonMessageWebsocketPublisher(connectionCache);
    }
}
