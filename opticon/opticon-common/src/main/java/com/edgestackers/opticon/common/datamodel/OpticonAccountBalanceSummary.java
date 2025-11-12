package com.edgestackers.opticon.common.datamodel;

import com.edgestackers.opticon.common.message.OpticonMessage;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;
import com.edgestackers.tote.hub.core.datamodel.message.ToteAccountBalanceType;

public record OpticonAccountBalanceSummary(ToteProvider toteProvider,
                                           TotePoolJurisdiction jurisdiction,
                                           ToteAccountBalanceType toteAccountType,
                                           double balance,
                                           long epochNanosTimestamp) implements OpticonMessage {
}
