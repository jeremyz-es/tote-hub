package com.edgestackers.opticon.service.message;

import com.edgestackers.opticon.common.datamodel.*;
import com.edgestackers.opticon.service.metadata.RaceEventUpdateRegistry;
import com.edgestackers.opticon.service.run.OpticonRunSummaryCache;
import com.edgestackers.opticon.service.run.OpticonRunSummaryKey;
import com.edgestackers.opticon.service.run.OpticonRunTheoCache;
import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCache;
import com.edgestackers.opticon.service.turbo.OpticonTheoUpdateCache;
import com.edgestackers.opticon.service.turbo.OpticonTheoUpdateFactory;
import com.edgestackers.tote.hub.core.datamodel.context.TurboPricingStatus;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.ToteRaceEvent;
import com.edgestackers.tote.hub.core.datamodel.message.RaceEventUpdateMessage;
import com.edgestackers.tote.hub.core.datamodel.message.turbo.ExoticTheoMessage;
import com.edgestackers.tote.hub.core.datamodel.turbo.TurboExoticTheoModel;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Map;

import static com.edgestackers.opticon.common.util.OpticonRunTheoTypeIdentifierFactory.createRunTheoTypeIdentifier;
import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;

public class ExoticTheoMessageProcessor {
    private final OpticonTheoUpdateFactory updateFactory;
    private final OpticonTheoUpdateCache updateCache;
    private final OpticonRunSummaryCache runSummaryCache;
    private final RaceEventUpdateRegistry raceEventUpdateRegistry;
    private final OpticonRunTheoCache opticonRunTheoCache;
    private final OpticonStrategyContextSummaryCache exoticContextSummaryCache;
    private final OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher;

    public ExoticTheoMessageProcessor(OpticonTheoUpdateFactory updateFactory,
                                      OpticonTheoUpdateCache updateCache,
                                      OpticonRunSummaryCache runSummaryCache,
                                      RaceEventUpdateRegistry raceEventUpdateRegistry,
                                      OpticonRunTheoCache opticonRunTheoCache,
                                      OpticonStrategyContextSummaryCache exoticContextSummaryCache,
                                      OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher)
    {
        this.updateFactory = updateFactory;
        this.updateCache = updateCache;
        this.runSummaryCache = runSummaryCache;
        this.raceEventUpdateRegistry = raceEventUpdateRegistry;
        this.opticonRunTheoCache = opticonRunTheoCache;
        this.exoticContextSummaryCache = exoticContextSummaryCache;
        this.opticonMessageWebsocketPublisher = opticonMessageWebsocketPublisher;
    }

    public void process(ExoticTheoMessage theoMessage) {
        createAndProcessTheoUpdate(theoMessage);
        createAndProcessNewExoticContextSummaryUpdate(theoMessage);
        createAndProcessRunSummaryTheos(theoMessage);
    }

    private void createAndProcessTheoUpdate(ExoticTheoMessage exoticTheoMessage) {
        OpticonTheoUpdate update = updateFactory.createUpdate(exoticTheoMessage);
        updateCache.put(update);
        opticonMessageWebsocketPublisher.publish(update);
    }

    private void createAndProcessNewExoticContextSummaryUpdate(ExoticTheoMessage exoticTheoMessage) {
        List<OpticonStrategyContextSummary> strategyContexts = exoticContextSummaryCache.getAll();
        for (OpticonStrategyContextSummary strategyContextSummary : strategyContexts) {
            boolean strategyMatches =
                    strategyContextSummary.getTheoModel() == exoticTheoMessage.exoticTheoModel()
                    && strategyContextSummary.getTheoModelMajorVersion() == exoticTheoMessage.theoModelMajorVersion()
                    && strategyContextSummary.getTheoModelMinorVersion() == exoticTheoMessage.theoModelMinorVersion();
            if (strategyMatches) {
                createAndProcessNewExoticContextSummaryUpdate(strategyContextSummary);
            }
        }
    }

    private void createAndProcessNewExoticContextSummaryUpdate(OpticonStrategyContextSummary contextSummary) {
        contextSummary.setTurboTheoStatus(TurboPricingStatus.OK);
        contextSummary.setLastUpdatedAtEpochNanos(generateEpochNanosTimestamp());
        opticonMessageWebsocketPublisher.publish(contextSummary);
    }

    private void createAndProcessRunSummaryTheos(ExoticTheoMessage theoMessage) {
        String esRaceId = theoMessage.esRaceId();
        if (theoMessage.betType() != ToteBetType.WIN && theoMessage.betType() != ToteBetType.PLACE) return;
        Map<ToteRaceEvent, RaceEventUpdateMessage> raceEvents = raceEventUpdateRegistry.getFor(esRaceId);
        if (raceEvents.containsKey(ToteRaceEvent.TWO_TO_GO) || raceEvents.containsKey(ToteRaceEvent.RACE_JUMP)) return;
        TurboExoticTheoModel theoModel = theoMessage.exoticTheoModel();
        int majorVersion = theoMessage.theoModelMajorVersion();
        int minorVersion = theoMessage.theoModelMinorVersion();
        List<OpticonRunSummary> runSummaries = runSummaryCache.getAll().stream().filter(runSummary -> runSummary.getEsRaceId().equals(esRaceId)).toList();
        Map<String, Double> probabilities = theoMessage.probabilities();
        for (OpticonRunSummary runSummary : runSummaries) {
            OpticonRunSummaryKey summaryKey = new OpticonRunSummaryKey(runSummary.getEsRaceId(), runSummary.getTab());
            String theoTypeIdentifier = createRunTheoTypeIdentifier(theoModel, majorVersion, minorVersion);
            @Nullable Double probability = probabilities.get(String.valueOf(runSummary.getTab()));
            if (probability == null || probability == 0.0) continue;
            runSummary.put(theoTypeIdentifier, 1 / probability);
            opticonRunTheoCache.put(summaryKey, theoTypeIdentifier, 1 / probability);
            opticonMessageWebsocketPublisher.publish(runSummary);
        }
    }
}
