package com.edgestackers.opticon.service.order;

import com.edgestackers.opticon.common.datamodel.OpticonBetStatus;
import com.edgestackers.opticon.common.datamodel.OpticonBetUpdate;
import com.edgestackers.opticon.common.datamodel.OpticonOrderStatus;
import com.edgestackers.opticon.common.datamodel.OpticonOrderUpdate;
import com.edgestackers.tote.hub.core.datamodel.message.nitro.NitroBetExecution;
import com.edgestackers.tote.hub.core.datamodel.message.nitro.NitroOrderExecutionMessage;
import com.edgestackers.tote.hub.core.datamodel.message.order.ToteOrderResponseMessage;
import jakarta.annotation.Nullable;

import java.util.List;

import static com.edgestackers.opticon.service.order.OpticonBetUpdateFactory.createOpticonBetUpdates;
import static com.edgestackers.opticon.service.order.OpticonBetUpdateFactory.createOpticonBetUpdatesUnknownStatus;

public class OpticonOrderUpdateFactory {
    private final NitroOrderExecutionMessageCache orderExecutionMessageCache;
    private final ToteOrderResponseMessageCache orderResponseCache;

    public OpticonOrderUpdateFactory(NitroOrderExecutionMessageCache orderExecutionMessageCache,
                                     ToteOrderResponseMessageCache orderResponseCache)
    {
        this.orderExecutionMessageCache = orderExecutionMessageCache;
        this.orderResponseCache = orderResponseCache;
    }

    @Nullable
    public OpticonOrderUpdate createOpticonOrderUpdate(ToteOrderResponseMessage toteOrderResponseMessage) {
        String clientOrderId = toteOrderResponseMessage.clientOrderId();
        @Nullable NitroOrderExecutionMessage orderExecutionMessage = orderExecutionMessageCache.get(clientOrderId);
        if (orderExecutionMessage == null) {
            return null;
        }
        List<OpticonBetUpdate> opticonBetUpdates = createOpticonBetUpdates(orderExecutionMessage.betUpdates(), toteOrderResponseMessage.toteBetResponses());
        double totalOrderAmount = opticonBetUpdates.stream().map(OpticonBetUpdate::betAmount).reduce(0.0, Double::sum);
        double totalAcceptedOrderAmount = opticonBetUpdates.stream().filter(bet -> bet.opticonBetStatus() == OpticonBetStatus.ACCEPTED).map(OpticonBetUpdate::betAmount).reduce(0.0, Double::sum);
        OpticonOrderStatus orderStatus =
                opticonBetUpdates.stream().allMatch(update -> update.opticonBetStatus() == OpticonBetStatus.POOL_CLOSED) ? OpticonOrderStatus.POOL_CLOSED
                : opticonBetUpdates.stream().allMatch(update -> update.opticonBetStatus() == OpticonBetStatus.REJECTED) ? OpticonOrderStatus.REJECTED
                : opticonBetUpdates.stream().allMatch(update -> update.opticonBetStatus() == OpticonBetStatus.ACCEPTED) ? OpticonOrderStatus.ACCEPTED : OpticonOrderStatus.ANOMALOUS;
        return new OpticonOrderUpdate(
                clientOrderId,
                orderExecutionMessage.jurisdiction(),
                orderExecutionMessage.betType(),
                orderExecutionMessage.esRaceId(),
                opticonBetUpdates,
                orderExecutionMessage.marketTotalPoolSize(),
                orderExecutionMessage.predictedTotalPoolSize(),
                orderExecutionMessage.orderGenerationEpochMillisTimestamp(),
                orderExecutionMessage.raceCode(),
                orderExecutionMessage.track(),
                orderExecutionMessage.race(),
                orderExecutionMessage.raceTrigger(),
                orderExecutionMessage.toteProvider(),
                orderExecutionMessage.exoticTheoId(),
                orderExecutionMessage.exoticDividendPredictionId(),
                totalOrderAmount,
                totalAcceptedOrderAmount,
                orderStatus,
                orderExecutionMessage.strategyId(),
                orderExecutionMessage.strategyName()
        );
    }

    @Nullable
    public OpticonOrderUpdate createOpticonOrderUpdate(NitroOrderExecutionMessage orderExecutionMessage) {
        String clientOrderId = orderExecutionMessage.clientOrderId();
        double totalOrderAmount = orderExecutionMessage.betUpdates().stream().map(NitroBetExecution::betAmount).reduce(0.0, Double::sum);
        @Nullable ToteOrderResponseMessage toteOrderResponseMessage = orderResponseCache.get(clientOrderId);
        if (toteOrderResponseMessage == null) {
            return new OpticonOrderUpdate(
                    clientOrderId,
                    orderExecutionMessage.jurisdiction(),
                    orderExecutionMessage.betType(),
                    orderExecutionMessage.esRaceId(),
                    createOpticonBetUpdatesUnknownStatus(orderExecutionMessage.betUpdates()),
                    orderExecutionMessage.marketTotalPoolSize(),
                    orderExecutionMessage.predictedTotalPoolSize(),
                    orderExecutionMessage.orderGenerationEpochMillisTimestamp(),
                    orderExecutionMessage.raceCode(),
                    orderExecutionMessage.track(),
                    orderExecutionMessage.race(),
                    orderExecutionMessage.raceTrigger(),
                    orderExecutionMessage.toteProvider(),
                    orderExecutionMessage.exoticTheoId(),
                    orderExecutionMessage.exoticDividendPredictionId(),
                    totalOrderAmount,
                    0.0d,
                    OpticonOrderStatus.IN_FLIGHT,
                    orderExecutionMessage.strategyId(),
                    orderExecutionMessage.strategyName()
            );
        }
        return createOpticonOrderUpdate(toteOrderResponseMessage);
    }
}
