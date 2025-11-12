package com.edgestackers.pgi.services.feed.controller.request;

import com.edgestackers.pgi.services.feed.datamodel.PgiRaceInformation;
import com.edgestackers.pgi.services.feed.information.PgiRaceInformationCache;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class PgiRaceMetadataRequestHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiRaceMetadataRequestHandler.class);
    private static final ObjectMapper SERIALIZER = new ObjectMapper();
    private final PgiRaceInformationCache pgiRaceInformationCache;

    public PgiRaceMetadataRequestHandler(PgiRaceInformationCache pgiRaceInformationCache) {
        this.pgiRaceInformationCache = pgiRaceInformationCache;
    }

    public void handle(Context context) {
        try {
            List<PgiRaceInformation> pgiRaceMetadata = pgiRaceInformationCache.getAll();
            String responseBody = SERIALIZER.writeValueAsString(pgiRaceMetadata);
            context.result(responseBody);
        }
        catch (IOException e) {
            LOGGER.error("Failed to handle [{}] retrieval due to {} - {}", PgiRaceInformation.class.getSimpleName(), e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
