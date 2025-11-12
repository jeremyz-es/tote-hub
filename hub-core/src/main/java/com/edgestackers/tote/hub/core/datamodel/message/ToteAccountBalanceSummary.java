package com.edgestackers.tote.hub.core.datamodel.message;

import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;

public record ToteAccountBalanceSummary(ToteProvider toteProvider,
                                        TotePoolJurisdiction jurisdiction,
                                        ToteAccountBalanceType toteAccountType,
                                        double balance,
                                        long epochNanosTimestamp) implements ToteMessage {
}
