package com.edgestackers.tote.hub.core.parameters;

import com.edgestackers.tote.hub.core.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;
import java.util.List;

public class CaesarApiClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(CaesarApiClient.class);
    private static final ObjectMapper SERIALIZER = new ObjectMapper();
    private static final ObjectMapper DESERIALIZER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final HttpClient HTTP_CLIENT = new HttpClient();
    private final CaesarApiProvider caesarApiProvider;

    public CaesarApiClient(CaesarApiProvider caesarApiProvider) {
        this.caesarApiProvider = caesarApiProvider;
    }

    public void pushToteStrategyParameters(ToteStrategyParameters toteStrategyParameters) throws Exception {
        String requestBody = SERIALIZER.writerFor(ToteStrategyParameters.class).writeValueAsString(toteStrategyParameters);
        HTTP_CLIENT.post(caesarApiProvider.toteStrategyParametersEndpoint(), requestBody);
    }

    public void deleteToteStrategyParameters(String strategyId) throws Exception {
        HTTP_CLIENT.delete(caesarApiProvider.toteStrategyParametersEndpoint(), strategyId);
    }

    public List<ToteStrategyParameters> retrieveToteStrategyParameters() throws Exception {
        HttpResponse<String> response = HTTP_CLIENT.get(caesarApiProvider.toteStrategyParametersEndpoint());
        return DESERIALIZER.readValue(response.body(), new TypeReference<>(){});
    }
}
