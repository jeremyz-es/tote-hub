package com.edgestackers.opticon.common.datamodel;

import com.edgestackers.opticon.common.message.OpticonMessage;
import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;

public class OpticonRunSummary implements OpticonMessage {
    private final RaceType raceType;
    private final String date;
    private final String esRaceId;
    private final String esRunnerId;
    private final String esRunId;
    private final String track;
    private final int race;
    private final int tab;
    private final String runnerName;

    private double fieldSize;
    private long lastUpdatedAtEpochNanos;
    private final Map<TotePoolJurisdiction, OpticonWinMarketSummary> winMarketSummaries;
    private final Map<String, Double> theos;

    public OpticonRunSummary(@JsonProperty("raceType") RaceType raceType,
                             @JsonProperty("date") String date,
                             @JsonProperty("esRaceId") String esRaceId,
                             @JsonProperty("esRunnerId") String esRunnerId,
                             @JsonProperty("esRunId") String esRunId,
                             @JsonProperty("track") String track,
                             @JsonProperty("race") int race,
                             @JsonProperty("tab") int tab,
                             @JsonProperty("runnerName") String runnerName,
                             @JsonProperty("fieldSize") int fieldSize,
                             @JsonProperty("lastUpdatedAtEpochNanos") long lastUpdatedAtEpochNanos,
                             @JsonProperty("winMarketSummaries") @Nullable Map<TotePoolJurisdiction, OpticonWinMarketSummary> winMarketSummaries,
                             @JsonProperty("theos") @Nullable Map<String, Double> theos)
    {
        this.raceType = raceType;
        this.date = date;
        this.esRaceId = esRaceId;
        this.esRunnerId = esRunnerId;
        this.esRunId = esRunId;
        this.track = track;
        this.race = race;
        this.tab = tab;
        this.runnerName = runnerName;
        this.lastUpdatedAtEpochNanos = lastUpdatedAtEpochNanos;
        this.winMarketSummaries = winMarketSummaries == null ? new HashMap<>() : winMarketSummaries;
        this.theos = theos == null ? new HashMap<>() : theos;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public String getDate() {
        return date;
    }

    public String getEsRaceId() {
        return esRaceId;
    }

    public String getEsRunnerId() {
        return esRunnerId;
    }

    public String getEsRunId() {
        return esRunId;
    }

    public String getTrack() {
        return track;
    }

    public int getRace() {
        return race;
    }

    public int getTab() {
        return tab;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public long getLastUpdatedAtEpochNanos() {
        return lastUpdatedAtEpochNanos;
    }

    public double getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(double fieldSize) {
        this.fieldSize = fieldSize;
    }

    public void setLastUpdatedAt(long lastUpdatedAtEpochNanos) {
        this.lastUpdatedAtEpochNanos = lastUpdatedAtEpochNanos;
    }

    public Map<TotePoolJurisdiction, OpticonWinMarketSummary> getWinMarketSummaries() {
        return winMarketSummaries;
    }

    public void put(TotePoolJurisdiction jurisdiction, OpticonWinMarketSummary opticonWinMarketSummary) {
        winMarketSummaries.put(jurisdiction, opticonWinMarketSummary);
        setLastUpdatedAt(generateEpochNanosTimestamp());
    }

    public Map<String, Double> getTheos() {
        return theos;
    }

    public void put(String opticonRunTheoTypeIdentifierKey, double theo) {
        theos.put(opticonRunTheoTypeIdentifierKey, theo);
        setLastUpdatedAt(generateEpochNanosTimestamp());
    }
}
