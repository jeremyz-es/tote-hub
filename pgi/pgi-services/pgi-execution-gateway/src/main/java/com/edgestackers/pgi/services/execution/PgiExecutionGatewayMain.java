package com.edgestackers.pgi.services.execution;

import com.edgestackers.pgi.common.client.GwsClientServiceConnectionConfig;
import com.edgestackers.pgi.services.execution.component.*;
import com.edgestackers.pgi.services.execution.config.PgiExecutionGatewayConfig;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import io.javalin.Javalin;
import io.nats.client.Connection;
import io.nats.client.Nats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.edgestackers.pgi.common.client.GwsClientServiceConnectionConfigProvider.retrieveAndCreateConnectionConfig;

public class PgiExecutionGatewayMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiExecutionGatewayMain.class);
    private static final Map<String, String> ENV_VARS = System.getenv();
    private static final double STRATEGY_ORDER_HARD_DNE_NOTIONAL = 1000.0d;
    private static final int PGI_EXECUTION_GATEWAY_HTTP_PORT = Integer.parseInt(ENV_VARS.get("PGI_EXECUTION_GATEWAY_HTTP_PORT"));
    private static final int PGI_EXECUTION_GATEWAY_WEBSOCKET_PORT = Integer.parseInt(ENV_VARS.get("PGI_EXECUTION_GATEWAY_WEBSOCKET_PORT"));
    private static final String PGI_EXECUTION_GATEWAY_NATS_CONNECTION_URL = ENV_VARS.get("PGI_EXECUTION_GATEWAY_NATS_CONNECTION_URL");
    private static final String PGI_EXECUTION_GATEWAY_ORDER_RESPONSE_TOPIC = ENV_VARS.get("PGI_EXECUTION_GATEWAY_ORDER_RESPONSE_TOPIC");
    private static final String PGI_EXECUTION_GATEWAY_TOTE_ORDER_RESPONSE_TOPIC = ENV_VARS.get("PGI_EXECUTION_GATEWAY_TOTE_ORDER_RESPONSE_TOPIC");
    private static final String PGI_EXECUTION_GATEWAY_ACCOUNT_BALANCE_TOPIC = ENV_VARS.get("PGI_EXECUTION_GATEWAY_ACCOUNT_BALANCE_TOPIC");
    private static final String PGI_EXECUTION_GATEWAY_FILE_BET_STATUS_CHECK_TOPIC = ENV_VARS.get("PGI_EXECUTION_GATEWAY_FILE_BET_STATUS_CHECK_TOPIC");
    private static final String PGI_EXECUTION_GATEWAY_BASE_ENDPOINT = ENV_VARS.get("PGI_EXECUTION_GATEWAY_BASE_ENDPOINT");
    private static final TotePoolJurisdiction PGI_EXECUTION_GATEWAY_JURISDICTION = TotePoolJurisdiction.valueOf(ENV_VARS.get("PGI_EXECUTION_GATEWAY_JURISDICTION"));
    private static final String PGI_EXECUTION_GATEWAY_ES_API_BASE_URL = ENV_VARS.get("PGI_EXECUTION_GATEWAY_ES_API_BASE_URL");
    private static final double PGI_EXECUTION_GATEWAY_STRATEGY_ORDER_DNE_NOTIONAL = Double.parseDouble(ENV_VARS.get("PGI_EXECUTION_GATEWAY_STRATEGY_ORDER_DNE_NOTIONAL"));

    public static void main(String[] args) throws Exception {
        validationHook();
        startupHook();
    }

    private static void validationHook() {
        if (PGI_EXECUTION_GATEWAY_STRATEGY_ORDER_DNE_NOTIONAL > STRATEGY_ORDER_HARD_DNE_NOTIONAL) {
            String errorMessage = String.format("Failed to validate execution config - configured strategy order DNE notional limit (%s) was greater than hard ceiling (%s).",
                    PGI_EXECUTION_GATEWAY_STRATEGY_ORDER_DNE_NOTIONAL,
                    STRATEGY_ORDER_HARD_DNE_NOTIONAL
            );
            LOGGER.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    private static void startupHook() throws Exception {
        Javalin server = Javalin.create();
        Connection connection = Nats.connect(PGI_EXECUTION_GATEWAY_NATS_CONNECTION_URL);
        GwsClientServiceConnectionConfig clientServiceConnectionConfig = retrieveAndCreateConnectionConfig(PGI_EXECUTION_GATEWAY_JURISDICTION, PGI_EXECUTION_GATEWAY_BASE_ENDPOINT);
        PgiExecutionGatewayConfig pgiExecutionGatewayConfig = new PgiExecutionGatewayConfig(PGI_EXECUTION_GATEWAY_STRATEGY_ORDER_DNE_NOTIONAL);

        PgiExecutionGatewayComponent pgiExecutionGatewayComponent = DaggerPgiExecutionGatewayComponent.builder()
                .executionModule(new ExecutionModule(PGI_EXECUTION_GATEWAY_JURISDICTION, pgiExecutionGatewayConfig))
                .fileBetModule(new FileBetModule(PGI_EXECUTION_GATEWAY_JURISDICTION))
                .gwsModule(new GwsModule(clientServiceConnectionConfig))
                .metadataModule(new MetadataModule(PGI_EXECUTION_GATEWAY_ES_API_BASE_URL))
                .natsModule(new NatsModule(
                        connection,
                        PGI_EXECUTION_GATEWAY_ORDER_RESPONSE_TOPIC,
                        PGI_EXECUTION_GATEWAY_ACCOUNT_BALANCE_TOPIC,
                        PGI_EXECUTION_GATEWAY_FILE_BET_STATUS_CHECK_TOPIC,
                        PGI_EXECUTION_GATEWAY_TOTE_ORDER_RESPONSE_TOPIC
                        )
                )
                .restModule(new RestModule(PGI_EXECUTION_GATEWAY_HTTP_PORT, server))
                .websocketModule(new WebsocketModule(PGI_EXECUTION_GATEWAY_WEBSOCKET_PORT))
                .build();
        PgiExecutionGateway pgiExecutionGateway = pgiExecutionGatewayComponent.pgiExecutionGateway();
        pgiExecutionGateway.start();
    }
}
