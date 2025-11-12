package com.edgestackers.pgi.services.execution.component;

import dagger.Module;

@Module
public class WebsocketModule {
    private final int websocketPort;

    public WebsocketModule(int websocketPort) {
        this.websocketPort = websocketPort;
    }
}
