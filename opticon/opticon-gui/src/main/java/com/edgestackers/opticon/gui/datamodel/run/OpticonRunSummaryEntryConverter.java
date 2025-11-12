package com.edgestackers.opticon.gui.datamodel.run;

import com.edgestackers.opticon.common.datamodel.OpticonRunSummary;
import com.edgestackers.opticon.common.datamodel.OpticonWinMarketSummary;
import com.edgestackers.core.datamodel.tote.TotePoolJurisdiction;
import jakarta.annotation.Nullable;

import java.util.Map;

import static com.edgestackers.core.util.TimeUtil.*;

public enum OpticonRunSummaryEntryConverter {
    ;

    public static OpticonRunSummaryEntry convertOpticonRunSummaryToEntry(OpticonRunSummary opticonRunSummary) {
        OpticonRunSummaryEntry entry = new OpticonRunSummaryEntry(
                opticonRunSummary.getRaceType(),
                opticonRunSummary.getDate(),
                opticonRunSummary.getEsRaceId(),
                opticonRunSummary.getEsRunnerId(),
                opticonRunSummary.getEsRunId(),
                opticonRunSummary.getTrack(),
                opticonRunSummary.getRace(),
                opticonRunSummary.getTab(),
                opticonRunSummary.getRunnerName(),
                sydTimeFor(opticonRunSummary.getLastUpdatedAtEpochNanos(), HMS_FORMATTER),
                qldTimeFor(opticonRunSummary.getLastUpdatedAtEpochNanos(), HMS_FORMATTER),
                getWinDescFor(opticonRunSummary.getWinMarketSummaries().get(TotePoolJurisdiction.NSW)),
                getWinDescFor(opticonRunSummary.getWinMarketSummaries().get(TotePoolJurisdiction.QLD)),
                getWinDescFor(opticonRunSummary.getWinMarketSummaries().get(TotePoolJurisdiction.VIC))
        );

        for (Map.Entry<String, Double> theoEntry : opticonRunSummary.getTheos().entrySet()) {
            String key = theoEntry.getKey();
            Double theoValue = theoEntry.getValue();
            String theoString = theoValue != null ? String.format("%.2f", theoValue) : "";
            entry.setTheo(key, theoString);
        }
        return entry;
    }

    private static String getWinDescFor(@Nullable OpticonWinMarketSummary winMarketSummary) {
        if (winMarketSummary == null) return "";
        return String.format("%.2f", winMarketSummary.marketTheo());
    }
}
