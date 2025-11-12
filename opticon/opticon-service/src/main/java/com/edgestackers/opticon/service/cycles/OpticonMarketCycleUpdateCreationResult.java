package com.edgestackers.opticon.service.cycles;

import com.edgestackers.opticon.common.datamodel.OpticonMarketCycleUpdate;

public record OpticonMarketCycleUpdateCreationResult(OpticonMarketCycleUpdate update,
                                                     double timeUntilOfficialJumpNanos) {
}
