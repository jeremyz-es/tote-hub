package com.edgestackers.pgi.services.event.component;

import com.edgestackers.pgi.services.event.handler.EventsNatsPublisher;
import dagger.Module;
import dagger.Provides;
import io.nats.client.Connection;

import javax.inject.Singleton;

@Module
public class NatsModule {
    private static final String EVENTS_NATS_TOPIC = "pgi.events.service.notifications";
    private final Connection connection;

    public NatsModule(Connection connection) {
        this.connection = connection;
    }

    @Provides
    @Singleton
    public EventsNatsPublisher eventsNatsPublisher() {
        return new EventsNatsPublisher(connection, EVENTS_NATS_TOPIC);
    }
}
