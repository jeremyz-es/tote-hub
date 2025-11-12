package com.edgestackers.opticon.gui.view.tote;

import com.edgestackers.opticon.common.datamodel.OpticonAccountBalanceSummary;
import com.edgestackers.opticon.common.datamodel.OpticonAccountBalanceSummaryKey;
import com.edgestackers.opticon.gui.datamodel.OpticonRaceSwitchKey;
import com.edgestackers.opticon.gui.view.OpticonViewType;
import com.edgestackers.opticon.gui.view.race.RaceViewPaneController;
import com.edgestackers.tote.hub.core.datamodel.message.ToteAccountBalanceType;

import static com.edgestackers.opticon.gui.settings.OpticonGuiSettingsManager.getSettingsFor;
import static com.edgestackers.opticon.gui.settings.OpticonGuiSettingsManager.saveOpticonGuiSettings;

public class ToteMainViewController {
    private static final OpticonViewType VIEW_TYPE = OpticonViewType.MAIN_CONTROL;
    private final ToteMainView view;
    private final ToteMainViewModel viewModel;
    private final RaceViewPaneController raceViewPaneController;

    public ToteMainViewController(ToteMainView view,
                                  ToteMainViewModel viewModel,
                                  RaceViewPaneController raceViewPaneController)
    {
        this.view = view;
        this.viewModel = viewModel;
        this.raceViewPaneController = raceViewPaneController;
    }

    public void initialize() {
        view.initialize(getSettingsFor(VIEW_TYPE));
        viewModel.initialize();
        raceViewPaneController.initialize();
        initializeBindings();
        initializeListeners();
    }

    public void handle(OpticonAccountBalanceSummary summary) {
        viewModel.handle(summary);
    }

    public void handle(OpticonRaceSwitchKey raceSwitchKey) {
        viewModel.handle(raceSwitchKey);
    }

    private void initializeBindings() {
        view.getBalanceTiles().forEach((key, tile) -> tile.getMasterBalanceLabel().textProperty().bindBidirectional(viewModel.getBalances().get(new OpticonAccountBalanceSummaryKey(key.toteProvider(), key.jurisdiction(), ToteAccountBalanceType.MASTER))));
        view.getBalanceTiles().forEach((key, tile) -> tile.getRebateBalanceLabel().textProperty().bindBidirectional(viewModel.getBalances().get(new OpticonAccountBalanceSummaryKey(key.toteProvider(), key.jurisdiction(), ToteAccountBalanceType.REBATE))));
        view.getBalanceTiles().forEach((key, tile) -> tile.getTotalBalanceLabel().textProperty().bindBidirectional(viewModel.getBalances().get(new OpticonAccountBalanceSummaryKey(key.toteProvider(), key.jurisdiction(), ToteAccountBalanceType.TOTAL))));
        view.getApproximatesTile().activeProperty().bindBidirectional(viewModel.approximatesReadyProperty());
        view.getPricingTile().activeProperty().bindBidirectional(viewModel.pricingReadyProperty());
        view.getTriggerTile().activeProperty().bindBidirectional(viewModel.triggerReadyProperty());
    }

    private void initializeListeners() {
        view.getStage().xProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
        view.getStage().yProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
        view.getStage().widthProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
        view.getStage().heightProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
    }
}
