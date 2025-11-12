package com.edgestackers.opticon.common.datamodel;

import com.edgestackers.opticon.common.message.OpticonMessage;
import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.turbo.TurboExoticTheoModel;
import jakarta.annotation.Nullable;

import java.util.List;

public record OpticonTheoUpdate(long generatedAtEpochNanos,
                                RaceType raceType,
                                String esRaceId,
                                String track,
                                int race,
                                ToteBetType toteBetType,
                                List<ExoticCombinationSummary> exoticCombinationSummaries,
                                String exoticTheoId,
                                TurboExoticTheoModel theoModel,
                                int modelMajorVersion,
                                int modelMinorVersion,
                                double calculationTimeMillis,
                                @Nullable Long lastTheoDeltaSeconds) implements OpticonMessage {
}
