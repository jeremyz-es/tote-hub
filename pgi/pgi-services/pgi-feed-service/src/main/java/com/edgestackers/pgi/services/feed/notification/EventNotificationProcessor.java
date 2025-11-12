package com.edgestackers.pgi.services.feed.notification;

import com.edgestackers.pgi.services.common.datamodel.CycleDataUpdatedNotificationMessage;
import com.edgestackers.pgi.services.common.datamodel.PgiEventServiceNotificationMessage;
import com.edgestackers.pgi.services.common.datamodel.RaceOfficialNotificationMessage;
import com.edgestackers.pgi.services.common.datamodel.RunnerScratchedNotificationMessage;
import com.edgestackers.pgi.services.common.event.PgiEventServiceNotificationMessageHandler;

public class EventNotificationProcessor implements PgiEventServiceNotificationMessageHandler {
    private final CycleDataUpdatedNotificationProcessor cycleDataUpdatedNotificationProcessor;
    private final RunnerScratchedNotificationMessageProcessor runnerScratchedNotificationMessageProcessor;
    private final RaceOfficialNotificationMessageProcessor raceOfficialNotificationMessageProcessor;

    public EventNotificationProcessor(CycleDataUpdatedNotificationProcessor cycleDataUpdatedNotificationProcessor,
                                      RunnerScratchedNotificationMessageProcessor runnerScratchedNotificationMessageProcessor,
                                      RaceOfficialNotificationMessageProcessor raceOfficialNotificationMessageProcessor)
    {
        this.cycleDataUpdatedNotificationProcessor = cycleDataUpdatedNotificationProcessor;
        this.runnerScratchedNotificationMessageProcessor = runnerScratchedNotificationMessageProcessor;
        this.raceOfficialNotificationMessageProcessor = raceOfficialNotificationMessageProcessor;
    }

    @Override
    public void handleNotification(PgiEventServiceNotificationMessage notificationMessage) {
        if (notificationMessage instanceof CycleDataUpdatedNotificationMessage message) cycleDataUpdatedNotificationProcessor.handle(message);
        if (notificationMessage instanceof RunnerScratchedNotificationMessage message) runnerScratchedNotificationMessageProcessor.process(message);
        if (notificationMessage instanceof RaceOfficialNotificationMessage message) raceOfficialNotificationMessageProcessor.process(message);
    }
}
