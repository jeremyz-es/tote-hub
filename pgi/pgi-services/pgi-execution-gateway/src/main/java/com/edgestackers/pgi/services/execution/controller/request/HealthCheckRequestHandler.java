package com.edgestackers.pgi.services.execution.controller.request;

import io.javalin.http.Context;

public class HealthCheckRequestHandler {
    private int counter = 0;

    public void handle(Context context) {
        context.result(String.format("Pong %s", counter++));
    }
}
