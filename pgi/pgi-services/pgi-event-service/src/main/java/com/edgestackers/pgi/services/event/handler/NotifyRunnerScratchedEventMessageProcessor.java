package com.edgestackers.pgi.services.event.handler;

import com.edgestackers.pgi.services.common.datamodel.PgiTotePoolId;
import com.edgestackers.pgi.services.common.datamodel.RunnerScratchedNotificationMessage;
import com.edgestackers.pgi.services.event.controller.publisher.PgiEventServiceWebsocketPublisher;
import com.edgestackers.pgi.services.event.datamodel.api.NotifyRunnerScratchedEventMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotifyRunnerScratchedEventMessageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyRunnerScratchedEventMessageProcessor.class);
    private final PgiEventServiceWebsocketPublisher websocketPublisher;

    public NotifyRunnerScratchedEventMessageProcessor(PgiEventServiceWebsocketPublisher websocketPublisher) {
        this.websocketPublisher = websocketPublisher;
    }

    public void process(NotifyRunnerScratchedEventMessage message) {
        try {
            PgiTotePoolId totePoolId = PgiTotePoolId.valueOf(message.getToteId());
            RunnerScratchedNotificationMessage eventMessage = new RunnerScratchedNotificationMessage(
                    totePoolId,
                    message.getProgramName(),
                    message.getRace(),
                    message.getRunner()
            );
            websocketPublisher.publish(eventMessage);
        }
        catch (Exception e) {
            LOGGER.error("No {} found for Cycle Data Event: Tote Pool Id = [{}]", PgiTotePoolId.class.getSimpleName(), message.getToteId());
        }
    }
}
