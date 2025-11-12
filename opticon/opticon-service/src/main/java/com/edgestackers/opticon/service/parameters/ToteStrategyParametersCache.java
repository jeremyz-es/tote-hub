package com.edgestackers.opticon.service.parameters;

import com.edgestackers.tote.hub.core.parameters.ToteStrategyParameters;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ToteStrategyParametersCache {
    private final Set<ToteStrategyParameters> toteStrategyParameters = new HashSet<>();

    public void clearAndPutAll(Collection<ToteStrategyParameters> toteStrategyParameters) {
        this.toteStrategyParameters.clear();
        this.toteStrategyParameters.addAll(toteStrategyParameters);
    }

    public Collection<ToteStrategyParameters> getAll() {
        return toteStrategyParameters;
    }
}
