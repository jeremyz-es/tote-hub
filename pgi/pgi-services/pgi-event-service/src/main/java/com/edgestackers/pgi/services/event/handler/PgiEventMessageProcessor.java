package com.edgestackers.pgi.services.event.handler;

import com.edgestackers.pgi.services.event.datamodel.api.NotifyCycleDataEventMessage;
import com.edgestackers.pgi.services.event.datamodel.api.NotifyRaceOfficialEventMessage;
import com.edgestackers.pgi.services.event.datamodel.api.NotifyRunnerScratchedEventMessage;
import com.edgestackers.pgi.services.event.datamodel.api.PgiEventMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PgiEventMessageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiEventMessageProcessor.class);
    private final NotifyCycleDataEventMessageProcessor notifyCycleDataEventMessageProcessor;
    private final NotifyRunnerScratchedEventMessageProcessor notifyRunnerScratchedEventMessageProcessor;
    private final NotifyRaceOfficialEventMessageProcessor notifyRaceOfficialEventMessageProcessor;
    private final EventsNatsPublisher eventsNatsPublisher;

    public PgiEventMessageProcessor(NotifyCycleDataEventMessageProcessor notifyCycleDataEventMessageProcessor,
                                    NotifyRunnerScratchedEventMessageProcessor notifyRunnerScratchedEventMessageProcessor,
                                    NotifyRaceOfficialEventMessageProcessor notifyRaceOfficialEventMessageProcessor,
                                    EventsNatsPublisher eventsNatsPublisher) {
        this.notifyCycleDataEventMessageProcessor = notifyCycleDataEventMessageProcessor;
        this.notifyRunnerScratchedEventMessageProcessor = notifyRunnerScratchedEventMessageProcessor;
        this.notifyRaceOfficialEventMessageProcessor = notifyRaceOfficialEventMessageProcessor;
        this.eventsNatsPublisher = eventsNatsPublisher;
    }

    public void handle(PgiEventMessage pgiEventMessage) {
        if (pgiEventMessage instanceof NotifyCycleDataEventMessage message) notifyCycleDataEventMessageProcessor.handle(message);
        if (pgiEventMessage instanceof NotifyRunnerScratchedEventMessage message) notifyRunnerScratchedEventMessageProcessor.process(message);
        if (pgiEventMessage instanceof NotifyRaceOfficialEventMessage message) notifyRaceOfficialEventMessageProcessor.handle(message);
        eventsNatsPublisher.publish(pgiEventMessage);
    }
}
