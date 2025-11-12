package com.edgestackers.pgi.services.event;

import com.edgestackers.pgi.services.event.component.*;
import io.javalin.Javalin;
import io.nats.client.Connection;
import io.nats.client.Nats;

import java.util.Map;

public class PgiEventServiceMain {
    private static final Map<String, String> ENV_VARS = System.getenv();
    private static final int HTTP_PORT = Integer.parseInt(ENV_VARS.get("PGI_EVENT_SERVICE_HTTP_PORT"));
    private static final int WEBSOCKET_PORT = Integer.parseInt(ENV_VARS.get("PGI_EVENT_SERVICE_WEBSOCKET_PORT"));
    private static final String PGI_EVENT_SERVICE_NATS_CONNECTION_URL = ENV_VARS.get("PGI_EVENT_SERVICE_NATS_CONNECTION_URL");

    public static void main(String[] args) throws Exception {
        Javalin server = Javalin.create();
        Connection connection = Nats.connect(PGI_EVENT_SERVICE_NATS_CONNECTION_URL);
        PgiEventServiceComponent component = DaggerPgiEventServiceComponent.builder()
                .natsModule(new NatsModule(connection))
                .restModule(new RestModule(server, HTTP_PORT))
                .websocketModule(new WebsocketModule(WEBSOCKET_PORT))
                .build();
        PgiEventService service = component.pgiEventService();
        service.start();
    }
}
