package com.edgestackers.opticon.gui.datamodel.turbo;

import com.edgestackers.opticon.common.datamodel.ExoticCombinationSummary;
import com.edgestackers.tote.hub.core.datamodel.core.*;
import com.edgestackers.tote.hub.core.datamodel.turbo.TurboExoticTheoModel;
import jakarta.annotation.Nullable;

import java.util.List;

public class OpticonTheoUpdateEntry {
    private final long generatedAtEpochMillis;
    private final String generatedAtQldTime;
    private final RaceType raceType;
    private final String esRaceId;
    private final String track;
    private final int race;
    private final ToteBetType toteBetType;
    private final List<ExoticCombinationSummary> exoticCombinationSummaries;
    private final String exoticTheoId;
    private final TurboExoticTheoModel exoticTheoModel;
    private final String calculationTimeMillis;
    @Nullable private final Long lastTheoUpdateDeltaSeconds;

    public OpticonTheoUpdateEntry(long generatedAtEpochMillis,
                                  String generatedAtQldTime,
                                  RaceType raceType,
                                  String esRaceId,
                                  String track,
                                  int race,
                                  ToteBetType toteBetType,
                                  List<ExoticCombinationSummary> exoticCombinationSummaries,
                                  String exoticTheoId,
                                  TurboExoticTheoModel exoticTheoModel,
                                  String calculationTimeMillis,
                                  @Nullable Long lastTheoUpdateDeltaSeconds)
    {
        this.generatedAtEpochMillis = generatedAtEpochMillis;
        this.generatedAtQldTime = generatedAtQldTime;
        this.raceType = raceType;
        this.esRaceId = esRaceId;
        this.track = track;
        this.race = race;
        this.toteBetType = toteBetType;
        this.exoticCombinationSummaries = exoticCombinationSummaries;
        this.exoticTheoId = exoticTheoId;
        this.exoticTheoModel = exoticTheoModel;
        this.calculationTimeMillis = calculationTimeMillis;
        this.lastTheoUpdateDeltaSeconds = lastTheoUpdateDeltaSeconds;
    }

    public long getGeneratedAtEpochMillis() {
        return generatedAtEpochMillis;
    }

    public String getGeneratedAtQldTime() {
        return generatedAtQldTime;
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

    public ToteBetType getToteBetType() {
        return toteBetType;
    }

    public List<ExoticCombinationSummary> getExoticCombinationSummaries() {
        return exoticCombinationSummaries;
    }

    public String getExoticTheoId() {
        return exoticTheoId;
    }

    public TurboExoticTheoModel getExoticTheoModel() {
        return exoticTheoModel;
    }

    public String getCalculationTimeMillis() {
        return calculationTimeMillis;
    }

    @Nullable
    public Long getLastTheoUpdateDeltaSeconds() {
        return lastTheoUpdateDeltaSeconds;
    }
}
