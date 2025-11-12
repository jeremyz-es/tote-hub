package com.edgestackers.opticon.gui.datamodel.filter;

import com.edgestackers.core.datamodel.tote.ToteBetType;
import com.edgestackers.core.datamodel.tote.TotePoolJurisdiction;

import java.util.Objects;

public record ExoticInstrumentFilter(ToteBetType toteBetType, TotePoolJurisdiction totePoolJurisdiction) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ExoticInstrumentFilter that = (ExoticInstrumentFilter) object;
        return toteBetType == that.toteBetType && totePoolJurisdiction == that.totePoolJurisdiction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toteBetType, totePoolJurisdiction);
    }
}
