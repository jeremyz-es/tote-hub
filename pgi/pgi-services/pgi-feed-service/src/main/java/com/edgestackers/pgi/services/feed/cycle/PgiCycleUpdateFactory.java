package com.edgestackers.pgi.services.feed.cycle;

import com.amtote.gws.*;
import com.edgestackers.pgi.common.datamodel.PgiBetType;
import com.edgestackers.pgi.services.feed.datamodel.PgiRaceInformation;
import com.edgestackers.pgi.common.datamodel.cycle.PgiCycleUpdate;
import com.edgestackers.pgi.services.common.datamodel.PgiTotePoolId;
import com.edgestackers.tote.hub.core.datamodel.cycle.ToteCycleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.edgestackers.pgi.services.feed.cycle.PgiTotePoolIdConverter.convertPgiTotePoolId;
import static com.edgestackers.pgi.services.feed.cycle.PgiSelectionPoolsConverter.convertRawMoneyToSelectionPools;
import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;

public enum PgiCycleUpdateFactory {
    ;
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiCycleUpdateFactory.class);

    public static List<PgiCycleUpdate> convertToPgiCycleUpdates(GpCycleData gpCycleData,
                                                                PgiRaceInformation raceMetadata,
                                                                PgiTotePoolId pgiTotePoolId)
    {
        List<GpPool> gpPools = gpCycleData.getPools().getGpPool();
        String cycleUpdateId = UUID.randomUUID().toString();
        long receivedAtEpochNanos = generateEpochNanosTimestamp();
        return extractGpPoolCycleUpdates(gpPools, cycleUpdateId, receivedAtEpochNanos, raceMetadata, pgiTotePoolId);
    }

    private static List<PgiCycleUpdate> extractGpPoolCycleUpdates(List<GpPool> gpPools,
                                                                  String cycleUpdateId,
                                                                  long receivedAtEpochNanos,
                                                                  PgiRaceInformation raceMetadata,
                                                                  PgiTotePoolId pgiTotePoolId)
    {
        List<PgiCycleUpdate> pgiCycleUpdates = new ArrayList<>();
        for (GpPool gpPool : gpPools) {
            try {
                PgiCycleUpdate pgiCycleUpdate = convertGpPoolToPgiCycleUpdate(cycleUpdateId, receivedAtEpochNanos, gpPool, raceMetadata, pgiTotePoolId);
                pgiCycleUpdates.add(pgiCycleUpdate);
            }
            catch (PgiCycleUpdateConversionException e) {
                LOGGER.error("Failed to convert {} for {}|{} to {} due to {} - {}",
                        gpPool.getClass().getSimpleName(),
                        raceMetadata.getDescription(),
                        gpPool.getBetType(),
                        PgiCycleUpdate.class.getSimpleName(),
                        e.getClass().getSimpleName(),
                        e.getMessage());
            }
        }
        return pgiCycleUpdates;
    }

    private static PgiCycleUpdate convertGpPoolToPgiCycleUpdate(String cycleUpdateId,
                                                                long receivedAtEpochNanos,
                                                                GpPool gpPool,
                                                                PgiRaceInformation raceMetadata,
                                                                PgiTotePoolId pgiTotePoolId) throws PgiCycleUpdateConversionException
    {
        try {
            PgiBetType betType = PgiBetType.valueOf(gpPool.getBetType());
            double poolTotal = Double.parseDouble(gpPool.getPoolTotal());
            String cyclePoolUpdateId = UUID.randomUUID().toString();
            return new PgiCycleUpdate(
                    cycleUpdateId,
                    cyclePoolUpdateId,
                    receivedAtEpochNanos,
                    raceMetadata.programName(),
                    raceMetadata.programLongName(),
                    raceMetadata.programDateYmd(),
                    raceMetadata.pgiRaceType(),
                    raceMetadata.pgiCountryCode(),
                    raceMetadata.race(),
                    betType,
                    poolTotal,
                    ToteCycleType.valueOf(gpPool.getCycleType()),
                    gpPool.getMoney() == null ? new ArrayList<>() : gpPool.getMoney().getString(),
                    convertPgiTotePoolId(pgiTotePoolId),
                    convertRawMoneyToSelectionPools(gpPool)
            );
        }
        catch (Exception e) {
            throw new PgiCycleUpdateConversionException(e.getMessage());
        }
    }
}
