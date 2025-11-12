package com.edgestackers.opticon.gui.datamodel.turbo;

import com.edgestackers.opticon.common.datamodel.OpticonTheoUpdate;

import static com.edgestackers.tote.hub.core.util.TimeUtil.*;

public enum OpticonTheoUpdateEntryConverter {
    ;

    public static OpticonTheoUpdateEntry convertOpticonTheoUpdateToEntry(OpticonTheoUpdate update) {
        return new OpticonTheoUpdateEntry(
                update.generatedAtEpochNanos(),
                qldTimeFor(update.generatedAtEpochNanos(), HMS_FORMATTER),
                update.raceType(),
                update.esRaceId(),
                update.track(),
                update.race(),
                update.toteBetType(),
                update.exoticCombinationSummaries(),
                update.exoticTheoId(),
                update.theoModel(),
                String.format("%.2f", update.calculationTimeMillis()),
                update.lastTheoDeltaSeconds()
        );
    }
}
