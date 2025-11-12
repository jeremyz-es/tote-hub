package com.edgestackers.opticon.gui.datamodel.cycles;

import com.edgestackers.opticon.common.datamodel.OpticonMarketCycleUpdate;
import com.edgestackers.opticon.common.datamodel.RaceEventTriggerMetadata;
import com.edgestackers.tote.hub.core.datamodel.core.ToteRaceEvent;
import jakarta.annotation.Nullable;

import java.util.Map;

import static com.edgestackers.tote.hub.core.datamodel.core.ToteRaceEvent.*;
import static com.edgestackers.tote.hub.core.util.TimeUtil.*;


public enum OpticonMarketCycleUpdateEntryConverter {
    ;
    private static final long SECOND_IN_NANOS = 1_000_000_000L;

    public static OpticonMarketCycleUpdateEntry convertOpticonMarketCycleUpdateToEntry(OpticonMarketCycleUpdate update) {
        Map<ToteRaceEvent, RaceEventTriggerMetadata> triggers = update.triggerMetadata();
        return new OpticonMarketCycleUpdateEntry(
                sydTimeFor(update.receivedAtEpochNanos(), HMS_FORMATTER),
                qldTimeFor(update.receivedAtEpochNanos(), HMS_FORMATTER),
                update.toteProvider(),
                update.totePoolJurisdiction(),
                update.raceType(),
                update.toteBetType(),
                update.toteCycleType(),
                update.esRaceId(),
                update.date(),
                update.track(),
                update.race(),
                update.poolTotal(),
                formatValue(update.timeUntilOfficialJumpNanos()),
                formatValue(update.previousCycleDeltaNanos()),
                formatValue(triggers.get(FIRST_HORSE_IN)),
                formatValue(triggers.get(THREE_TO_GO)),
                formatValue(triggers.get(TWO_TO_GO)),
                formatValue(triggers.get(LAST_HORSE_IN)),
                formatValue(triggers.get(RACE_JUMP))
        );
    }

    private static String formatValue(@Nullable RaceEventTriggerMetadata triggerMetadata) {
        if (triggerMetadata == null) return "";
        return formatSeconds((double) triggerMetadata.receivedDeltaNanos() / SECOND_IN_NANOS);
    }

    private static String formatValue(@Nullable Long epochNanosTimestamp) {
        return epochNanosTimestamp == null ? null : formatSeconds((double) epochNanosTimestamp / SECOND_IN_NANOS);
    }

    private static String formatSeconds(double seconds) {
        int totalSeconds = (int) Math.floor(seconds);
        int minutes = totalSeconds / 60;
        int remainingSeconds = totalSeconds % 60;
        return minutes > 0 ? String.format("%dm %ds", minutes, remainingSeconds) : String.format("%ds", remainingSeconds);
    }
}
