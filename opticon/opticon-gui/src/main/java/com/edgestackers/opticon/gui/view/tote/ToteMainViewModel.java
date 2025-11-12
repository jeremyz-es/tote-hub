package com.edgestackers.opticon.gui.view.tote;

import com.edgestackers.opticon.common.datamodel.OpticonAccountBalanceSummary;
import com.edgestackers.opticon.common.datamodel.OpticonAccountBalanceSummaryKey;
import com.edgestackers.opticon.gui.datamodel.OpticonRaceSwitchKey;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;
import com.edgestackers.tote.hub.core.datamodel.message.ToteAccountBalanceType;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ToteMainViewModel {
    private final Map<OpticonAccountBalanceSummaryKey, SimpleStringProperty> balances = new HashMap<>();
    private final SimpleBooleanProperty approximatesReady = new SimpleBooleanProperty(false);
    private final SimpleBooleanProperty pricingReady = new SimpleBooleanProperty(false);
    private final SimpleBooleanProperty triggerReady = new SimpleBooleanProperty(false);
    private final SimpleObjectProperty<OpticonRaceSwitchKey> selectedRace = new SimpleObjectProperty<>();

    public void initialize() {
        Arrays.stream(TotePoolJurisdiction.values()).forEach(jurisdiction -> {
            balances.put(new OpticonAccountBalanceSummaryKey(ToteProvider.PGI, jurisdiction, ToteAccountBalanceType.MASTER), new SimpleStringProperty());
            balances.put(new OpticonAccountBalanceSummaryKey(ToteProvider.PGI, jurisdiction, ToteAccountBalanceType.REBATE), new SimpleStringProperty());
            balances.put(new OpticonAccountBalanceSummaryKey(ToteProvider.PGI, jurisdiction, ToteAccountBalanceType.TOTAL), new SimpleStringProperty());
        });
    }

    public void handle(OpticonAccountBalanceSummary summary) {
        OpticonAccountBalanceSummaryKey key = new OpticonAccountBalanceSummaryKey(summary.toteProvider(), summary.jurisdiction(), summary.toteAccountType());
        SimpleStringProperty balance = balances.get(key);
        Platform.runLater(() -> balance.setValue(String.format("$%,.2f", summary.balance())));
    }

    public void handle(OpticonRaceSwitchKey race) {
        selectedRace.set(race);
    }

    public SimpleBooleanProperty approximatesReadyProperty() { return approximatesReady; }

    public SimpleBooleanProperty pricingReadyProperty() { return pricingReady; }

    public SimpleBooleanProperty triggerReadyProperty() { return triggerReady; }

    public Map<OpticonAccountBalanceSummaryKey, SimpleStringProperty> getBalances() { return balances; }
}
