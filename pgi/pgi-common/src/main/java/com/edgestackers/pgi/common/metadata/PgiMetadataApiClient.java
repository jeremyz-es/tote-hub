package com.edgestackers.pgi.common.metadata;

import com.edgestackers.pgi.common.datamodel.metadata.PgiRaceMetadata;
import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;
import com.edgestackers.tote.hub.core.util.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.util.List;

import static com.edgestackers.pgi.common.metadata.PgiMetadataApiProvider.pgiRaceMetadataEndpointFor;

public class PgiMetadataApiClient {
    private static final ObjectMapper DESERIALIZER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final HttpClient HTTP_CLIENT = new HttpClient();
    private final String baseUrl;

    public PgiMetadataApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<PgiRaceMetadata> retrievePgiRaceMetadata(RaceCode raceType, String date) throws Exception {
        HttpResponse<String> response = HTTP_CLIENT.get(pgiRaceMetadataEndpointFor(baseUrl, raceType, date));
        return DESERIALIZER.readValue(response.body(), new TypeReference<List<PgiRaceMetadata>>() {});
    }
}
