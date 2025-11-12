package com.edgestackers.pgi.services.feed.controller;

import com.edgestackers.pgi.services.feed.controller.request.CycleNotifierRequestHandler;
import com.edgestackers.pgi.services.feed.controller.request.PgiCycleUpdateRequestHandler;
import com.edgestackers.pgi.services.feed.controller.request.PgiProgramMetadataRequestHandler;
import com.edgestackers.pgi.services.feed.controller.request.PgiRaceMetadataRequestHandler;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.edgestackers.pgi.services.feed.controller.UriProvider.*;

public class PgiFeedServiceRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiFeedServiceRestController.class);
    private final Javalin server;
    private final int httpPort;
    private final PgiProgramMetadataRequestHandler pgiProgramMetadataRequestHandler;
    private final PgiCycleUpdateRequestHandler pgiCycleUpdateRequestHandler;
    private final CycleNotifierRequestHandler cycleNotifierRequestHandler;
    private final PgiRaceMetadataRequestHandler pgiRaceMetadataRequestHandler;

    public PgiFeedServiceRestController(Javalin server,
                                        int httpPort,
                                        PgiProgramMetadataRequestHandler pgiProgramMetadataRequestHandler,
                                        PgiCycleUpdateRequestHandler pgiCycleUpdateRequestHandler,
                                        CycleNotifierRequestHandler cycleNotifierRequestHandler,
                                        PgiRaceMetadataRequestHandler pgiRaceMetadataRequestHandler) {
        this.server = server;
        this.httpPort = httpPort;
        this.pgiProgramMetadataRequestHandler = pgiProgramMetadataRequestHandler;
        this.pgiCycleUpdateRequestHandler = pgiCycleUpdateRequestHandler;
        this.cycleNotifierRequestHandler = cycleNotifierRequestHandler;
        this.pgiRaceMetadataRequestHandler = pgiRaceMetadataRequestHandler;
    }

    public void start() {
        server.start(httpPort);
        server.get(PROGRAM_METADATA_URI, pgiProgramMetadataRequestHandler::handle);
        server.get(CYCLE_UPDATES_URI, pgiCycleUpdateRequestHandler::handle);
        server.post(CYCLE_EVENT_PUSH_URI, cycleNotifierRequestHandler::handle);
        server.get(RACE_METADATA_URI, pgiRaceMetadataRequestHandler::handle);
        LOGGER.info("[{}] has started! Bound to Port {}", getClass().getSimpleName(), httpPort);
    }
}
