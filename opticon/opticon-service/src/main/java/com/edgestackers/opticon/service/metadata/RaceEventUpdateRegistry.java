package com.edgestackers.opticon.service.metadata;


import com.edgestackers.tote.hub.core.datamodel.core.ToteRaceEvent;
import com.edgestackers.tote.hub.core.datamodel.message.RaceEventUpdateMessage;

import java.util.HashMap;
import java.util.Map;

public class RaceEventUpdateRegistry {
    private final Map<String, Map<ToteRaceEvent, RaceEventUpdateMessage>> registries;

    public RaceEventUpdateRegistry() {
        registries = new HashMap<>();
    }

    public void submit(RaceEventUpdateMessage raceEventUpdateMessage) {
        String esRaceId = raceEventUpdateMessage.esRaceId();
        registries.putIfAbsent(esRaceId, new HashMap<>());
        Map<ToteRaceEvent, RaceEventUpdateMessage> raceEvents = registries.get(esRaceId);
        raceEvents.put(raceEventUpdateMessage.ToteRaceEvent(), raceEventUpdateMessage);
    }

    public Map<ToteRaceEvent, RaceEventUpdateMessage> getFor(String esRaceId) {
        return registries.getOrDefault(esRaceId, new HashMap<>());
    }
}
