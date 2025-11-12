package com.edgestackers.opticon.service.run;

import com.edgestackers.opticon.common.datamodel.OpticonRunSummary;
import com.edgestackers.opticon.common.datamodel.OpticonWinMarketSummary;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.metadata.MasterField;
import jakarta.annotation.Nullable;

import java.util.Map;

import static com.edgestackers.tote.hub.core.datamodel.core.RaceCode.convertToRaceType;
import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;

public class OpticonRunSummaryFactory {
    private final OpticonRunSummaryCache runSummaryCache;
    private final OpticonRunTheoCache runTheoCache;
    private final OpticonWinMarketSummaryCache winMarketSummaryCache;

    public OpticonRunSummaryFactory(OpticonRunSummaryCache runSummaryCache,
                                    OpticonRunTheoCache runTheoCache,
                                    OpticonWinMarketSummaryCache winMarketSummaryCache)
    {
        this.runSummaryCache = runSummaryCache;
        this.runTheoCache = runTheoCache;
        this.winMarketSummaryCache = winMarketSummaryCache;
    }

    @Nullable
    public OpticonRunSummary createOpticonRunSummary(MasterField masterField) {
        OpticonRunSummaryKey key = new OpticonRunSummaryKey(masterField.esRaceId(), masterField.tab());
        if (runSummaryCache.contains(key)) return null;
        @Nullable Map<TotePoolJurisdiction, OpticonWinMarketSummary> winMarketSummaries = winMarketSummaryCache.get(key);
        @Nullable Map<String, Double> theos = runTheoCache.get(key);
        return new OpticonRunSummary(
                convertToRaceType(masterField.esRaceCode()),
                masterField.date(),
                masterField.esRaceId(),
                masterField.esRunnerId(),
                masterField.esRunId(),
                masterField.track(),
                masterField.race(),
                masterField.tab(),
                masterField.runnerName(),
                masterField.fieldSize(),
                generateEpochNanosTimestamp(),
                winMarketSummaries,
                theos
        );
    }
}
