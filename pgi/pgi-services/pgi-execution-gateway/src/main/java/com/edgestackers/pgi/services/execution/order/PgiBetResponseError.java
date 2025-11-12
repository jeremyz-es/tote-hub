package com.edgestackers.pgi.services.execution.order;

import com.edgestackers.tote.hub.core.datamodel.message.order.ToteBetResult;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum PgiBetResponseError {
    POOL_CLOSED(5),
    RUNNER_SCRATCHED(12),
    RUNNER_UNAVAILABLE(13),
    ;

    PgiBetResponseError(int errorCode) {
        this.code = errorCode;
    }

    private final int code;

    public int getCode() {
        return code;
    }

    private static final Map<Integer, PgiBetResponseError> MAPPINGS = Arrays.stream(PgiBetResponseError.values()).collect(Collectors.toMap(error -> error.code, bookmaker -> bookmaker));

    @Nullable
    public static PgiBetResponseError convertFromErrorCode(int errorCode) {
        return MAPPINGS.get(errorCode);
    }

    public static ToteBetResult convertToToteBetResult(@Nullable PgiBetResponseError error) {
        return switch (error) {
            case POOL_CLOSED        -> ToteBetResult.POOL_CLOSED;
            case RUNNER_SCRATCHED   -> ToteBetResult.RUNNER_SCRATCHED;
            case RUNNER_UNAVAILABLE -> ToteBetResult.RUNNER_UNAVAILABLE;
            case null               -> ToteBetResult.UNKNOWN;
        };
    }
}
