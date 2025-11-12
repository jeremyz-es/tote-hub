package com.edgestackers.opticon.common.datamodel;

import java.util.List;

public record OpticonBetUpdate(String clientBetId,
                               List<Integer> selections,
                               double betAmount,
                               double predictedMarketProb,
                               double predictedMarketPrice,
                               double theoProb,
                               double theoPrice,
                               OpticonBetStatus opticonBetStatus) {
}
