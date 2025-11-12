package com.edgestackers.pgi.services.feed;

import com.edgestackers.pgi.common.client.GwsClientServiceConnectionConfig;
import com.edgestackers.pgi.services.common.datamodel.PgiTotePoolId;
import com.edgestackers.pgi.services.feed.component.*;
import com.edgestackers.pgi.services.feed.publisher.PgiNatsTopicContext;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import io.javalin.Javalin;
import io.nats.client.Connection;
import io.nats.client.Nats;

import java.io.IOException;
import java.util.Map;

import static com.edgestackers.pgi.common.client.GwsClientServiceConnectionConfigProvider.retrieveAndCreateConnectionConfig;

public class PgiFeedServiceMain {
    private static final Map<String, String> ENV_VARS = System.getenv();
    private static final String PGI_NATS_CONNECTION_URL = ENV_VARS.get("PGI_NATS_CONNECTION_URL");
    private static final String PGI_CYCLE_UPDATES_NATS_TOPIC = ENV_VARS.get("PGI_CYCLE_UPDATES_NATS_TOPIC");
    private static final String TOTE_MARKET_CYCLE_UPDATES_NATS_TOPIC = ENV_VARS.get("TOTE_MARKET_CYCLE_UPDATES_NATS_TOPIC");
    private static final String TOTE_WIN_MARKET_CYCLE_UPDATES_NATS_TOPIC = ENV_VARS.get("TOTE_WIN_MARKET_CYCLE_UPDATES_NATS_TOPIC");
    private static final String PGI_PROXY_SERVER = ENV_VARS.get("PGI_PROXY_SERVER");
    private static final int PGI_PROXY_SERVER_PORT = Integer.parseInt(ENV_VARS.get("PGI_PROXY_SERVER_PORT"));
    private static final int PGI_FEED_SERVICE_HTTP_PORT = Integer.parseInt(ENV_VARS.get("PGI_FEED_SERVICE_HTTP_PORT"));
    private static final String PGI_EVENT_SERVICE_SUBSCRIPTION_ENDPOINT = ENV_VARS.get("PGI_EVENT_SERVICE_SUBSCRIPTION_ENDPOINT");
    private static final PgiTotePoolId PGI_TOTE_POOL_ID = PgiTotePoolId.valueOf(ENV_VARS.get("PGI_TOTE_POOL_ID"));
    private static final String PGI_FEED_SERVICE_ES_API_BASE_URL = ENV_VARS.get("PGI_FEED_SERVICE_ES_API_BASE_URL");
    private static final TotePoolJurisdiction PGI_FEED_SERVICE_JURISDICTION = TotePoolJurisdiction.valueOf(ENV_VARS.get("PGI_FEED_SERVICE_JURISDICTION"));
    private static final String PGI_FEED_SERVICE_BASE_ENDPOINT = ENV_VARS.get("PGI_FEED_SERVICE_BASE_ENDPOINT");
    private static final String PGI_FEED_SERVICE_SCRATCHING_NATS_TOPIC = ENV_VARS.get("PGI_FEED_SERVICE_SCRATCHING_NATS_TOPIC");
    private static final String PGI_FEED_SERVICE_TOTE_SCRATCHING_NATS_TOPIC = ENV_VARS.get("PGI_FEED_SERVICE_TOTE_SCRATCHING_NATS_TOPIC");
    private static final String PGI_FEED_SERVICE_TOTE_RACE_OFFICIAL_NATS_TOPIC = ENV_VARS.get("PGI_FEED_SERVICE_TOTE_RACE_OFFICIAL_NATS_TOPIC");
    private static final boolean ENABLE_SCRATCHING_PUBLISHING = Boolean.parseBoolean(ENV_VARS.get("PGI_FEED_SERVICE_ENABLE_SCRATCHING_PUBLISHING"));

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(PGI_PROXY_SERVER_PORT);
        System.out.println(PGI_PROXY_SERVER);
        GwsClientServiceConnectionConfig clientServiceConnectionConfig = retrieveAndCreateConnectionConfig(PGI_FEED_SERVICE_JURISDICTION, PGI_FEED_SERVICE_BASE_ENDPOINT);
        Connection natsConnection = Nats.connect(PGI_NATS_CONNECTION_URL);
        Javalin server = Javalin.create();
        PgiNatsTopicContext pgiNatsTopicContext = new PgiNatsTopicContext(
                PGI_CYCLE_UPDATES_NATS_TOPIC,
                TOTE_MARKET_CYCLE_UPDATES_NATS_TOPIC,
                TOTE_WIN_MARKET_CYCLE_UPDATES_NATS_TOPIC,
                PGI_FEED_SERVICE_SCRATCHING_NATS_TOPIC,
                PGI_FEED_SERVICE_TOTE_SCRATCHING_NATS_TOPIC,
                PGI_FEED_SERVICE_TOTE_RACE_OFFICIAL_NATS_TOPIC

        );
        PgiFeedServiceComponent pgiFeedServiceComponent = DaggerPgiFeedServiceComponent.builder()
                .clientServiceModule(new ClientServiceModule(clientServiceConnectionConfig, PGI_PROXY_SERVER, PGI_PROXY_SERVER_PORT))
                .eventNotificationModule(new EventNotificationModule(PGI_EVENT_SERVICE_SUBSCRIPTION_ENDPOINT, ENABLE_SCRATCHING_PUBLISHING, PGI_FEED_SERVICE_JURISDICTION))
                .feedServiceModule(new FeedServiceModule(PGI_TOTE_POOL_ID))
                .metadataModule(new MetadataModule(PGI_FEED_SERVICE_ES_API_BASE_URL))
                .natsModule(new NatsModule(natsConnection, pgiNatsTopicContext))
                .restModule(new RestModule(PGI_FEED_SERVICE_HTTP_PORT, server))
                .build();
        PgiFeedService pgiFeedService = pgiFeedServiceComponent.pgiFeedService();
        pgiFeedService.start();
    }
}
