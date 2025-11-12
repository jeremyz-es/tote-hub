package com.edgestackers.pgi.services.event.controller.request;

import io.javalin.http.Context;

public class PingRequestHandler {

    public void handle(Context context) {
        context.result("Pong");
    }
}
