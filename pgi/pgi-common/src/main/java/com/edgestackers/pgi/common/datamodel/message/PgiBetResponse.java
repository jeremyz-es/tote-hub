package com.edgestackers.pgi.common.datamodel.message;

import com.edgestackers.pgi.common.datamodel.PgiBetType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record PgiBetResponse(@JsonProperty("client_bet_id") String clientBetId,
                             @JsonProperty("program_name") String programName,
                             @JsonProperty("race") int race,
                             @JsonProperty("stake") double stake,
                             @JsonProperty("pgi_bet_type") PgiBetType pgiBetType,
                             @JsonProperty("runners_string") String runnersString,
                             @JsonProperty("error_code") int errorCode,
                             @JsonProperty("error_message") String errorMessage) {
}
