package com.edgestackers.tote.hub.core.datamodel.message;

import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;
import com.edgestackers.tote.hub.core.datamodel.cycle.MarketApproximate;
import com.edgestackers.tote.hub.core.datamodel.cycle.ToteCycleType;
import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ToteMarketCycleUpdate(@JsonProperty("feed_service_cycle_pool_update_id") String feedServiceCyclePoolUpdateId,
                                    @JsonProperty("received_at_epoch_nanos") long receivedAtEpochNanos,

                                    @JsonProperty("tote_provider") ToteProvider toteProvider,
                                    @JsonProperty("jurisdiction") TotePoolJurisdiction totePoolJurisdiction,
                                    @JsonProperty("race_code") RaceCode raceCode,
                                    @JsonProperty("bet_type") ToteBetType toteBetType,
                                    @JsonProperty("tote_cycle_type") ToteCycleType toteCycleType,
                                    @JsonProperty("es_race_id") String esRaceId,
                                    @JsonProperty("date") String date,
                                    @JsonProperty("track") String track,
                                    @JsonProperty("race") int race,
                                    @JsonProperty("exotic_instrument_id") String exoticInstrumentId,
                                    @JsonProperty("market_approximates_available") boolean marketApproximatesAvailable,

                                    @JsonProperty("market_approximates") List<MarketApproximate> marketApproximates,
                                    @JsonProperty("pool_total") double poolTotal) implements ToteMessage {
}
