package com.edgestackers.tote.hub.core.datamodel.message.theos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RunTheoUpdate(@JsonProperty("es_run_id") String esRunId,
                            @JsonProperty("tab_number") int tab,
                            @JsonProperty("theo_price") double theoPrice,
                            @JsonProperty("theo_prob") double theoProb) {
}
