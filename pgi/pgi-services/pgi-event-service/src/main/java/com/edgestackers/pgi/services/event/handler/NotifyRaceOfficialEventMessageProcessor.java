package com.edgestackers.pgi.services.event.handler;

import com.edgestackers.pgi.services.common.datamodel.PgiTotePoolId;
import com.edgestackers.pgi.services.common.datamodel.RaceOfficialNotificationMessage;
import com.edgestackers.pgi.services.event.controller.publisher.PgiEventServiceWebsocketPublisher;
import com.edgestackers.pgi.services.event.datamodel.api.NotifyRaceOfficialEventMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotifyRaceOfficialEventMessageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyRaceOfficialEventMessageProcessor.class);
    private final PgiEventServiceWebsocketPublisher websocketPublisher;

    public NotifyRaceOfficialEventMessageProcessor(PgiEventServiceWebsocketPublisher websocketPublisher) {
        this.websocketPublisher = websocketPublisher;
    }

    public void handle(NotifyRaceOfficialEventMessage message) {
        LOGGER.info("Received {} for {} {}.", message.getClass().getSimpleName(), message.getProgramName(), message.getRace());
        try {
            PgiTotePoolId totePoolId = PgiTotePoolId.valueOf(message.getToteId());
            RaceOfficialNotificationMessage eventMessage = new RaceOfficialNotificationMessage(
                    totePoolId,
                    message.getProgramName(),
                    message.getRace()
            );
            websocketPublisher.publish(eventMessage);
        }
        catch (Exception e) {
            LOGGER.error("No {} found for Notify Race Official Event: Tote Pool Id = [{}]", PgiTotePoolId.class.getSimpleName(), message.getToteId());
        }
    }
}
