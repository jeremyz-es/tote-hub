package com.edgestackers.opticon.service;

import com.edgestackers.opticon.service.component.*;
import com.edgestackers.opticon.service.message.NatsTopicContext;
import io.javalin.Javalin;
import io.nats.client.Connection;
import io.nats.client.Nats;

import java.util.Map;

public class OpticonServiceMain {
    private static final Map<String, String> ENV_VARS = System.getenv();
    private static final int HTTP_PORT = Integer.parseInt(ENV_VARS.get("OPTICON_SERVICE_HTTP_PORT"));
    private static final int WEBSOCKET_PORT = Integer.parseInt(ENV_VARS.get("OPTICON_SERVICE_WEBSOCKET_PORT"));
    private static final String NATS_CONNECTION_URL = ENV_VARS.get("OPTICON_SERVICE_NATS_CONNECTION_URL");
    private static final String TOTE_SUBSCRIPTION_TOPIC = ENV_VARS.get("OPTICON_SERVICE_TOTE_SUBSCRIPTION_TOPIC");
    private static final String ES_API_BASE_URL = ENV_VARS.get("OPTICON_SERVICE_ES_API_BASE_URL");
    private static final String CAESAR_BRIDGE_BASE_ENDPOINT = ENV_VARS.get("OPTICON_SERVICE_CAESAR_BRIDGE_BASE_ENDPOINT");

    public static void main(String[] args) throws Exception {
        Javalin server = Javalin.create();
        Connection connection = Nats.connect(NATS_CONNECTION_URL);
        NatsTopicContext natsTopicContext = new NatsTopicContext(TOTE_SUBSCRIPTION_TOPIC, "nitro.>", "TURBO.>");
        OpticonServiceComponent opticonServiceComponent = DaggerOpticonServiceComponent.builder()
                .metadataModule(new MetadataModule(ES_API_BASE_URL))
                .parametersModule(new ParametersModule(CAESAR_BRIDGE_BASE_ENDPOINT))
                .natsModule(new NatsModule(connection, natsTopicContext))
                .restModule(new RestModule(HTTP_PORT, server))
                .websocketModule(new WebsocketModule(WEBSOCKET_PORT))
                .build();
        OpticonService opticonService = opticonServiceComponent.opticonService();
        opticonService.start();
    }
}
