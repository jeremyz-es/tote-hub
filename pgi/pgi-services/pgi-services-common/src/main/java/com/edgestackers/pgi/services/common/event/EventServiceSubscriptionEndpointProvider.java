package com.edgestackers.pgi.services.common.event;

public enum EventServiceSubscriptionEndpointProvider {
    ;

    public static String subscriptionEndpointFor(EventServiceSubscriptionConfig config) {
        return switch (config) {
            case CLUSTER           -> "ws://pgi-event-service.default.svc.cluster.local:8030";
            case PROD_DIRECT_PROXY -> "ws://localhost:8030";
        };
    }
}
