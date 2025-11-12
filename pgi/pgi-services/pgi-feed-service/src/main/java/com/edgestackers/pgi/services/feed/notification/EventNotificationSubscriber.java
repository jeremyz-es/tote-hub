package com.edgestackers.pgi.services.feed.notification;

import com.edgestackers.pgi.services.common.datamodel.PgiEventServiceNotificationMessage;
import com.edgestackers.pgi.services.common.event.EventServiceWebsocketClient;
import jakarta.annotation.Nullable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EventNotificationSubscriber {
    private final String subscriptionEndpoint;
    private final EventNotificationProcessor eventNotificationProcessor;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    @Nullable private EventServiceWebsocketClient client;

    public EventNotificationSubscriber(String subscriptionEndpoint,
                                       EventNotificationProcessor eventNotificationProcessor)
    {
        this.subscriptionEndpoint = subscriptionEndpoint;
        this.eventNotificationProcessor = eventNotificationProcessor;
    }

    public void start() {
        executorService.scheduleWithFixedDelay(this::refreshConnection, 1L, 10L, TimeUnit.SECONDS);
    }

    private void refreshConnection() {
        if (client == null || client.isClosed()) {
            this.client = new EventServiceWebsocketClient(subscriptionEndpoint, this::handle);
            this.client.connect();
        }
    }

    private void handle(PgiEventServiceNotificationMessage message) {
        eventNotificationProcessor.handleNotification(message);
    }
}
