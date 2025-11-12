package com.edgestackers.opticon.service.operations;

import com.edgestackers.opticon.common.datamodel.*;
import com.edgestackers.tote.hub.core.datamodel.context.*;
import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import com.edgestackers.tote.hub.core.datamodel.core.ToteRaceEvent;
import com.edgestackers.tote.hub.core.parameters.ToteStrategyParameters;

import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;


public enum OpticonStrategyContextSummaryFactory {
    ;
    private static final String UNRESULTED_WINNING_SELECTION = "";
    private static final double UNRESULTED_PAYOUT = 0.0;
    private static final double UNRESULTED_TURNOVER = 0.0;
    private static final double UNRESULTED_PROFIT = 0.0;

    public static OpticonStrategyContextSummary createDefaultOpticonExoticContextSummary(long raceStartTimeEpochNanos,
                                                                                         ToteStrategyParameters toteStrategyParameters,
                                                                                         RaceType raceType,
                                                                                         String esRaceId,
                                                                                         String track,
                                                                                         int race)
    {
        return new OpticonStrategyContextSummary(
                generateEpochNanosTimestamp(),
                raceStartTimeEpochNanos,
                toteStrategyParameters.strategyId(),
                toteStrategyParameters.strategyName(),
                raceType,
                esRaceId,
                track,
                race,
                toteStrategyParameters.turboExoticDividendModel(),
                toteStrategyParameters.dividendModelMajorVersion(),
                toteStrategyParameters.dividendModelMinorVersion(),
                toteStrategyParameters.turboExoticTheoModel(),
                toteStrategyParameters.theoModelMajorVersion(),
                toteStrategyParameters.theoModelMinorVersion(),
                toteStrategyParameters.toteBetType(),
                toteStrategyParameters.jurisdiction(),
                ToteRaceEvent.RACE_STATUS,
                ToteMarketCycleStatus.AWAITING,
                TurboPricingStatus.AWAITING,
                TurboPricingStatus.AWAITING,
                NitroContextStatus.AWAITING_PRICING,
                OrderGatewayExecutionStatus.AWAITING_ORDER,
                UNRESULTED_WINNING_SELECTION,
                UNRESULTED_PAYOUT,
                UNRESULTED_TURNOVER,
                UNRESULTED_PROFIT
        );
    }
}
