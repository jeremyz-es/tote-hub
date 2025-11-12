package com.edgestackers.opticon.common.datamodel;

import com.edgestackers.opticon.common.message.OpticonMessage;
import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;
import jakarta.annotation.Nullable;

import java.util.Map;

public record PacmanRaceFlucsUpdate(long epochNanosTimestamp,
                                    RaceCode raceType,
                                    String esRaceId,
                                    String track,
                                    int race,
                                    Map<Integer, PacmanRunFlucsUpdate> pacmanRunFlucsUpdates,
                                    @Nullable Long lastFlucsUpdateDeltaSeconds) implements OpticonMessage {
}
