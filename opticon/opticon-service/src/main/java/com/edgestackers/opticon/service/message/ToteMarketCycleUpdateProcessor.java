package com.edgestackers.opticon.service.message;

import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummary;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCache;
import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.cycles.OpticonMarketCycleUpdateCache;
import com.edgestackers.opticon.service.cycles.OpticonMarketCycleUpdateCreationResult;
import com.edgestackers.opticon.service.cycles.OpticonMarketCycleUpdateFactory;
import com.edgestackers.tote.hub.core.datamodel.context.ToteMarketCycleStatus;
import com.edgestackers.tote.hub.core.datamodel.message.ToteMarketCycleUpdate;
import jakarta.annotation.Nullable;

import java.util.List;

import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;

public class ToteMarketCycleUpdateProcessor {
    private static final long TEN_MINUTES_IN_NANOS = 10 * 60 * 1_000_000_000L;
    private final OpticonMarketCycleUpdateFactory opticonMarketCycleUpdateFactory;
    private final OpticonMarketCycleUpdateCache updateCache;
    private final OpticonStrategyContextSummaryCache exoticContextSummaryCache;
    private final OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher;

    public ToteMarketCycleUpdateProcessor(OpticonMarketCycleUpdateFactory opticonMarketCycleUpdateFactory,
                                          OpticonMarketCycleUpdateCache updateCache,
                                          OpticonStrategyContextSummaryCache exoticContextSummaryCache,
                                          OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher)
    {
        this.opticonMarketCycleUpdateFactory = opticonMarketCycleUpdateFactory;
        this.updateCache = updateCache;
        this.exoticContextSummaryCache = exoticContextSummaryCache;
        this.opticonMessageWebsocketPublisher = opticonMessageWebsocketPublisher;
    }

    public void process(ToteMarketCycleUpdate toteMarketCycleUpdate) {
        createAndProcessOpticonMarketCycleUpdate(toteMarketCycleUpdate);
        createAndProcessExoticContextSummaryUpdate(toteMarketCycleUpdate);
    }

    private void createAndProcessOpticonMarketCycleUpdate(ToteMarketCycleUpdate toteMarketCycleUpdate) {
        @Nullable OpticonMarketCycleUpdateCreationResult update = opticonMarketCycleUpdateFactory.createUpdate(toteMarketCycleUpdate);
        if (update == null) return;
        opticonMessageWebsocketPublisher.publish(update.update());
        if (update.timeUntilOfficialJumpNanos() > TEN_MINUTES_IN_NANOS) return;
        updateCache.put(update.update());
    }

    private void createAndProcessExoticContextSummaryUpdate(ToteMarketCycleUpdate toteMarketCycleUpdate) {
        List<OpticonStrategyContextSummary> strategyContexts = exoticContextSummaryCache.getAll();
        for (OpticonStrategyContextSummary strategyContextSummary : strategyContexts) {
            if (strategyContextSummary.getEsRaceId().equals(toteMarketCycleUpdate.esRaceId())
                    && strategyContextSummary.getToteBetType() == toteMarketCycleUpdate.toteBetType()
                    && strategyContextSummary.getTotePoolJurisdiction() == toteMarketCycleUpdate.totePoolJurisdiction())
            {
                strategyContextSummary.setLastUpdatedAtEpochNanos(generateEpochNanosTimestamp());
                strategyContextSummary.setCyclesStatus(ToteMarketCycleStatus.HEALTHY);
                opticonMessageWebsocketPublisher.publish(strategyContextSummary);
            }
        }
    }
}
