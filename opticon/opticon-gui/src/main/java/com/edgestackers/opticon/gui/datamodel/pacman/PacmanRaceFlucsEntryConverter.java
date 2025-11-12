package com.edgestackers.opticon.gui.datamodel.pacman;

import com.edgestackers.opticon.common.datamodel.PacmanRaceFlucsUpdate;
import com.edgestackers.opticon.common.datamodel.PacmanRunFlucsUpdate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.edgestackers.tote.hub.core.datamodel.core.RaceCode.convertToRaceType;
import static com.edgestackers.tote.hub.core.util.TimeUtil.*;


public enum PacmanRaceFlucsEntryConverter {
    ;

    public static PacmanRaceFlucsEntry convertPacmanRaceFlucsUpdateToEntry(PacmanRaceFlucsUpdate update) {
        return new PacmanRaceFlucsEntry(
                update.epochNanosTimestamp(),
                qldTimeFor(update.epochNanosTimestamp(), HMS_FORMATTER),
                convertToRaceType(update.raceType()),
                update.esRaceId(),
                update.track(),
                update.race(),
                convertPacmanRunFlucsUpdatesToEntries(update.pacmanRunFlucsUpdates().values()),
                update.lastFlucsUpdateDeltaSeconds()
        );
    }

    private static List<PacmanRunFlucsEntry> convertPacmanRunFlucsUpdatesToEntries(Collection<PacmanRunFlucsUpdate> updates) {
        List<PacmanRunFlucsEntry> entries = new ArrayList<>();
        updates.forEach(update -> entries.add(convertPacmanRunFlucUpdateToEntry(update)));
        return entries;
    }

    private static PacmanRunFlucsEntry convertPacmanRunFlucUpdateToEntry(PacmanRunFlucsUpdate runFlucsUpdate) {
        return new PacmanRunFlucsEntry(
                runFlucsUpdate.tab(),
                runFlucsUpdate.runnerName(),
                runFlucsUpdate.marketId(),
                runFlucsUpdate.selectionId(),
                runFlucsUpdate.runnerTotalMatched(),
                runFlucsUpdate.marketTotalMatched(),
                String.format("%.2f", runFlucsUpdate.backMarketPct()),
                runFlucsUpdate.layMarketPct(),
                String.format("%.2f", runFlucsUpdate.tradedWap()),
                String.format("%.2f", runFlucsUpdate.atb1p()),
                String.format("%.2f", runFlucsUpdate.atb2p()),
                String.format("%.2f", runFlucsUpdate.atb3p()),
                String.format("%.2f", runFlucsUpdate.atb1s()),
                String.format("%.2f", runFlucsUpdate.atb2s()),
                String.format("%.2f", runFlucsUpdate.atb3s()),
                String.format("%.2f", runFlucsUpdate.atl1p()),
                String.format("%.2f", runFlucsUpdate.atl2p()),
                String.format("%.2f", runFlucsUpdate.atl3p()),
                String.format("%.2f", runFlucsUpdate.atl1s()),
                String.format("%.2f", runFlucsUpdate.atl2s()),
                String.format("%.2f", runFlucsUpdate.atl3s()),
                String.format("%.2f", runFlucsUpdate.tradedTwap30s()),
                String.format("%.2f", runFlucsUpdate.tradedTwap60s()),
                String.format("%.2f", runFlucsUpdate.tradedTwap120s()),
                String.format("%.2f", runFlucsUpdate.tradedTwap240s()),
                String.format("%.2f", runFlucsUpdate.tradedTwap300s()),
                String.format("%.2f", runFlucsUpdate.tradedTwap600s()),
                String.format("%.2f", runFlucsUpdate.tradedTwap900s()),
                String.format("%.2f", runFlucsUpdate.tradedVolume30s()),
                String.format("%.2f", runFlucsUpdate.tradedVolume60s()),
                String.format("%.2f", runFlucsUpdate.tradedVolume120s()),
                String.format("%.2f", runFlucsUpdate.tradedVolume240s()),
                String.format("%.2f", runFlucsUpdate.tradedVolume300s()),
                String.format("%.2f", runFlucsUpdate.tradedVolume600s()),
                String.format("%.2f", runFlucsUpdate.tradedVolume900s())
        );
    }
}
