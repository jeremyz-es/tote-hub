package com.edgestackers.opticon.gui.datamodel.order;

import com.edgestackers.opticon.common.datamodel.OpticonOrderStatus;
import com.edgestackers.tote.hub.core.datamodel.core.*;

import java.util.List;

public class OpticonOrderUpdateEntry {
    private final long epochMillisTimestamp;
    private final String orderGeneratedAtQldTime;
    private final String clientOrderId;
    private final String strategyId;
    private final String strategyName;
    private final TotePoolJurisdiction jurisdiction;
    private final ToteBetType betType;
    private final String esRaceId;
    private final ToteProvider toteProvider;
    private final String track;
    private final int race;
    private final List<OpticonBetUpdateEntry> betEntries;
    private final String marketTotalPoolSize;
    private final String predictedTotalPoolSize;
    private final String orderAmount;
    private final String orderAmountAccepted;
    private final ToteRaceEvent raceEventTrigger;
    private final String exoticTheoId;
    private final String exoticDividendId;
    private final OpticonOrderStatus opticonOrderStatus;

    public OpticonOrderUpdateEntry(long epochMillisTimestamp,
                                   String clientOrderId,
                                   String strategyId,
                                   String strategyName,
                                   TotePoolJurisdiction jurisdiction,
                                   ToteBetType betType,
                                   String esRaceId,
                                   ToteProvider toteProvider,
                                   String track,
                                   int race,
                                   List<OpticonBetUpdateEntry> betEntries,
                                   String marketTotalPoolSize,
                                   String predictedTotalPoolSize,
                                   String orderAmount,
                                   String orderAmountAccepted,
                                   String orderGeneratedAtQldTime,
                                   ToteRaceEvent raceEventTrigger,
                                   String exoticTheoId,
                                   String exoticDividendId,
                                   OpticonOrderStatus opticonOrderStatus)
    {
        this.epochMillisTimestamp = epochMillisTimestamp;
        this.clientOrderId = clientOrderId;
        this.strategyId = strategyId;
        this.strategyName = strategyName;
        this.jurisdiction = jurisdiction;
        this.betType = betType;
        this.esRaceId = esRaceId;
        this.toteProvider = toteProvider;
        this.track = track;
        this.race = race;
        this.betEntries = betEntries;
        this.marketTotalPoolSize = marketTotalPoolSize;
        this.predictedTotalPoolSize = predictedTotalPoolSize;
        this.orderAmount = orderAmount;
        this.orderAmountAccepted = orderAmountAccepted;
        this.orderGeneratedAtQldTime = orderGeneratedAtQldTime;
        this.raceEventTrigger = raceEventTrigger;
        this.exoticTheoId = exoticTheoId;
        this.exoticDividendId = exoticDividendId;
        this.opticonOrderStatus = opticonOrderStatus;
    }

    public long getEpochMillisTimestamp() {
        return epochMillisTimestamp;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public String getStrategyId() {
        return strategyId;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public TotePoolJurisdiction getJurisdiction() {
        return jurisdiction;
    }

    public ToteBetType getBetType() {
        return betType;
    }

    public String getEsRaceId() {
        return esRaceId;
    }

    public ToteProvider getToteProvider() {
        return toteProvider;
    }

    public String getTrack() {
        return track;
    }

    public int getRace() {
        return race;
    }

    public List<OpticonBetUpdateEntry> getBetEntries() {
        return betEntries;
    }

    public String getMarketTotalPoolSize() {
        return marketTotalPoolSize;
    }

    public String getPredictedTotalPoolSize() {
        return predictedTotalPoolSize;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public String getOrderAmountAccepted() {
        return orderAmountAccepted;
    }

    public String getOrderGeneratedAtQldTime() {
        return orderGeneratedAtQldTime;
    }

    public ToteRaceEvent getRaceEventTrigger() {
        return raceEventTrigger;
    }

    public String getExoticTheoId() {
        return exoticTheoId;
    }

    public String getExoticDividendId() {
        return exoticDividendId;
    }

    public OpticonOrderStatus getOpticonOrderStatus() {
        return opticonOrderStatus;
    }
}
