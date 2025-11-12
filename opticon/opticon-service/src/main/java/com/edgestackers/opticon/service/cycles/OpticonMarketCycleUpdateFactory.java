package com.edgestackers.opticon.service.cycles;

import com.edgestackers.opticon.common.datamodel.OpticonMarketCycleUpdate;
import com.edgestackers.opticon.common.datamodel.RaceEventTriggerMetadata;
import com.edgestackers.opticon.service.metadata.RaceEventUpdateRegistry;
import com.edgestackers.opticon.service.metadata.RaceMetadataCache;
import com.edgestackers.tote.hub.core.datamodel.core.ToteRaceEvent;
import com.edgestackers.tote.hub.core.datamodel.message.RaceEventUpdateMessage;
import com.edgestackers.tote.hub.core.datamodel.message.ToteMarketCycleUpdate;
import com.edgestackers.tote.hub.core.metadata.EsRaceMetadata;
import jakarta.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.edgestackers.opticon.service.cycles.OpticonMarketCycleUpdateKeyFactory.createKey;
import static com.edgestackers.tote.hub.core.datamodel.core.RaceCode.convertToRaceType;

public class OpticonMarketCycleUpdateFactory {
    private final OpticonMarketCycleUpdateCache updateCache;
    private final RaceEventUpdateRegistry raceEventUpdateRegistry;
    private final RaceMetadataCache raceMetadataCache;

    public OpticonMarketCycleUpdateFactory(OpticonMarketCycleUpdateCache updateCache,
                                           RaceEventUpdateRegistry raceEventUpdateRegistry,
                                           RaceMetadataCache raceMetadataCache) {
        this.updateCache = updateCache;
        this.raceEventUpdateRegistry = raceEventUpdateRegistry;
        this.raceMetadataCache = raceMetadataCache;
    }

    @Nullable
    public OpticonMarketCycleUpdateCreationResult createUpdate(ToteMarketCycleUpdate toteUpdate) {
        long updateNanos = toteUpdate.receivedAtEpochNanos();
        String esRaceId = toteUpdate.esRaceId();
        OpticonMarketCycleUpdateKey key = createKey(toteUpdate);
        List<OpticonMarketCycleUpdate> updates = updateCache.get(key);
        @Nullable EsRaceMetadata esRaceMetadata = raceMetadataCache.get(esRaceId);
        @Nullable Long previousCycleDeltaNanos = updates.isEmpty() ? null : updateNanos - updates.getLast().receivedAtEpochNanos();
        @Nullable Long timeUntilOfficialJumpNanos = esRaceMetadata == null ? null : esRaceMetadata.raceStartTimeUtcNanos() - updateNanos;
        if (timeUntilOfficialJumpNanos == null) {
            return null;
        }
        Map<ToteRaceEvent, RaceEventUpdateMessage> raceEvents = raceEventUpdateRegistry.getFor(esRaceId);
        Map<ToteRaceEvent, RaceEventTriggerMetadata> triggerMetadata = new HashMap<>();
        for (Map.Entry<ToteRaceEvent, RaceEventUpdateMessage> entry : raceEvents.entrySet()) {
            ToteRaceEvent triggerType = entry.getKey();
            RaceEventUpdateMessage message = entry.getValue();
            long receivedEpochNanos = message.receivedEpochNanoTs();
            long serverEpochNanos = message.serverEpochNanoTs();
            long receivedDeltaNanos = updateNanos - receivedEpochNanos;
            long serverDeltaNanos = updateNanos - serverEpochNanos;
            triggerMetadata.put(triggerType, new RaceEventTriggerMetadata(receivedDeltaNanos, serverDeltaNanos));
        }
        OpticonMarketCycleUpdate update = new OpticonMarketCycleUpdate(
                toteUpdate.feedServiceCyclePoolUpdateId(),
                toteUpdate.receivedAtEpochNanos(),
                toteUpdate.toteProvider(),
                toteUpdate.totePoolJurisdiction(),
                convertToRaceType(toteUpdate.raceCode()),
                toteUpdate.toteBetType(),
                toteUpdate.toteCycleType(),
                toteUpdate.esRaceId(),
                toteUpdate.date(),
                toteUpdate.track(),
                toteUpdate.race(),
                toteUpdate.marketApproximates(),
                toteUpdate.poolTotal(),
                timeUntilOfficialJumpNanos,
                previousCycleDeltaNanos,
                triggerMetadata
        );
        return new OpticonMarketCycleUpdateCreationResult(update, timeUntilOfficialJumpNanos);
    }
}
