package com.edgestackers.pgi.services.feed.cycle.tote;

import com.edgestackers.pgi.common.datamodel.cycle.PgiCycleUpdate;
import com.edgestackers.pgi.common.datamodel.metadata.PgiRaceMetadata;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;
import com.edgestackers.tote.hub.core.datamodel.cycle.MarketApproximate;
import com.edgestackers.tote.hub.core.datamodel.cycle.SelectionPool;
import com.edgestackers.tote.hub.core.datamodel.message.ToteMarketCycleUpdate;
import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;

import java.util.ArrayList;
import java.util.List;

import static com.edgestackers.pgi.common.util.PgiRaceTypeConverter.convertToRaceCode;
import static com.edgestackers.pgi.common.util.PgiRaceTypeConverter.convertToRaceType;
import static com.edgestackers.tote.hub.core.util.ExoticInstrumentIdProvider.createExoticInstrumentId;

public enum ToteMarketCycleUpdateConverter {
    ;
    private static final ToteProvider TOTE_PROVIDER = ToteProvider.PGI;
    private static final double DEFAULT_SELECTION_PROB = 1.0;

    public static ToteMarketCycleUpdate createToteCycleMarketUpdate(PgiCycleUpdate pgiCycleUpdate,
                                                                    PgiRaceMetadata pgiRaceMetadata,
                                                                    ToteBetType toteBetType)
    {
        RaceCode raceType = convertToRaceType(pgiCycleUpdate.pgiRaceType());
        String date = pgiRaceMetadata.date();
        String track = pgiRaceMetadata.track();
        int race = pgiRaceMetadata.raceNumber();
        TotePoolJurisdiction jurisdiction = pgiCycleUpdate.totePoolJurisdiction();
        String esRaceId = pgiRaceMetadata.esRaceId();

        List<MarketApproximate> marketApproximates = createMarketApproximates(pgiCycleUpdate);
        return new ToteMarketCycleUpdate(
                pgiCycleUpdate.feedServiceCyclePoolUpdateId(),
                pgiCycleUpdate.receivedAtEpochNanos(),
                TOTE_PROVIDER,
                pgiCycleUpdate.totePoolJurisdiction(),
                convertToRaceCode(pgiCycleUpdate.pgiRaceType()),
                toteBetType,
                pgiCycleUpdate.toteCycleType(),
                esRaceId,
                date,
                track,
                race,
                createExoticInstrumentId(raceType, date, jurisdiction, toteBetType, track, race),
                !marketApproximates.isEmpty(),
                marketApproximates,
                pgiCycleUpdate.poolTotal()
        );
    }

    private static List<MarketApproximate> createMarketApproximates(PgiCycleUpdate pgiCycleUpdate) {
        List<MarketApproximate> marketApproximates = new ArrayList<>();
        double poolTotal = pgiCycleUpdate.poolTotal();
        List<SelectionPool> selectionPools = pgiCycleUpdate.selectionPools();
        for (SelectionPool selectionPool : selectionPools) {
            double selectionProbability = poolTotal == 0 ? DEFAULT_SELECTION_PROB : selectionPool.poolSize() / poolTotal;
            List<Integer> selections = selectionPool.selections();
            marketApproximates.add(new MarketApproximate(selections, selectionProbability));
        }
        return marketApproximates;
    }
}
