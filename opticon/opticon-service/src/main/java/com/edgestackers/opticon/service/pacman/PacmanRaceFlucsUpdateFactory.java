package com.edgestackers.opticon.service.pacman;

import com.edgestackers.tote.hub.core.datamodel.message.pacman.PacmanRaceFlucsMessage;
import com.edgestackers.tote.hub.core.datamodel.message.pacman.PacmanRunFlucsMessage;
import com.edgestackers.tote.hub.core.metadata.EsRaceMetadata;
import com.edgestackers.opticon.common.datamodel.PacmanRaceFlucsUpdate;
import com.edgestackers.opticon.common.datamodel.PacmanRunFlucsUpdate;
import com.edgestackers.opticon.service.metadata.MasterFieldsCache;
import com.edgestackers.opticon.service.metadata.RaceMetadataCache;
import com.edgestackers.tote.hub.core.metadata.MasterField;
import com.edgestackers.tote.hub.core.metadata.MasterFieldRunnerIdKey;
import jakarta.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;

public class PacmanRaceFlucsUpdateFactory {
    private final RaceMetadataCache raceMetadataCache;
    private final MasterFieldsCache masterFieldsCache;
    private final PacmanRaceFlucsUpdateCache raceFlucsUpdateCache;

    public PacmanRaceFlucsUpdateFactory(RaceMetadataCache raceMetadataCache,
                                        MasterFieldsCache masterFieldsCache,
                                        PacmanRaceFlucsUpdateCache raceFlucsUpdateCache)
    {
        this.raceMetadataCache = raceMetadataCache;
        this.masterFieldsCache = masterFieldsCache;
        this.raceFlucsUpdateCache = raceFlucsUpdateCache;
    }

    @Nullable
    public PacmanRaceFlucsUpdate createUpdate(PacmanRaceFlucsMessage raceFlucsMessage) {
        String esRaceId = raceFlucsMessage.esRaceId();
        @Nullable EsRaceMetadata raceMetadata = raceMetadataCache.get(esRaceId);
        if (raceMetadata == null) return null;
        Map<Integer, PacmanRunFlucsUpdate> updates = new HashMap<>();
        Map<String, PacmanRunFlucsMessage> pacmanRunFlucsMessages = raceFlucsMessage.runFlucs();
        for (Map.Entry<String, PacmanRunFlucsMessage> pacmanRunFlucsMessageEntry : pacmanRunFlucsMessages.entrySet()) {
            String esRunnerId = pacmanRunFlucsMessageEntry.getKey();
            PacmanRunFlucsMessage runFlucsMessage = pacmanRunFlucsMessageEntry.getValue();
            @Nullable MasterField masterField = masterFieldsCache.get(new MasterFieldRunnerIdKey(esRaceId, esRunnerId));
            if (masterField == null) continue;
            PacmanRunFlucsUpdate update = createRunFlucsUpdate(runFlucsMessage, masterField);
            updates.put(masterField.tab(), update);
        }
        @Nullable List<PacmanRaceFlucsUpdate> raceFlucs = raceFlucsUpdateCache.getFor(esRaceId);
        @Nullable Long lastFlucsUpdateDeltaSeconds = raceFlucs == null ? null : (generateEpochNanosTimestamp() - raceFlucs.getLast().epochNanosTimestamp()) / 1_000_000_000;

        return new PacmanRaceFlucsUpdate(
                raceFlucsMessage.epochNanosTimestamp(),
                raceMetadata.raceCode(),
                esRaceId,
                raceMetadata.track(),
                raceMetadata.race(),
                updates,
                lastFlucsUpdateDeltaSeconds
        );
    }

    private static PacmanRunFlucsUpdate createRunFlucsUpdate(PacmanRunFlucsMessage runFlucsMessage, MasterField masterField) {
        return new PacmanRunFlucsUpdate(
                masterField.tab(),
                masterField.runnerName(),
                runFlucsMessage.marketId(),
                runFlucsMessage.selectionId(),
                runFlucsMessage.runnerTotalMatched(),
                runFlucsMessage.marketTotalMatched(),
                runFlucsMessage.backMarketPct(),
                runFlucsMessage.layMarketPct(),
                runFlucsMessage.tradedWap(),
                runFlucsMessage.atb1p(),
                runFlucsMessage.atb2p(),
                runFlucsMessage.atb3p(),
                runFlucsMessage.atb1s(),
                runFlucsMessage.atb2s(),
                runFlucsMessage.atb3s(),
                runFlucsMessage.atl1p(),
                runFlucsMessage.atl2p(),
                runFlucsMessage.atl3p(),
                runFlucsMessage.atl1s(),
                runFlucsMessage.atl2s(),
                runFlucsMessage.atl3s(),
                runFlucsMessage.tradedTwap30s(),
                runFlucsMessage.tradedTwap60s(),
                runFlucsMessage.tradedTwap120s(),
                runFlucsMessage.tradedTwap240s(),
                runFlucsMessage.tradedTwap300s(),
                runFlucsMessage.tradedTwap600s(),
                runFlucsMessage.tradedTwap900s(),
                runFlucsMessage.tradedVolume30s(),
                runFlucsMessage.tradedVolume60s(),
                runFlucsMessage.tradedVolume120s(),
                runFlucsMessage.tradedVolume240s(),
                runFlucsMessage.tradedVolume300s(),
                runFlucsMessage.tradedVolume600s(),
                runFlucsMessage.tradedVolume900s()
        );
    }
}
