package com.edgestackers.opticon.gui.datamodel.turbo;

import com.edgestackers.opticon.common.datamodel.ExoticCombinationSummary;
import com.edgestackers.tote.hub.core.datamodel.core.*;
import com.edgestackers.tote.hub.core.datamodel.turbo.*;
import jakarta.annotation.Nullable;

import java.util.List;

public class OpticonDividendPredictionUpdateEntry {
    private final long generatedAtEpochMillis;
    private final String generatedAtQldTime;
    private final String cyclePoolUpdateId;
    private final RaceType raceType;
    private final TotePoolJurisdiction jurisdiction;
    private final ToteProvider provider;
    private final String date;
    private final String esRaceId;
    private final String track;
    private final int race;
    private final String marketPoolTotal;
    private final String predictedPoolTotal;
    private final ToteBetType toteBetType;
    private final List<ExoticCombinationSummary> exoticCombinationSummaries;
    private final String exoticDividendPredictionId;
    private final TurboExoticDividendModel exoticDividendModel;
    private final String calculationTimeMillis;
    @Nullable private final Long lastDividendUpdateDeltaSeconds;

    public OpticonDividendPredictionUpdateEntry(long generatedAtEpochMillis,
                                                String generatedAtQldTime,
                                                String cyclePoolUpdateId,
                                                RaceType raceType,
                                                TotePoolJurisdiction jurisdiction,
                                                ToteProvider provider,
                                                String date,
                                                String esRaceId,
                                                String track,
                                                int race,
                                                String marketPoolTotal,
                                                String predictedPoolTotal,
                                                ToteBetType toteBetType,
                                                List<ExoticCombinationSummary> exoticCombinationSummaries,
                                                String exoticDividendPredictionId,
                                                TurboExoticDividendModel exoticDividendModel,
                                                String calculationTimeMillis,
                                                @Nullable Long lastDividendUpdateDeltaSeconds)
    {
        this.generatedAtEpochMillis = generatedAtEpochMillis;
        this.generatedAtQldTime = generatedAtQldTime;
        this.cyclePoolUpdateId = cyclePoolUpdateId;
        this.raceType = raceType;
        this.jurisdiction = jurisdiction;
        this.provider = provider;
        this.date = date;
        this.esRaceId = esRaceId;
        this.track = track;
        this.race = race;
        this.marketPoolTotal = marketPoolTotal;
        this.predictedPoolTotal = predictedPoolTotal;
        this.toteBetType = toteBetType;
        this.exoticCombinationSummaries = exoticCombinationSummaries;
        this.exoticDividendPredictionId = exoticDividendPredictionId;
        this.exoticDividendModel = exoticDividendModel;
        this.calculationTimeMillis = calculationTimeMillis;
        this.lastDividendUpdateDeltaSeconds = lastDividendUpdateDeltaSeconds;
    }

    public long getGeneratedAtEpochMillis() {
        return generatedAtEpochMillis;
    }

    public String getGeneratedAtQldTime() {
        return generatedAtQldTime;
    }

    public String getCyclePoolUpdateId() {
        return cyclePoolUpdateId;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public TotePoolJurisdiction getJurisdiction() {
        return jurisdiction;
    }

    public ToteProvider getProvider() {
        return provider;
    }

    public String getDate() {
        return date;
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

    public String getMarketPoolTotal() {
        return marketPoolTotal;
    }

    public String getPredictedPoolTotal() {
        return predictedPoolTotal;
    }

    public ToteBetType getToteBetType() {
        return toteBetType;
    }

    public List<ExoticCombinationSummary> getExoticCombinationSummaries() {
        return exoticCombinationSummaries;
    }

    public String getExoticDividendPredictionId() {
        return exoticDividendPredictionId;
    }

    public TurboExoticDividendModel getExoticDividendModel() {
        return exoticDividendModel;
    }

    public String getCalculationTimeMillis() {
        return calculationTimeMillis;
    }

    @Nullable
    public Long getLastDividendUpdateDeltaSeconds() {
        return lastDividendUpdateDeltaSeconds;
    }
}
