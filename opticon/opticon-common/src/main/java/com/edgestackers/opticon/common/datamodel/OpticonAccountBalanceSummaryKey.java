package com.edgestackers.opticon.common.datamodel;


import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;
import com.edgestackers.tote.hub.core.datamodel.message.ToteAccountBalanceType;

import java.util.Objects;

public record OpticonAccountBalanceSummaryKey(ToteProvider toteProvider, TotePoolJurisdiction jurisdiction, ToteAccountBalanceType toteAccountType) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OpticonAccountBalanceSummaryKey that = (OpticonAccountBalanceSummaryKey) object;
        return toteProvider == that.toteProvider && toteAccountType == that.toteAccountType && jurisdiction == that.jurisdiction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toteProvider, jurisdiction, toteAccountType);
    }
}
