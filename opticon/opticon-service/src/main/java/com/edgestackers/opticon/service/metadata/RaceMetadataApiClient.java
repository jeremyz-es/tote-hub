package com.edgestackers.opticon.service.metadata;

import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;
import com.edgestackers.tote.hub.core.metadata.EsRaceMetadata;
import com.edgestackers.tote.hub.core.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.util.List;

public class RaceMetadataApiClient {
    private static final ObjectMapper DESERIALIZER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final HttpClient HTTP_CLIENT = new HttpClient();
    private final String baseEndpoint;

    public RaceMetadataApiClient(String baseEndpoint) {
        this.baseEndpoint = baseEndpoint;
    }

    public List<EsRaceMetadata> retrieveEsRaceMetadataFor(RaceCode raceType, String date) throws Exception {
        HttpResponse<String> response = HTTP_CLIENT.get(String.format("%s/v1/metadata/race_meta/%s/AU/%s", baseEndpoint, raceType, date));
        return DESERIALIZER.readValue(response.body(), new TypeReference<>() {});
    }
}
