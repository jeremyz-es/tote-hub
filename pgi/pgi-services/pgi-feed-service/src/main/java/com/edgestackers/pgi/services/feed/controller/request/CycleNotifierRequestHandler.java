package com.edgestackers.pgi.services.feed.controller.request;

import com.edgestackers.pgi.services.common.datamodel.CycleDataUpdatedNotificationMessage;
import com.edgestackers.pgi.services.feed.notification.CycleDataUpdatedNotificationProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used to manually push cycle notification events to trigger refreshes
 */
public class CycleNotifierRequestHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CycleNotifierRequestHandler.class);
    private static final ObjectMapper DESERIALIZER = new ObjectMapper();
    private final CycleDataUpdatedNotificationProcessor notificationProcessor;

    public CycleNotifierRequestHandler(CycleDataUpdatedNotificationProcessor notificationProcessor) {
        this.notificationProcessor = notificationProcessor;
    }

    public void handle(Context context) {
        try {
            CycleDataUpdatedNotificationMessage notificationMessage = DESERIALIZER.readValue(context.body(), CycleDataUpdatedNotificationMessage.class);
            notificationProcessor.handle(notificationMessage);
        }
        catch (Exception e) {
            LOGGER.error("Failed to handle manual cycle event push - Request Body: {} due to {} - {}",
                    context.body(),
                    e.getClass().getSimpleName(),
                    e.getMessage()
            );
        }
    }
}
