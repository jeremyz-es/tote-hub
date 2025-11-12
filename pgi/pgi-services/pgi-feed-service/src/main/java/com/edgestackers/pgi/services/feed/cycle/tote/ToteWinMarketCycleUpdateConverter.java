package com.edgestackers.pgi.services.feed.cycle.tote;

import com.edgestackers.pgi.common.datamodel.cycle.PgiCycleUpdate;
import com.edgestackers.pgi.common.datamodel.metadata.PgiRaceMetadata;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;
import com.edgestackers.tote.hub.core.datamodel.message.ToteWinMarketCycleUpdate;
import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;

import java.util.Map;
import java.util.stream.Collectors;

import static com.edgestackers.pgi.common.util.PgiRaceTypeConverter.convertToRaceCode;
import static com.edgestackers.pgi.common.util.PgiRaceTypeConverter.convertToRaceType;
import static com.edgestackers.tote.hub.core.util.ExoticInstrumentIdProvider.createExoticInstrumentId;

public enum ToteWinMarketCycleUpdateConverter {
    ;
    private static final ToteProvider TOTE_PROVIDER = ToteProvider.PGI;
    private static final ToteBetType TOTE_BET_TYPE = ToteBetType.WIN;
    private static final double ZERO_PROB_PRICE = 10000.0;

    public static ToteWinMarketCycleUpdate createToteWinMarketCycleUpdate(PgiCycleUpdate cycleUpdate, PgiRaceMetadata raceMetadata) {
        RaceCode raceType = convertToRaceType(cycleUpdate.pgiRaceType());
        String date = raceMetadata.date();
        String track = raceMetadata.track();
        int race = raceMetadata.raceNumber();
        TotePoolJurisdiction jurisdiction = cycleUpdate.totePoolJurisdiction();
        String esRaceId = raceMetadata.esRaceId();

        return new ToteWinMarketCycleUpdate(
                cycleUpdate.feedServiceCyclePoolUpdateId(),
                cycleUpdate.receivedAtEpochNanos(),
                TOTE_PROVIDER,
                cycleUpdate.totePoolJurisdiction(),
                convertToRaceCode(cycleUpdate.pgiRaceType()),
                cycleUpdate.toteCycleType(),
                esRaceId,
                date,
                track,
                race,
                createExoticInstrumentId(raceType, date, jurisdiction, TOTE_BET_TYPE, track, race),
                extractMarketPrices(cycleUpdate),
                cycleUpdate.poolTotal()
        );
    }

    private static Map<Integer, Double> extractMarketPrices(PgiCycleUpdate cycleUpdate) {
        return cycleUpdate.selectionPools()
                .stream()
                .collect(Collectors.toMap(
                        selectionPool -> selectionPool.selections().getFirst(),
                        selectionPool -> selectionPool.poolSize() == 0 ? ZERO_PROB_PRICE : cycleUpdate.poolTotal() / selectionPool.poolSize())
                );
    }
}
