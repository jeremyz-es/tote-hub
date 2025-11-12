package com.edgestackers.pgi.services.execution;

import com.edgestackers.pgi.services.execution.controller.PgiExecutionGatewayRestController;
import com.edgestackers.pgi.services.execution.filebet.FileBetService;
import com.edgestackers.pgi.services.execution.metadata.PgiMetadataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PgiExecutionGateway {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiExecutionGateway.class);
    private final PgiExecutionGatewayRestController restController;
    private final PgiMetadataService pgiMetadataService;
    private final FileBetService fileBetService;

    public PgiExecutionGateway(PgiExecutionGatewayRestController restController,
                               PgiMetadataService pgiMetadataService,
                               FileBetService fileBetService) {
        this.restController = restController;
        this.pgiMetadataService = pgiMetadataService;
        this.fileBetService = fileBetService;
    }

    public void start() {
        restController.start();
        pgiMetadataService.start();
        fileBetService.start();
        LOGGER.info("[{}] has started!", getClass().getSimpleName());
    }
}
