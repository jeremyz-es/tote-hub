package com.edgestackers.opticon.gui.datamodel.filter;


import com.edgestackers.tote.hub.core.datamodel.core.*;

import java.util.Objects;

public record ToteJurisdictionKey(ToteProvider toteProvider, TotePoolJurisdiction jurisdiction) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ToteJurisdictionKey that = (ToteJurisdictionKey) object;
        return toteProvider == that.toteProvider && jurisdiction == that.jurisdiction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toteProvider, jurisdiction);
    }
}
