package com.edgestackers.opticon.gui.view.operations;

import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummary;
import com.edgestackers.opticon.gui.datamodel.OpticonRaceSwitchKey;
import com.edgestackers.opticon.gui.view.OpticonViewType;
import com.edgestackers.tote.hub.core.datamodel.core.*;
import javafx.collections.ObservableList;

import static com.edgestackers.opticon.gui.settings.OpticonGuiSettingsManager.getSettingsFor;
import static com.edgestackers.opticon.gui.settings.OpticonGuiSettingsManager.saveOpticonGuiSettings;

public class OperationsViewController {
    private static final OpticonViewType VIEW_TYPE = OpticonViewType.OPERATIONS_VIEW;
    private final OperationsView view;
    private final OperationsViewModel viewModel;

    public OperationsViewController(OperationsView view, OperationsViewModel viewModel) {
        this.view = view;
        this.viewModel = viewModel;
    }

    public void initialize() {
        initializeBindings();
        initializeListeners();
        view.initialize(getSettingsFor(OpticonViewType.OPERATIONS_VIEW));
        viewModel.initialize();
        view.getTableView().refresh();
    }

    public void handle(OpticonRaceSwitchKey raceSwitchKey) {
        viewModel.handle(raceSwitchKey);
    }

    public void process(OpticonStrategyContextSummary summary) {
        viewModel.process(summary);
    }

    private void initializeBindings() {
        view.getLinkRaceFilter().selectedProperty().bindBidirectional(viewModel.linkRaceFilterPropertyProperty());
        view.getTableView().setItems(viewModel.getSortedUpdateEntries());
        view.getBetTypeSelector().getItems().addAll(ToteBetType.values());
        view.getJurisdictionSelector().getItems().addAll(TotePoolJurisdiction.values());
    }

    private void initializeListeners() {
        ObservableList<ToteBetType> checkedBetTypes = view.getBetTypeSelector().getCheckModel().getCheckedItems();
        ObservableList<TotePoolJurisdiction> checkedJurisdictions = view.getJurisdictionSelector().getCheckModel().getCheckedItems();
        viewModel.register(checkedBetTypes, checkedJurisdictions);
        view.getStage().xProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
        view.getStage().yProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
        view.getStage().widthProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
        view.getStage().heightProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
    }
}
