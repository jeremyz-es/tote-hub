package com.edgestackers.pgi.services.execution.order;

import com.edgestackers.pgi.common.datamodel.PgiBetType;
import com.edgestackers.pgi.common.datamodel.message.PgiBetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PgiBetResponseConverter {
    ;
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiBetResponseConverter.class);
    private static final String NEWLINE_DELIMITER = "\\R";
    private static final String BET_RESPONSE_LINE_FIELD_DELIMITER = "\\|";

    static List<PgiBetResponse> convertResponseToBetResponses(String response) {
        LOGGER.info("\nRaw bets response: {}\n", response);
        List<PgiBetResponse> summaries = new ArrayList<>();
        String[] betResponseLines = response.split(NEWLINE_DELIMITER);
        LOGGER.info("{} bet response lines", betResponseLines.length);
        for (String betResponseLine : betResponseLines) {
            summaries.add(convertBetResponseLine(betResponseLine));
        }
        return summaries;
    }

    private static PgiBetResponse convertBetResponseLine(String betResponseLine) {
        String[] fieldValues = betResponseLine.split(BET_RESPONSE_LINE_FIELD_DELIMITER);
        String clientBetId = fieldValues[0];
        String programName = fieldValues[1];
        int race = Integer.parseInt(fieldValues[2]);
        double stake = Double.parseDouble(fieldValues[3]);
        PgiBetType pgiBetType = PgiBetType.valueOf(fieldValues[4]);
        String runnersString = fieldValues[5];
        int pgiBetErrorCode = Integer.parseInt(fieldValues[6]);
        String pgiBetErrorMessage = fieldValues[7];
        return new PgiBetResponse(
                clientBetId,
                programName,
                race,
                stake,
                pgiBetType,
                runnersString,
                pgiBetErrorCode,
                pgiBetErrorMessage
        );
    }
}
