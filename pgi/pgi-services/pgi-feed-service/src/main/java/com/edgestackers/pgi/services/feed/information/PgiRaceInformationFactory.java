package com.edgestackers.pgi.services.feed.information;

import com.amtote.gws.*;
import com.edgestackers.pgi.common.datamodel.PgiBetType;
import com.edgestackers.pgi.services.feed.datamodel.PgiProgramInformation;
import com.edgestackers.pgi.services.feed.datamodel.PgiRaceInformation;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum PgiRaceInformationFactory {
    ;
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiRaceInformationFactory.class);
    private static final String RUNNERS_DELIMITER = ",";

    public static List<PgiRaceInformation> convertToPgiRaceMetadatas(GpProgramDefinition definition, PgiProgramInformation pgiProgramInformation) {
        List<PgiRaceInformation> pgiRaceMetadata = new ArrayList<>();
        List<RaceDefinition> raceDefinitions = definition.getRaceDetailList().getRaceDefinition();
        for (RaceDefinition raceDefinition : raceDefinitions) {
            String live = raceDefinition.getLiveRunners();
            String scr = raceDefinition.getScratchedRunners();
            List<Integer> liveRunners = live.isEmpty() ? new ArrayList<>() : Arrays.stream(raceDefinition.getLiveRunners().split(RUNNERS_DELIMITER)).map(Integer::valueOf).toList();
            List<Integer> scratchedRunners = scr.isEmpty() ? new ArrayList<>() : Arrays.stream(raceDefinition.getScratchedRunners().split(RUNNERS_DELIMITER)).map(Integer::valueOf).toList();
            int raceNumber = raceDefinition.getRaceNumber();
            List<PgiBetType> availableBetTypes = extractAvailableBetTypes(raceDefinition);
            pgiRaceMetadata.add(new PgiRaceInformation(
                    pgiProgramInformation.programName(),
                    pgiProgramInformation.programLongName(),
                    pgiProgramInformation.programDateYmd(),
                    pgiProgramInformation.pgiRaceType(),
                    pgiProgramInformation.pgiCountryCode(),
                    raceNumber,
                    liveRunners,
                    scratchedRunners,
                    availableBetTypes
            ));
        }
        return pgiRaceMetadata;
    }

    private static List<PgiBetType> extractAvailableBetTypes(RaceDefinition raceDefinition) {
        List<PgiBetType> availableBetTypes = new ArrayList<>();
        @Nullable ArrayOfBetTypeInformation root = raceDefinition.getBetTypeInformation();
        if (root == null) {
            return availableBetTypes;
        }
        for (BetTypeInformation betTypeInformation : root.getBetTypeInformation()) {
            try {
                availableBetTypes.add(PgiBetType.valueOf(betTypeInformation.getBetTypeName()));
            }
            catch (IllegalArgumentException e) {
                LOGGER.error("Failed to extract available bet types from [{}] - {}", RaceDefinition.class.getSimpleName(), e.getMessage());
            }
        }
        return availableBetTypes;
    }
}
