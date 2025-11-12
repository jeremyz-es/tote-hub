package com.edgestackers.opticon.service.message;

import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummary;
import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummaryKey;
import com.edgestackers.opticon.common.datamodel.OpticonOrderUpdate;
import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryCache;
import com.edgestackers.opticon.service.order.NitroOrderExecutionMessageCache;
import com.edgestackers.opticon.service.order.OpticonOrderUpdateFactory;
import com.edgestackers.opticon.service.order.OpticonOrderUpdateProcessor;
import com.edgestackers.opticon.service.order.ToteOrderResponseMessageCache;
import com.edgestackers.tote.hub.core.datamodel.message.nitro.NitroBetExecution;
import com.edgestackers.tote.hub.core.datamodel.message.nitro.NitroOrderExecutionMessage;
import com.edgestackers.tote.hub.core.datamodel.message.order.ToteOrderResponseMessage;
import jakarta.annotation.Nullable;

import static com.edgestackers.opticon.service.message.OrderExecutionStatusUtil.executionStatusFor;
import static com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryKeyFactory.createKey;

public class NitroOrderExecutionMessageProcessor {
    private final NitroOrderExecutionMessageCache nitroOrderExecutionMessageCache;
    private final ToteOrderResponseMessageCache toteOrderResponseMessageCache;
    private final OpticonOrderUpdateFactory opticonOrderUpdateFactory;
    private final OpticonOrderUpdateProcessor opticonOrderUpdateProcessor;
    private final OpticonStrategyContextSummaryCache opticonStrategyContextSummaryCache;
    private final OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher;

    public NitroOrderExecutionMessageProcessor(NitroOrderExecutionMessageCache nitroOrderExecutionMessageCache,
                                               ToteOrderResponseMessageCache toteOrderResponseMessageCache,
                                               OpticonOrderUpdateFactory opticonOrderUpdateFactory,
                                               OpticonOrderUpdateProcessor opticonOrderUpdateProcessor,
                                               OpticonStrategyContextSummaryCache opticonStrategyContextSummaryCache,
                                               OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher)
    {
        this.nitroOrderExecutionMessageCache = nitroOrderExecutionMessageCache;
        this.toteOrderResponseMessageCache = toteOrderResponseMessageCache;
        this.opticonOrderUpdateFactory = opticonOrderUpdateFactory;
        this.opticonOrderUpdateProcessor = opticonOrderUpdateProcessor;
        this.opticonStrategyContextSummaryCache = opticonStrategyContextSummaryCache;
        this.opticonMessageWebsocketPublisher = opticonMessageWebsocketPublisher;
    }

    public void process(NitroOrderExecutionMessage message) {
        nitroOrderExecutionMessageCache.put(message);
        processOpticonOrderUpdate(message);
        processOpticonExoticContextSummary(message);
    }

    private void processOpticonOrderUpdate(NitroOrderExecutionMessage message) {
        @Nullable OpticonOrderUpdate opticonOrderUpdate = opticonOrderUpdateFactory.createOpticonOrderUpdate(message);
        if (opticonOrderUpdate == null) return;
        opticonOrderUpdateProcessor.process(opticonOrderUpdate);
    }

    private void processOpticonExoticContextSummary(NitroOrderExecutionMessage message) {
        OpticonStrategyContextSummaryKey key = createKey(message);
        @Nullable OpticonStrategyContextSummary summary = opticonStrategyContextSummaryCache.get(key);
        // TODO: In this particular case, create a summary
        if (summary == null) return;
        @Nullable ToteOrderResponseMessage toteOrderResponseMessage = toteOrderResponseMessageCache.get(message.clientOrderId());
        if (toteOrderResponseMessage != null) {
            summary.setTurnover(message.betUpdates().stream().map(NitroBetExecution::betAmount).reduce(0.0, Double::sum));
            summary.setExecutionStatus(executionStatusFor(toteOrderResponseMessage));
        }
        opticonMessageWebsocketPublisher.publish(summary);
    }
}
