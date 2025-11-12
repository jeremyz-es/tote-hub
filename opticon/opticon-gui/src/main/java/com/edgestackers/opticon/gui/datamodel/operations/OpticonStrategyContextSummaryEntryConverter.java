package com.edgestackers.opticon.gui.datamodel.operations;

import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummary;

import static com.edgestackers.tote.hub.core.util.TimeUtil.HMS_FORMATTER;
import static com.edgestackers.tote.hub.core.util.TimeUtil.qldTimeFor;

public enum OpticonStrategyContextSummaryEntryConverter {
    ;

    public static OpticonStrategyContextSummaryEntry convertOpticonExoticSummaryToEntry(OpticonStrategyContextSummary summary) {
        return new OpticonStrategyContextSummaryEntry(
                summary.getRaceStartTimeEpochNanos(),
                summary.getLastUpdatedAtEpochNanos(),
                qldTimeFor(summary.getLastUpdatedAtEpochNanos(), HMS_FORMATTER),
                summary.getStrategyId(),
                summary.getStrategyName(),
                summary.getDividendModel(),
                summary.getTheoModel(),
                summary.getRaceType(),
                summary.getToteBetType(),
                summary.getTotePoolJurisdiction(),
                summary.getEsRaceId(),
                summary.getTrack(),
                summary.getRace(),
                summary.getRaceStatus(),
                summary.getCyclesStatus(),
                summary.getTurboDividendStatus(),
                summary.getTurboTheoStatus(),
                summary.getNitroStatus(),
                summary.getExecutionStatus(),
                summary.getWinningSelection(),
                summary.getPayout(),
                summary.getTurnover(),
                summary.getProfit()
        );
    }
}
