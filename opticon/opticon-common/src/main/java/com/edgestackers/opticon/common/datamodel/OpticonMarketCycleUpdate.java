package com.edgestackers.opticon.common.datamodel;

import com.edgestackers.opticon.common.message.OpticonMessage;
import com.edgestackers.tote.hub.core.datamodel.core.*;
import com.edgestackers.tote.hub.core.datamodel.cycle.MarketApproximate;
import com.edgestackers.tote.hub.core.datamodel.cycle.ToteCycleType;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Map;

public record OpticonMarketCycleUpdate(String feedServiceCyclePoolUpdateId,
                                       long receivedAtEpochNanos,
                                       ToteProvider toteProvider,
                                       TotePoolJurisdiction totePoolJurisdiction,
                                       RaceType raceType,
                                       ToteBetType toteBetType,
                                       ToteCycleType toteCycleType,
                                       String esRaceId,
                                       String date,
                                       String track,
                                       int race,
                                       List<MarketApproximate> marketApproximates,
                                       double poolTotal,

                                       long timeUntilOfficialJumpNanos,
                                       @Nullable Long previousCycleDeltaNanos,
                                       Map<ToteRaceEvent, RaceEventTriggerMetadata> triggerMetadata) implements OpticonMessage {
}
