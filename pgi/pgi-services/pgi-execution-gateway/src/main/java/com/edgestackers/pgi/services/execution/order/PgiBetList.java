package com.edgestackers.pgi.services.execution.order;

import jakarta.annotation.Nullable;

public record PgiBetList(String betsFile, @Nullable String desc) {
}
