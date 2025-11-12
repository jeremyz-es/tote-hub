package com.edgestackers.pgi.services.feed.publisher;

import com.edgestackers.pgi.common.datamodel.cycle.PgiCycleUpdate;
import com.edgestackers.pgi.common.datamodel.message.PgiScratchingMessage;
import com.edgestackers.tote.hub.core.datamodel.message.ToteMarketCycleUpdate;
import com.edgestackers.tote.hub.core.datamodel.message.ToteWinMarketCycleUpdate;
import com.edgestackers.tote.hub.core.datamodel.message.scratching.ToteScratchingMessage;
import com.edgestackers.tote.hub.core.datamodel.official.ToteRaceOfficial;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PgiMessageNatsPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiMessageNatsPublisher.class);
    private static final ObjectMapper SERIALIZER = new ObjectMapper();
    private final Connection natsConnection;
    private final PgiNatsTopicContext natsTopicContext;

    public PgiMessageNatsPublisher(Connection natsConnection, PgiNatsTopicContext natsTopicContext) {
        this.natsConnection = natsConnection;
        this.natsTopicContext = natsTopicContext;
    }

    public void publish(PgiCycleUpdate pgiCycleUpdate) {
        publish(pgiCycleUpdate, natsTopicContext.pgiCycleUpdatesTopic());
    }

    public void publish(ToteMarketCycleUpdate toteMarketCycleUpdate) {
        publish(toteMarketCycleUpdate, natsTopicContext.toteMarketCycleUpdatesTopic());
    }

    public void publish(ToteWinMarketCycleUpdate winMarketCycleUpdate) {
        publish(winMarketCycleUpdate, natsTopicContext.toteWinMarketCycleUpdatesTopic());
    }

    public void publish(PgiScratchingMessage pgiScratchingMessage) {
        publish(pgiScratchingMessage, natsTopicContext.pgiScratchingTopic());
    }

    public void publish(ToteScratchingMessage toteScratchingMessage) {
        publish(toteScratchingMessage, natsTopicContext.toteScratchingTopic());
    }

    public void publish(ToteRaceOfficial toteRaceOfficial) {
        publish(toteRaceOfficial, natsTopicContext.toteRaceOfficialTopic());
    }

    private void publish(Object object, String natsTopic) {
        try {
            String message = SERIALIZER.writeValueAsString(object);
            natsConnection.publish(natsTopic, message.getBytes());
        }
        catch (JsonProcessingException e) {
            LOGGER.error("Failed to publish [{}] due to [{}] - {}", object.getClass().getSimpleName(), e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
