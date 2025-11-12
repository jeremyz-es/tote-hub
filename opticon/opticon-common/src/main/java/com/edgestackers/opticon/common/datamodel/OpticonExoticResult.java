package com.edgestackers.opticon.common.datamodel;

import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;

import java.util.List;

public record OpticonExoticResult(String esRaceId,
                                  String track,
                                  int race,
                                  ToteBetType betType,
                                  TotePoolJurisdiction jurisdiction,
                                  List<Integer> winningSelection,
                                  double payout,
                                  double turnover,
                                  double profit) {
}
