package com.edgestackers.opticon.gui.datamodel.parameters;

import com.edgestackers.tote.hub.core.parameters.ToteStrategyParameters;

import static com.edgestackers.tote.hub.core.util.TimeUtil.*;

public enum ToteStrategyParametersEntryConverter {
    ;

    public static ToteStrategyParametersEntry convertToteStrategyParametersToEntry(ToteStrategyParameters toteStrategyParameters) {
        return new ToteStrategyParametersEntry(
                toteStrategyParameters.strategyId(),
                toteStrategyParameters.strategyName(),
                qldTimeFor(toteStrategyParameters.createdAtUtcNanos(), YMDHMS_FORMATTER),
                qldTimeFor(toteStrategyParameters.lastUpdatedAtEpochNanos(), YMDHMS_FORMATTER),
                toteStrategyParameters.raceType(),
                toteStrategyParameters.toteProvider(),
                toteStrategyParameters.toteBetType(),
                toteStrategyParameters.jurisdiction(),
                toteStrategyParameters.maxRaceBet(),
                toteStrategyParameters.rebate(),
                toteStrategyParameters.takeout(),
                toteStrategyParameters.discount(),
                toteStrategyParameters.minBet(),
                toteStrategyParameters.minPoolSize(),
                toteStrategyParameters.maxPoolSize(),
                toteStrategyParameters.turboExoticDividendModel(),
                toteStrategyParameters.dividendModelMajorVersion(),
                toteStrategyParameters.dividendModelMinorVersion(),
                String.format("%s v%s.%s", toteStrategyParameters.turboExoticDividendModel(), toteStrategyParameters.dividendModelMajorVersion(), toteStrategyParameters.dividendModelMinorVersion()),
                toteStrategyParameters.turboExoticTheoModel(),
                toteStrategyParameters.theoModelMajorVersion(),
                toteStrategyParameters.theoModelMinorVersion(),
                String.format("%s v%s.%s", toteStrategyParameters.turboExoticTheoModel(), toteStrategyParameters.theoModelMajorVersion(), toteStrategyParameters.theoModelMinorVersion()),
                toteStrategyParameters.maxDividendAgeSeconds(),
                toteStrategyParameters.maxTheoAgeSeconds(),
                toteStrategyParameters.triggerRaceEvents()
        );
    }
}

