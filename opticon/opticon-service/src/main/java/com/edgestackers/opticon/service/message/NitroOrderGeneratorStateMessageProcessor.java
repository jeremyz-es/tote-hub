package com.edgestackers.opticon.service.message;

import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummary;
import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummaryKey;
import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCache;
import com.edgestackers.tote.hub.core.datamodel.message.nitro.NitroOrderGeneratorStateMessage;
import jakarta.annotation.Nullable;

import static com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryKeyFactory.createKey;
import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;

public class NitroOrderGeneratorStateMessageProcessor {
    private final OpticonStrategyContextSummaryCache exoticContextSummaryCache;
    private final OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher;

    public NitroOrderGeneratorStateMessageProcessor(OpticonStrategyContextSummaryCache exoticContextSummaryCache,
                                                    OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher)
    {
        this.exoticContextSummaryCache = exoticContextSummaryCache;
        this.opticonMessageWebsocketPublisher = opticonMessageWebsocketPublisher;
    }

    public void process(NitroOrderGeneratorStateMessage message) {
        OpticonStrategyContextSummaryKey key = createKey(message);
        @Nullable OpticonStrategyContextSummary summary = exoticContextSummaryCache.get(key);
        if (summary == null) return;
        summary.setNitroStatus(message.nitroContextStatus());
        summary.setLastUpdatedAtEpochNanos(generateEpochNanosTimestamp());
        opticonMessageWebsocketPublisher.publish(summary);
    }
}
