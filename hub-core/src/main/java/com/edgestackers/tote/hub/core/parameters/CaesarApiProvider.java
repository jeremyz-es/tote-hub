package com.edgestackers.tote.hub.core.parameters;

public class CaesarApiProvider {
    private final String baseEndpoint;

    public CaesarApiProvider(String baseEndpoint) {
        this.baseEndpoint = baseEndpoint;
    }

    public String toteStrategyParametersEndpoint() {
        return baseEndpoint + "/tote-strategy-parameters";
    }
}
