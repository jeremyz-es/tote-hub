package com.edgestackers.opticon.service;

import com.edgestackers.opticon.service.controller.OpticonServiceRestController;
import com.edgestackers.opticon.service.controller.OpticonServiceWebsocketController;
import com.edgestackers.opticon.service.message.OpticonDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpticonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpticonService.class);
    private final OpticonServiceRestController restController;
    private final OpticonServiceWebsocketController websocketController;
    private final OpticonDataService opticonDataService;

    public OpticonService(OpticonServiceRestController restController,
                          OpticonServiceWebsocketController websocketController,
                          OpticonDataService opticonDataService)
    {
        this.restController = restController;
        this.websocketController = websocketController;
        this.opticonDataService = opticonDataService;
    }

    public void start() {
        restController.start();
        websocketController.start();
        opticonDataService.start();
        LOGGER.info("{} has started!", getClass().getSimpleName());
    }
}
