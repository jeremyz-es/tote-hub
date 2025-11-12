package com.edgestackers.pgi.services.event;

import com.edgestackers.pgi.services.event.controller.PgiEventServiceRestController;
import com.edgestackers.pgi.services.event.controller.PgiEventServiceWebsocketController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PgiEventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiEventService.class);
    private final PgiEventServiceRestController restController;
    private final PgiEventServiceWebsocketController websocketController;

    public PgiEventService(PgiEventServiceRestController restController,
                           PgiEventServiceWebsocketController websocketController)
    {
        this.restController = restController;
        this.websocketController = websocketController;
    }

    public void start() {
        restController.start();
        websocketController.start();
        LOGGER.info("[{}] has started!", getClass().getSimpleName());
    }
}
