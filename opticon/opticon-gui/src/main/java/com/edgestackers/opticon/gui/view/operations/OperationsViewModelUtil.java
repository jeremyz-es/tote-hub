package com.edgestackers.opticon.gui.view.operations;

import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummary;
import com.edgestackers.opticon.gui.datamodel.operations.OpticonStrategyContextSummaryEntry;

enum OperationsViewModelUtil {
    ;

    static void updateEntry(OpticonStrategyContextSummaryEntry entry, OpticonStrategyContextSummary summary) {
        entry.setRaceStatus(summary.getRaceStatus());
        entry.setCyclesStatus(summary.getCyclesStatus());
        entry.setTurboDividendStatus(summary.getTurboDividendStatus());
        entry.setTurboTheoStatus(summary.getTurboTheoStatus());
        entry.setNitroStatus(summary.getNitroStatus());
        entry.setExecutionStatus(summary.getExecutionStatus());
        entry.setTurnover(summary.getTurnover());
        entry.setWinningSelection(summary.getWinningSelection());
        entry.setPayout(summary.getPayout());
        entry.setProfit(summary.getProfit());
        entry.setEpochNanosTimestamp(summary.getLastUpdatedAtEpochNanos());
    }
}
