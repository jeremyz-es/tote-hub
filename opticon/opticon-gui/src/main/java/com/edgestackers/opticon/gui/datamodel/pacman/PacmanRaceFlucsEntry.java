package com.edgestackers.opticon.gui.datamodel.pacman;

import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import jakarta.annotation.Nullable;

import java.util.List;

public class PacmanRaceFlucsEntry {
    private final long epochNanosTimestamp;
    private final String qldTime;
    private final RaceType raceType;
    private final String esRaceId;
    private final String track;
    private final int race;
    private final List<PacmanRunFlucsEntry> runFlucs;
    @Nullable private final Long lastFlucsUpdateDeltaSeconds;

    public PacmanRaceFlucsEntry(long epochNanosTimestamp,
                                String qldTime,
                                RaceType raceType,
                                String esRaceId,
                                String track,
                                int race,
                                List<PacmanRunFlucsEntry> runFlucs,
                                @Nullable Long lastFlucsUpdateDeltaSeconds)
    {
        this.epochNanosTimestamp = epochNanosTimestamp;
        this.qldTime = qldTime;
        this.raceType = raceType;
        this.esRaceId = esRaceId;
        this.track = track;
        this.race = race;
        this.runFlucs = runFlucs;
        this.lastFlucsUpdateDeltaSeconds = lastFlucsUpdateDeltaSeconds;
    }

    public long getEpochNanosTimestamp() {
        return epochNanosTimestamp;
    }

    public String getQldTime() {
        return qldTime;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public String getEsRaceId() {
        return esRaceId;
    }

    public String getTrack() {
        return track;
    }

    public int getRace() {
        return race;
    }

    public List<PacmanRunFlucsEntry> getRunFlucs() {
        return runFlucs;
    }

    @Nullable
    public Long getLastFlucsUpdateDeltaSeconds() {
        return lastFlucsUpdateDeltaSeconds;
    }
}
