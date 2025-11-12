package com.edgestackers.opticon.service.message;

import com.edgestackers.opticon.common.datamodel.OpticonDividendPredictionUpdate;
import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummary;
import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCache;
import com.edgestackers.opticon.service.turbo.OpticonDividendPredictionUpdateCache;
import com.edgestackers.opticon.service.turbo.OpticonDividendPredictionUpdateFactory;
import com.edgestackers.tote.hub.core.datamodel.context.TurboPricingStatus;
import com.edgestackers.tote.hub.core.datamodel.message.turbo.ExoticDividendPredictionMessage;
import jakarta.annotation.Nullable;

import java.util.List;

import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;

public class ExoticDividendMessageProcessor {
    private final OpticonDividendPredictionUpdateFactory updateFactory;
    private final OpticonDividendPredictionUpdateCache updateCache;
    private final OpticonStrategyContextSummaryCache exoticContextSummaryCache;
    private final OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher;

    public ExoticDividendMessageProcessor(OpticonDividendPredictionUpdateFactory updateFactory,
                                          OpticonDividendPredictionUpdateCache updateCache,
                                          OpticonStrategyContextSummaryCache exoticContextSummaryCache,
                                          OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher)
    {
        this.updateFactory = updateFactory;
        this.updateCache = updateCache;
        this.exoticContextSummaryCache = exoticContextSummaryCache;
        this.opticonMessageWebsocketPublisher = opticonMessageWebsocketPublisher;
    }

    public void process(ExoticDividendPredictionMessage dividendPrediction) {
        createAndProcessOpticonDivUpdate(dividendPrediction);
        createAndProcessExoticSummaryUpdate(dividendPrediction);
    }

    private void createAndProcessOpticonDivUpdate(ExoticDividendPredictionMessage dividendPredictionMessage) {
        @Nullable OpticonDividendPredictionUpdate update = updateFactory.createUpdate(dividendPredictionMessage);
        if (update == null) return;
        updateCache.put(update);
        opticonMessageWebsocketPublisher.publish(update);
    }

    private void createAndProcessExoticSummaryUpdate(ExoticDividendPredictionMessage message) {

        List<OpticonStrategyContextSummary> strategyContexts = exoticContextSummaryCache.getAll();
        for (OpticonStrategyContextSummary strategyContextSummary : strategyContexts) {
            boolean strategyMatches =
                    strategyContextSummary.getDividendModel() == message.dividendModel()
                    && strategyContextSummary.getDividendModelMajorVersion() == message.dividendModelMajorVersion()
                    && strategyContextSummary.getTheoModelMinorVersion() == message.dividendModelMinorVersion();
            if (strategyMatches) {
                createAndProcessNewExoticContextSummaryUpdate(strategyContextSummary);
            }
        }
    }

    private void createAndProcessNewExoticContextSummaryUpdate(OpticonStrategyContextSummary contextSummary) {
        contextSummary.setTurboDividendStatus(TurboPricingStatus.OK);
        contextSummary.setLastUpdatedAtEpochNanos(generateEpochNanosTimestamp());
        opticonMessageWebsocketPublisher.publish(contextSummary);
    }
}
