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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.edgestackers.opticon.service.message.OrderExecutionStatusUtil.executionStatusFor;
import static com.edgestackers.opticon.service.operations.OpticonStrategyContextSummaryKeyFactory.createKey;

public class ToteOrderResponseMessageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToteOrderResponseMessageProcessor.class);
    private final ToteOrderResponseMessageCache orderResponseCache;
    private final OpticonOrderUpdateFactory opticonOrderUpdateFactory;
    private final OpticonOrderUpdateProcessor opticonOrderUpdateProcessor;
    private final OpticonStrategyContextSummaryCache opticonStrategyContextSummaryCache;
    private final NitroOrderExecutionMessageCache nitroOrderExecutionMessageCache;
    private final OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher;

    public ToteOrderResponseMessageProcessor(ToteOrderResponseMessageCache orderResponseCache,
                                             OpticonOrderUpdateFactory opticonOrderUpdateFactory,
                                             OpticonOrderUpdateProcessor opticonOrderUpdateProcessor,
                                             OpticonStrategyContextSummaryCache opticonStrategyContextSummaryCache,
                                             NitroOrderExecutionMessageCache nitroOrderExecutionMessageCache,
                                             OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher)
    {
        this.orderResponseCache = orderResponseCache;
        this.opticonOrderUpdateFactory = opticonOrderUpdateFactory;
        this.opticonOrderUpdateProcessor = opticonOrderUpdateProcessor;
        this.opticonStrategyContextSummaryCache = opticonStrategyContextSummaryCache;
        this.nitroOrderExecutionMessageCache = nitroOrderExecutionMessageCache;
        this.opticonMessageWebsocketPublisher = opticonMessageWebsocketPublisher;
    }

    public void process(ToteOrderResponseMessage message) {
        orderResponseCache.put(message);
        handleOpticonOrderUpdate(message);
        handleExoticContextSummary(message);
    }

    private void handleOpticonOrderUpdate(ToteOrderResponseMessage message) {
        @Nullable OpticonOrderUpdate opticonOrderUpdate = opticonOrderUpdateFactory.createOpticonOrderUpdate(message);
        if (opticonOrderUpdate == null) return;
        opticonOrderUpdateProcessor.process(opticonOrderUpdate);
    }

    private void handleExoticContextSummary(ToteOrderResponseMessage message) {
        @Nullable NitroOrderExecutionMessage nitroOrder = nitroOrderExecutionMessageCache.get(message.clientOrderId());
        if (nitroOrder == null) {
            LOGGER.error("{} received for {} but no matching {} found.", message.getClass().getSimpleName(), message.clientOrderId(), NitroOrderExecutionMessage.class.getSimpleName());
            return;
        }
        OpticonStrategyContextSummaryKey key = createKey(nitroOrder);
        @Nullable OpticonStrategyContextSummary summary = opticonStrategyContextSummaryCache.get(key);
        if (summary == null) return;
        summary.setTurnover(nitroOrder.betUpdates().stream().map(NitroBetExecution::betAmount).reduce(0.0, Double::sum));
        summary.setExecutionStatus(executionStatusFor(message));
        opticonMessageWebsocketPublisher.publish(summary);
    }

}
