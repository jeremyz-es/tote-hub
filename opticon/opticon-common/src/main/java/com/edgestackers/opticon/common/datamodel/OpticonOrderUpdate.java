package com.edgestackers.opticon.common.datamodel;

import com.edgestackers.opticon.common.message.OpticonMessage;
import com.edgestackers.tote.hub.core.datamodel.core.*;

import java.util.List;

public record OpticonOrderUpdate(String clientOrderId,
                                 TotePoolJurisdiction jurisdiction,
                                 ToteBetType betType,
                                 String esRaceId,
                                 List<OpticonBetUpdate> betUpdates,
                                 double marketTotalPoolSize,
                                 double predictedTotalPoolSize,
                                 long orderGenerationEpochMillisTimestamp,
                                 RaceCode raceType,
                                 String track,
                                 int race,
                                 ToteRaceEvent raceEventTrigger,
                                 ToteProvider toteProvider,
                                 String exoticTheoId,
                                 String exoticDividendPredictionId,
                                 double totalOrderAmount,
                                 double totalAcceptedOrderAmount,
                                 OpticonOrderStatus opticonOrderStatus,
                                 String strategyId,
                                 String strategyName) implements OpticonMessage {
}
