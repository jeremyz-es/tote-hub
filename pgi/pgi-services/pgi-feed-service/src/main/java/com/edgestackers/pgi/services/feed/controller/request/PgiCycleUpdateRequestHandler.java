package com.edgestackers.pgi.services.feed.controller.request;

import com.edgestackers.pgi.common.datamodel.cycle.PgiCycleUpdate;
import com.edgestackers.pgi.services.feed.cycle.PgiCycleUpdateCache;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PgiCycleUpdateRequestHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiCycleUpdateRequestHandler.class);
    private static final ObjectMapper SERIALIZER = new ObjectMapper();
    private final PgiCycleUpdateCache pgiCycleUpdateCache;

    public PgiCycleUpdateRequestHandler(PgiCycleUpdateCache pgiCycleUpdateCache) {
        this.pgiCycleUpdateCache = pgiCycleUpdateCache;
    }

    public void handle(Context context) {
        try {
            List<PgiCycleUpdate> cycleUpdates = pgiCycleUpdateCache.getAll();
            String responseBody = SERIALIZER.writeValueAsString(cycleUpdates);
            context.result(responseBody);
        }
        catch (Exception e) {
            LOGGER.error("Failed to serve {}s due to {} - {}", PgiCycleUpdate.class.getSimpleName(), e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
