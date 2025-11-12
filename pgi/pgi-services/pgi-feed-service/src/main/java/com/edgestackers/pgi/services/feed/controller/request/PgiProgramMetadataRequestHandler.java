package com.edgestackers.pgi.services.feed.controller.request;

import com.edgestackers.pgi.services.feed.datamodel.PgiProgramInformation;
import com.edgestackers.pgi.services.feed.information.PgiProgramInformationCache;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PgiProgramMetadataRequestHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiProgramMetadataRequestHandler.class);
    private static final ObjectMapper SERIALIZER = new ObjectMapper();
    private final PgiProgramInformationCache pgiProgramInformationCache;

    public PgiProgramMetadataRequestHandler(PgiProgramInformationCache pgiProgramInformationCache) {
        this.pgiProgramInformationCache = pgiProgramInformationCache;
    }

    public void handle(Context context) {
        try {
            List<PgiProgramInformation> pgiProgramMetadata = pgiProgramInformationCache.getAll();
            String responseBody = SERIALIZER.writeValueAsString(pgiProgramMetadata);
            context.result(responseBody);
        }
        catch (Exception e) {
            LOGGER.error("Failed to handle [{}] retrieval request due to {} - {}", PgiProgramInformation.class.getSimpleName(), e.getClass().getSimpleName(), e.getMessage());
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
