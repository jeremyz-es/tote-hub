package com.edgestackers.pgi.services.event.component;

import com.edgestackers.pgi.services.event.controller.publisher.PgiEventServiceWebsocketPublisher;
import com.edgestackers.pgi.services.event.handler.*;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class EventMessageModule {

    @Provides
    @Singleton
    public NotifyCycleDataEventMessageProcessor notifyCycleDataEventMessageProcessor(PgiEventServiceWebsocketPublisher websocketPublisher) {
        return new NotifyCycleDataEventMessageProcessor(websocketPublisher);
    }

    @Provides
    @Singleton
    public NotifyRunnerScratchedEventMessageProcessor notifyRunnerScratchedEventMessageProcessor(PgiEventServiceWebsocketPublisher websocketPublisher) {
        return new NotifyRunnerScratchedEventMessageProcessor(websocketPublisher);
    }

    @Provides
    @Singleton
    public NotifyRaceOfficialEventMessageProcessor notifyRaceOfficialEventMessageProcessor(PgiEventServiceWebsocketPublisher websocketPublisher) {
        return new NotifyRaceOfficialEventMessageProcessor(websocketPublisher);
    }

    @Provides
    @Singleton
    public PgiEventMessageProcessor pgiEventMessageProcessor(NotifyCycleDataEventMessageProcessor notifyCycleDataEventMessageProcessor,
                                                             NotifyRunnerScratchedEventMessageProcessor notifyRunnerScratchedEventMessageProcessor,
                                                             NotifyRaceOfficialEventMessageProcessor notifyRaceOfficialEventMessageProcessor,
                                                             EventsNatsPublisher eventsNatsPublisher) {
        return new PgiEventMessageProcessor(
                notifyCycleDataEventMessageProcessor,
                notifyRunnerScratchedEventMessageProcessor,
                notifyRaceOfficialEventMessageProcessor,
                eventsNatsPublisher
        );
    }
}
