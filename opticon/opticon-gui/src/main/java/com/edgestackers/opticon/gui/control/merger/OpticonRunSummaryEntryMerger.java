package com.edgestackers.opticon.gui.control.merger;

import com.edgestackers.opticon.gui.datamodel.run.OpticonRunSummaryEntry;

public enum OpticonRunSummaryEntryMerger {
    ;

    public static void merge(OpticonRunSummaryEntry existing, OpticonRunSummaryEntry newEntry) {
        existing.setLastUpdatedAtQldTime(newEntry.getLastUpdatedAtQldTime());
        existing.setLastUpdatedAtSydTime(newEntry.getLastUpdatedAtSydTime());
        existing.setNswDesc(newEntry.getNswDesc());
        existing.setQldDesc(newEntry.getQldDesc());
        existing.setVicDesc(newEntry.getVicDesc());

        for (String key : newEntry.getTheoColumns().keySet()) {
            String theoValue = newEntry.getTheo(key);
            existing.setTheo(key, theoValue);
        }
    }
}
