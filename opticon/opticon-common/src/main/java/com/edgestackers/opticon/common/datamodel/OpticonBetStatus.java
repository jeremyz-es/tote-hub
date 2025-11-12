package com.edgestackers.opticon.common.datamodel;

import com.edgestackers.tote.hub.core.datamodel.message.order.ToteBetResult;
import jakarta.annotation.Nullable;

public enum OpticonBetStatus {
    ACCEPTED,
    REJECTED,
    UNKNOWN,
    POOL_CLOSED,
    RUNNER_SCRATCHED,
    RUNNER_UNAVAILABLE,
    ;

    public static OpticonBetStatus convertFromToteBetResult(@Nullable ToteBetResult toteBetResult) {
        return switch (toteBetResult) {
            case ACCEPTED -> ACCEPTED;
            case REJECTED -> REJECTED;
            case POOL_CLOSED -> POOL_CLOSED;
            case RUNNER_SCRATCHED -> RUNNER_SCRATCHED;
            case RUNNER_UNAVAILABLE -> RUNNER_UNAVAILABLE;
            case UNKNOWN -> UNKNOWN;
            case null -> UNKNOWN;
        };
    }
}
