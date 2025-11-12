package com.edgestackers.opticon.service.run;

import com.edgestackers.opticon.common.datamodel.OpticonRunSummary;
import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import jakarta.annotation.Nullable;

public class OpticonRunSummaryProcessor {
    private final OpticonRunSummaryCache runSummaryCache;
    private final OpticonMessageWebsocketPublisher websocketPublisher;

    public OpticonRunSummaryProcessor(OpticonRunSummaryCache runSummaryCache,
                                      OpticonMessageWebsocketPublisher websocketPublisher) {
        this.runSummaryCache = runSummaryCache;
        this.websocketPublisher = websocketPublisher;
    }

    public void process(@Nullable OpticonRunSummary runSummary) {
        if (runSummary == null) return;
        runSummaryCache.put(runSummary);
        websocketPublisher.publish(runSummary);
    }
}
