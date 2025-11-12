package com.edgestackers.opticon.gui.datamodel.cycles;


import com.edgestackers.tote.hub.core.datamodel.core.*;
import com.edgestackers.tote.hub.core.datamodel.cycle.ToteCycleType;

public class OpticonMarketCycleUpdateEntry {
    private final String receivedAtSydTime;
    private final String receivedAtQldTime;
    private final ToteProvider provider;
    private final TotePoolJurisdiction jurisdiction;
    private final RaceType raceType;
    private final ToteBetType toteBetType;
    private final ToteCycleType toteCycleType;
    private final String esRaceId;
    private final String date;
    private final String track;
    private final int race;
    private final double poolTotal;

    private final String officialRaceJumpDeltaSeconds;
    private final String previousCycleDeltaSeconds;
    private final String firstHorseInDeltaSeconds;
    private final String threeToGoDeltaSeconds;
    private final String twoToGoDeltaSeconds;
    private final String lastHorseInDeltaSeconds;
    private final String raceJumpedDeltaSeconds;

    public OpticonMarketCycleUpdateEntry(String receivedAtSydTime,
                                         String receivedAtQldTime,
                                         ToteProvider provider,
                                         TotePoolJurisdiction jurisdiction,
                                         RaceType raceType,
                                         ToteBetType toteBetType,
                                         ToteCycleType toteCycleType,
                                         String esRaceId,
                                         String date,
                                         String track,
                                         int race,
                                         double poolTotal,
                                         String officialRaceJumpDeltaSeconds,
                                         String previousCycleDeltaSeconds,
                                         String firstHorseInDeltaSeconds,
                                         String threeToGoDeltaSeconds,
                                         String twoToGoDeltaSeconds,
                                         String lastHorseInDeltaSeconds,
                                         String raceJumpedDeltaSeconds)
    {
        this.receivedAtSydTime = receivedAtSydTime;
        this.receivedAtQldTime = receivedAtQldTime;
        this.provider = provider;
        this.jurisdiction = jurisdiction;
        this.raceType = raceType;
        this.toteBetType = toteBetType;
        this.toteCycleType = toteCycleType;
        this.esRaceId = esRaceId;
        this.date = date;
        this.track = track;
        this.race = race;
        this.poolTotal = poolTotal;
        this.officialRaceJumpDeltaSeconds = officialRaceJumpDeltaSeconds;
        this.previousCycleDeltaSeconds = previousCycleDeltaSeconds;
        this.firstHorseInDeltaSeconds = firstHorseInDeltaSeconds;
        this.threeToGoDeltaSeconds = threeToGoDeltaSeconds;
        this.twoToGoDeltaSeconds = twoToGoDeltaSeconds;
        this.lastHorseInDeltaSeconds = lastHorseInDeltaSeconds;
        this.raceJumpedDeltaSeconds = raceJumpedDeltaSeconds;
    }

    public String getReceivedAtSydTime() {
        return receivedAtSydTime;
    }

    public String getReceivedAtQldTime() {
        return receivedAtQldTime;
    }

    public ToteProvider getProvider() {
        return provider;
    }

    public TotePoolJurisdiction getJurisdiction() {
        return jurisdiction;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public ToteBetType getToteBetType() {
        return toteBetType;
    }

    public String getEsRaceId() {
        return esRaceId;
    }

    public String getDate() {
        return date;
    }

    public String getTrack() {
        return track;
    }

    public int getRace() {
        return race;
    }

    public double getPoolTotal() {
        return poolTotal;
    }

    public String getOfficialRaceJumpDeltaSeconds() {
        return officialRaceJumpDeltaSeconds;
    }

    public String getPreviousCycleDeltaSeconds() {
        return previousCycleDeltaSeconds;
    }

    public String getFirstHorseInDeltaSeconds() {
        return firstHorseInDeltaSeconds;
    }

    public String getThreeToGoDeltaSeconds() {
        return threeToGoDeltaSeconds;
    }

    public String getTwoToGoDeltaSeconds() {
        return twoToGoDeltaSeconds;
    }

    public String getLastHorseInDeltaSeconds() {
        return lastHorseInDeltaSeconds;
    }

    public String getRaceJumpedDeltaSeconds() {
        return raceJumpedDeltaSeconds;
    }

    public ToteCycleType getToteCycleType() {
        return toteCycleType;
    }
}
