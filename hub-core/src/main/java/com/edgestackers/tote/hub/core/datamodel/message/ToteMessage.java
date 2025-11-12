package com.edgestackers.tote.hub.core.datamodel.message;

import com.edgestackers.tote.hub.core.datamodel.message.nitro.NitroOrderExecutionMessage;
import com.edgestackers.tote.hub.core.datamodel.message.nitro.NitroOrderGeneratorStateMessage;
import com.edgestackers.tote.hub.core.datamodel.message.order.ToteOrderResponseMessage;
import com.edgestackers.tote.hub.core.datamodel.message.pacman.PacmanRaceFlucsMessage;
import com.edgestackers.tote.hub.core.datamodel.message.scratching.ToteScratchingMessage;
import com.edgestackers.tote.hub.core.datamodel.message.theos.RaceTheosUpdateMessage;
import com.edgestackers.tote.hub.core.datamodel.message.turbo.ExoticDividendPredictionMessage;
import com.edgestackers.tote.hub.core.datamodel.message.turbo.ExoticTheoMessage;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ToteMarketCycleUpdate.class, name = "ToteMarketCycleUpdate"),
        @JsonSubTypes.Type(value = ToteWinMarketCycleUpdate.class, name = "ToteWinMarketCycleUpdate"),
        @JsonSubTypes.Type(value = RaceEventUpdateMessage.class, name = "RaceEventUpdateMessage"),
        @JsonSubTypes.Type(value = RaceTheosUpdateMessage.class, name = "RaceTheosUpdateMessage"),
        @JsonSubTypes.Type(value = ToteAccountBalanceSummary.class, name = "ToteAccountBalanceSummary"),
        @JsonSubTypes.Type(value = ExoticDividendPredictionMessage.class, name = "ExoticDividendPredictionMessage"),
        @JsonSubTypes.Type(value = ExoticTheoMessage.class, name = "ExoticTheoMessage"),
        @JsonSubTypes.Type(value = NitroOrderExecutionMessage.class, name = "NitroOrderExecutionMessage"),
        @JsonSubTypes.Type(value = ToteOrderResponseMessage.class, name = "ToteOrderResponseMessage"),
        @JsonSubTypes.Type(value = PacmanRaceFlucsMessage.class, name = "PacmanRaceFlucsUpdateMessage"),
        @JsonSubTypes.Type(value = ToteScratchingMessage.class, name = "ToteScratchingMessage"),
        @JsonSubTypes.Type(value = NitroOrderGeneratorStateMessage.class, name = "NitroOrderGeneratorStateMessage"),
})
public interface ToteMessage {
}
