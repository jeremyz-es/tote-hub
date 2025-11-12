package com.edgestackers.pgi.services.feed.cycle;

import com.amtote.gws.ArrayOfString;
import com.amtote.gws.GpPool;
import com.edgestackers.pgi.common.datamodel.PgiBetType;
import com.edgestackers.tote.hub.core.datamodel.cycle.SelectionPool;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

enum PgiSelectionPoolsConverter {
    ;
    private static final String RAW_MONEY_DELIMITER = " ";

    static List<SelectionPool> convertRawMoneyToSelectionPools(GpPool gpPool) {
        @Nullable ArrayOfString moneyStringArray = gpPool.getMoney();
        if (moneyStringArray == null) {
            return new ArrayList<>();
        }
        List<String> rawMoneys = moneyStringArray.getString();
        String gpPoolType = gpPool.getBetType();
        if ((PgiBetType.WN.name().equals(gpPoolType) || PgiBetType.PL.name().equals(gpPoolType)) && !rawMoneys.isEmpty()) {
            return convertSingleSelectionPools(rawMoneys.getFirst());
        }
        return new ArrayList<>();
    }

    private static List<SelectionPool> convertSingleSelectionPools(String rawMoneyString) {
        List<SelectionPool> selectionPools = new ArrayList<>();
        String[] moneys = rawMoneyString.split(RAW_MONEY_DELIMITER);
        for (int i = 0; i < moneys.length; i++) {
            try {
                int rawMoneyForRunner = Integer.parseInt(moneys[i]);
                int runnerNumber = i + 1;
                selectionPools.add(new SelectionPool(List.of(runnerNumber), rawMoneyForRunner));
            }
            catch (Exception ignored) {}
        }
        return selectionPools;
    }
}
