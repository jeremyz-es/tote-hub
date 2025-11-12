package com.edgestackers.opticon.common.client;

import com.edgestackers.opticon.common.datamodel.OpticonInitContext;
import com.edgestackers.tote.hub.core.util.HttpClient;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;

import static com.edgestackers.opticon.common.service.OpticonServiceUriProvider.INIT_CONTEXT_URI;

public class OpticonApiClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpticonApiClient.class);
    private static final HttpClient HTTP_CLIENT = new HttpClient();
    private static final ObjectMapper DESERIALIZER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final String baseEndpoint;

    public OpticonApiClient(String baseEndpoint) {
        this.baseEndpoint = baseEndpoint;
    }

    public OpticonInitContext retrieveOpticonInitContext() throws Exception {
        HttpResponse<String> response = HTTP_CLIENT.get(String.format("%s%s", baseEndpoint, INIT_CONTEXT_URI));
        LOGGER.info("{} size: {}MB", OpticonInitContext.class.getSimpleName(), response.body().length() / 1024 / 1024);
        OpticonInitContext opticonInitContext = DESERIALIZER.readValue(response.body(), OpticonInitContext.class);

        String metadatas = DESERIALIZER.writeValueAsString(opticonInitContext.esRaceMetadatas());
        String runs = DESERIALIZER.writeValueAsString(opticonInitContext.runSummaries());
        String cycles = DESERIALIZER.writeValueAsString(opticonInitContext.cycleUpdates());
        String balances = DESERIALIZER.writeValueAsString(opticonInitContext.balanceSummaries());
        String divUpdates = DESERIALIZER.writeValueAsString(opticonInitContext.dividendPredictionUpdates());
        String theoUpdates = DESERIALIZER.writeValueAsString(opticonInitContext.theoUpdates());
        String orderUpdates = DESERIALIZER.writeValueAsString(opticonInitContext.orderUpdates());
        String exoticContexts = DESERIALIZER.writeValueAsString(opticonInitContext.exoticContextSummaries());

        LOGGER.info("Metadata: {}KB, Runs: {}KB, Cycles: {}KB, Balances: {}KB, Divs: {}KB, Theos: {}KB, Orders: {}KB, Exotic Contexts: {}KB",
                metadatas.length() / 1024,
                runs.length() / 1024,
                cycles.length() / 1024,
                balances.length() / 1024,
                divUpdates.length() / 1024,
                theoUpdates.length() / 1024,
                orderUpdates.length() / 1024,
                exoticContexts.length() / 1024
        );
        return opticonInitContext;
    }
}
