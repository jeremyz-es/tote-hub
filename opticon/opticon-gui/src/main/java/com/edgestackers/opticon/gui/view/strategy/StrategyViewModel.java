package com.edgestackers.opticon.gui.view.strategy;

import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import jakarta.annotation.Nullable;
import javafx.beans.property.SimpleObjectProperty;

public class StrategyViewModel {
    private final SimpleObjectProperty<TotePoolJurisdiction> selectedJurisdiction = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<ToteBetType> selectedBetType = new SimpleObjectProperty<>();

    void initialize() {
        initializeListeners();
    }

    private void initializeListeners() {
        selectedJurisdiction.addListener((observable, oldValue, newValue) -> updateFilter());
        selectedBetType.addListener((observable, oldValue, newValue) -> updateFilter());
    }

    private void updateFilter() {
        @Nullable TotePoolJurisdiction jurisdiction = selectedJurisdiction.get();
        @Nullable ToteBetType betType = selectedBetType.get();
        if (jurisdiction == null || betType == null) {
            return;
        }
    }

    public SimpleObjectProperty<ToteBetType> selectedBetTypeProperty() { return selectedBetType; }

    public SimpleObjectProperty<TotePoolJurisdiction> selectedJurisdictionProperty() { return selectedJurisdiction; }

}
