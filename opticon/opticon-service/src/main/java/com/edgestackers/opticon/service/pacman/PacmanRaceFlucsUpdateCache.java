package com.edgestackers.opticon.service.pacman;

import com.edgestackers.opticon.common.datamodel.PacmanRaceFlucsUpdate;
import jakarta.annotation.Nullable;

import java.util.*;

public class PacmanRaceFlucsUpdateCache {
    private final Map<String, List<PacmanRaceFlucsUpdate>> pacmanRaceFlucs;

    public PacmanRaceFlucsUpdateCache() {
        this.pacmanRaceFlucs = new HashMap<>();
    }

    public void put(PacmanRaceFlucsUpdate update) {
        pacmanRaceFlucs.putIfAbsent(update.esRaceId(), new ArrayList<>());
        pacmanRaceFlucs.get(update.esRaceId()).add(update);
    }

    public List<PacmanRaceFlucsUpdate> getAll() {
        return pacmanRaceFlucs.values().stream().flatMap(Collection::stream).toList();
    }

    @Nullable
    public List<PacmanRaceFlucsUpdate> getFor(String esRaceId) {
        return pacmanRaceFlucs.get(esRaceId);
    }
}
