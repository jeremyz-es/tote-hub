package com.edgestackers.opticon.service.control;

import com.edgestackers.opticon.common.datamodel.OpticonAccountBalanceSummary;
import com.edgestackers.opticon.common.datamodel.OpticonAccountBalanceSummaryKey;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;
import com.edgestackers.tote.hub.core.datamodel.message.ToteAccountBalanceSummary;
import com.edgestackers.tote.hub.core.datamodel.message.ToteAccountBalanceType;
import jakarta.annotation.Nullable;

import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;


public class OpticonAccountBalanceSummaryFactory {
    private final OpticonAccountBalanceSummaryCache opticonAccountBalanceSummaryCache;

    public OpticonAccountBalanceSummaryFactory(OpticonAccountBalanceSummaryCache opticonAccountBalanceSummaryCache) {
        this.opticonAccountBalanceSummaryCache = opticonAccountBalanceSummaryCache;
    }

    public OpticonAccountBalanceSummary createTotalBalanceSummary(ToteProvider provider, TotePoolJurisdiction jurisdiction) {
        @Nullable OpticonAccountBalanceSummary masterBalance = opticonAccountBalanceSummaryCache.get(new OpticonAccountBalanceSummaryKey(provider, jurisdiction, ToteAccountBalanceType.MASTER));
        @Nullable OpticonAccountBalanceSummary rebateBalance = opticonAccountBalanceSummaryCache.get(new OpticonAccountBalanceSummaryKey(provider, jurisdiction, ToteAccountBalanceType.REBATE));
        return new OpticonAccountBalanceSummary(
                provider,
                jurisdiction,
                ToteAccountBalanceType.TOTAL,
                (masterBalance == null ? 0 : masterBalance.balance()) + (rebateBalance == null ? 0 : rebateBalance.balance()),
                generateEpochNanosTimestamp()
        );
    }

    public static OpticonAccountBalanceSummary createAccountBalanceSummary(ToteAccountBalanceSummary summary) {
        return new OpticonAccountBalanceSummary(
                summary.toteProvider(),
                summary.jurisdiction(),
                summary.toteAccountType(),
                summary.balance(),
                summary.epochNanosTimestamp()
        );
    }
}
