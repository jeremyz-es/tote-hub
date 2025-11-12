package com.edgestackers.pgi.services.event.handler;

import com.edgestackers.pgi.services.common.datamodel.CycleDataUpdatedNotificationMessage;
import com.edgestackers.pgi.services.common.datamodel.PgiTotePoolId;
import com.edgestackers.pgi.services.event.controller.publisher.PgiEventServiceWebsocketPublisher;
import com.edgestackers.pgi.services.event.datamodel.api.NotifyCycleDataEventMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotifyCycleDataEventMessageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyCycleDataEventMessageProcessor.class);
    private final PgiEventServiceWebsocketPublisher websocketPublisher;

    public NotifyCycleDataEventMessageProcessor(PgiEventServiceWebsocketPublisher websocketPublisher) {
        this.websocketPublisher = websocketPublisher;
    }

    public void handle(NotifyCycleDataEventMessage message) {
        try {
            PgiTotePoolId totePoolId = PgiTotePoolId.valueOf(message.getToteId());
            CycleDataUpdatedNotificationMessage eventMessage = new CycleDataUpdatedNotificationMessage(
                    totePoolId,
                    message.getProgramName(),
                    message.getRace()
            );
            websocketPublisher.publish(eventMessage);
        }
        catch (Exception e) {
            LOGGER.error("No {} found for Cycle Data Event: Tote Pool Id = [{}]", PgiTotePoolId.class.getSimpleName(), message.getToteId());
        }
    }
}
