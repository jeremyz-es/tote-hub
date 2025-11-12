package com.edgestackers.opticon.gui.message;

import com.edgestackers.opticon.common.client.OpticonServiceWebsocketClient;
import com.edgestackers.opticon.common.message.OpticonMessage;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OpticonMessageSubscription {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpticonMessageSubscription.class);
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final String subscriptionEndpoint;
    private final OpticonMessageCacheRefresher opticonMessageCacheRefresher;
    private final OpticonMessageProcessor opticonMessageProcessor;
    @Nullable private OpticonServiceWebsocketClient opticonServiceWebsocketClient;

    public OpticonMessageSubscription(String subscriptionEndpoint,
                                      OpticonMessageCacheRefresher opticonMessageCacheRefresher,
                                      OpticonMessageProcessor opticonMessageProcessor) {
        this.subscriptionEndpoint = subscriptionEndpoint;
        this.opticonMessageCacheRefresher = opticonMessageCacheRefresher;
        this.opticonMessageProcessor = opticonMessageProcessor;
    }

    public void start() {
        executorService.scheduleWithFixedDelay(this::probeConnection, 1L, 15L, TimeUnit.SECONDS);
        LOGGER.info("{} has started!", getClass().getSimpleName());
    }

    private void probeConnection() {
        if (opticonServiceWebsocketClient == null || opticonServiceWebsocketClient.isClosed()) {
            connect();
        }
    }

    private void connect() {
        opticonServiceWebsocketClient = new OpticonServiceWebsocketClient(subscriptionEndpoint, this::handle, this::onDisconnect);
        opticonServiceWebsocketClient.connect();
    }

    private void handle(OpticonMessage opticonMessage) {
        opticonMessageProcessor.handle(opticonMessage);
    }

    private void onDisconnect() {
        opticonMessageCacheRefresher.refresh();
        connect();
    }
}
