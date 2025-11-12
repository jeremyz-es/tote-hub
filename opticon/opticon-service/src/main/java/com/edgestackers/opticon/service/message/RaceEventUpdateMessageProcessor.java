package com.edgestackers.opticon.service.message;

import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummary;
import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.metadata.RaceEventUpdateRegistry;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCache;
import com.edgestackers.tote.hub.core.datamodel.message.RaceEventUpdateMessage;

import java.util.List;

import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;


public class RaceEventUpdateMessageProcessor {
    private final RaceEventUpdateRegistry raceEventUpdateRegistry;
    private final OpticonStrategyContextSummaryCache opticonStrategyContextSummaryCache;
    private final OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher;

    public RaceEventUpdateMessageProcessor(RaceEventUpdateRegistry raceEventUpdateRegistry,
                                           OpticonStrategyContextSummaryCache opticonStrategyContextSummaryCache,
                                           OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher)
    {
        this.raceEventUpdateRegistry = raceEventUpdateRegistry;
        this.opticonStrategyContextSummaryCache = opticonStrategyContextSummaryCache;
        this.opticonMessageWebsocketPublisher = opticonMessageWebsocketPublisher;
    }

    public void process(RaceEventUpdateMessage raceEventUpdateMessage) {
        raceEventUpdateRegistry.submit(raceEventUpdateMessage);
        // TODO: Trigger update for all cycles for this race, also publish this message
        createAndPublishExoticContextSummaryUpdates(raceEventUpdateMessage);
    }

    private void createAndPublishExoticContextSummaryUpdates(RaceEventUpdateMessage message) {
        List<OpticonStrategyContextSummary> summaries = opticonStrategyContextSummaryCache.getAll();
        for (OpticonStrategyContextSummary summary : summaries) {
            if (summary.getEsRaceId().equals(message.esRaceId())) {
                summary.setRaceStatus(message.ToteRaceEvent());
                summary.setLastUpdatedAtEpochNanos(generateEpochNanosTimestamp());
                opticonMessageWebsocketPublisher.publish(summary);
            }
        }
    }
}
