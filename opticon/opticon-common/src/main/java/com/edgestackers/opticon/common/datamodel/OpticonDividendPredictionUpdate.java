package com.edgestackers.opticon.common.datamodel;

import com.edgestackers.opticon.common.message.OpticonMessage;
import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;
import com.edgestackers.tote.hub.core.datamodel.turbo.TurboExoticDividendModel;
import jakarta.annotation.Nullable;

import java.util.List;

public record OpticonDividendPredictionUpdate(long generatedAtEpochNanos,
                                              String cyclePoolUpdateId,
                                              RaceType raceType,
                                              TotePoolJurisdiction jurisdiction,
                                              ToteProvider provider,
                                              String date,
                                              String esTrackId,
                                              String esRaceId,
                                              String track,
                                              int race,
                                              double marketPoolTotal,
                                              double predictedPoolTotal,
                                              List<ExoticCombinationSummary> exoticCombinationSummaries,
                                              ToteBetType betType,
                                              String exoticDividendPredictionId,
                                              TurboExoticDividendModel dividendModel,
                                              int modelMajorVersion,
                                              int modelMinorVersion,
                                              String pricingModelSource,
                                              double calculationTimeMillis,
                                              @Nullable Long lastDividendDeltaSeconds) implements OpticonMessage {
}
