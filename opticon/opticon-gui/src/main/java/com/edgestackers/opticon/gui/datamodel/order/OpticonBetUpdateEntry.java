package com.edgestackers.opticon.gui.datamodel.order;

import com.edgestackers.opticon.common.datamodel.OpticonBetStatus;

public class OpticonBetUpdateEntry {
    private final String selections;
    private final String selectionPool;
    private final String betAmount;
    private final String predictedMarketProbability;
    private final String predictedMarketPrice;
    private final String theoProb;
    private final String theoPrice;
    private final String clientBetId;
    private final OpticonBetStatus opticonBetStatus;

    public OpticonBetUpdateEntry(String selections,
                                 String selectionPool,
                                 String betAmount,
                                 String predictedMarketProbability,
                                 String predictedMarketPrice,
                                 String theoProb,
                                 String theoPrice,
                                 String clientBetId,
                                 OpticonBetStatus opticonBetStatus)
    {
        this.selections = selections;
        this.selectionPool = selectionPool;
        this.betAmount = betAmount;
        this.predictedMarketProbability = predictedMarketProbability;
        this.predictedMarketPrice = predictedMarketPrice;
        this.theoProb = theoProb;
        this.theoPrice = theoPrice;
        this.clientBetId = clientBetId;
        this.opticonBetStatus = opticonBetStatus;
    }

    public String getSelections() {
        return selections;
    }

    public String getSelectionPool() {
        return selectionPool;
    }

    public String getBetAmount() {
        return betAmount;
    }

    public String getPredictedMarketProbability() {
        return predictedMarketProbability;
    }

    public String getPredictedMarketPrice() {
        return predictedMarketPrice;
    }

    public String getTheoProb() {
        return theoProb;
    }

    public String getTheoPrice() {
        return theoPrice;
    }

    public String getClientBetId() {
        return clientBetId;
    }

    public OpticonBetStatus getOpticonBetStatus() {
        return opticonBetStatus;
    }
}