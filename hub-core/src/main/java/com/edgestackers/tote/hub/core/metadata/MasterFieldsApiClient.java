package com.edgestackers.tote.hub.core.metadata;

import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;
import com.edgestackers.tote.hub.core.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;
import java.util.List;

public class MasterFieldsApiClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(MasterFieldsApiClient.class);
    private static final ObjectMapper DESERIALIZER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final HttpClient HTTP_CLIENT = new HttpClient();
    private final String baseEndpoint;

    public MasterFieldsApiClient(String baseEndpoint) {
        this.baseEndpoint = baseEndpoint;
    }

    public List<MasterField> retrieveMasterFields(RaceCode raceType, String date) throws Exception {
        String endpoint = String.format("%s/v1/fields/master_fields/%s/%s", baseEndpoint, raceType, date);
        HttpResponse<String> response = HTTP_CLIENT.get(endpoint);
        return DESERIALIZER.readValue(response.body(), new TypeReference<List<MasterField>>() {});
    }
}
