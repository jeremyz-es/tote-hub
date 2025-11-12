package com.edgestackers.opticon.gui.view.parameters.crud;

import com.edgestackers.opticon.gui.datamodel.parameters.ToteStrategyParametersEntry;
import com.edgestackers.tote.hub.core.datamodel.core.ToteRaceEvent;
import com.edgestackers.tote.hub.core.parameters.CaesarApiClient;
import com.edgestackers.tote.hub.core.parameters.ToteStrategyParameters;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ToteStrategyParametersCrudViewController {
    private final ToteStrategyParametersCrudView view;
    private final ToteStrategyParametersCrudViewModel viewModel;
    private final CaesarApiClient caesarApiClient;
    private final Set<Runnable> onCloseCallbacks = new HashSet<>();

    public ToteStrategyParametersCrudViewController(ToteStrategyParametersCrudView view,
                                                    ToteStrategyParametersCrudViewModel viewModel,
                                                    CaesarApiClient caesarApiClient)
    {
        this.view = view;
        this.viewModel = viewModel;
        this.caesarApiClient = caesarApiClient;
    }

    public void initialize() {
        initializeBindings();
        initializeListeners();
        view.initialize();
    }

    public void registerOnCloseCallback(Runnable runnable) {
        onCloseCallbacks.add(runnable);
    }

    public void open(ToteStrategyParametersEntry entry) {
        viewModel.handle(entry);
        if (entry != null && entry.getTriggerRaceEvents() != null) {
            view.getTriggerRaceEvents().getCheckModel().clearChecks();
            entry.getTriggerRaceEvents().forEach(trigger -> 
                view.getTriggerRaceEvents().getCheckModel().check(trigger)
            );
        }
        view.open();
    }

    public void open() {
        viewModel.handle(null);
        view.open();
    }

    private void initializeBindings() {
        view.getLastUpdatedAtQldTime().textProperty().bindBidirectional(viewModel.lastedUpdatedAtQldTimeProperty());
        view.getStrategyNameField().textProperty().bindBidirectional(viewModel.strategyNameProperty());
        view.getMaxRaceBetField().textProperty().bindBidirectional(viewModel.maxRaceBetProperty());
        view.getRebateField().textProperty().bindBidirectional(viewModel.rebateProperty());
        view.getTakeoutField().textProperty().bindBidirectional(viewModel.takeoutProperty());
        view.getDiscountField().textProperty().bindBidirectional(viewModel.discountProperty());
        view.getMinBetField().textProperty().bindBidirectional(viewModel.minBetProperty());
        view.getMinPoolField().textProperty().bindBidirectional(viewModel.minPoolSizeProperty());
        view.getMaxPoolField().textProperty().bindBidirectional(viewModel.maxPoolSizeProperty());
        view.getMaxDividendAgeSecondsField().textProperty().bindBidirectional(viewModel.maxDividendAgeSecondsProperty());
        view.getMaxTheoAgeSecondsField().textProperty().bindBidirectional(viewModel.maxTheoAgeSecondsProperty());
        
        view.getRaceType().valueProperty().bindBidirectional(viewModel.raceTypeProperty());
        view.getToteProvider().valueProperty().bindBidirectional(viewModel.toteProviderProperty());
        view.getToteBetType().valueProperty().bindBidirectional(viewModel.toteBetTypeProperty());
        view.getJurisdiction().valueProperty().bindBidirectional(viewModel.jurisdictionProperty());
        view.getDividendModel().valueProperty().bindBidirectional(viewModel.dividendModelProperty());
        view.getDividendModelMajorVersion().textProperty().bindBidirectional(viewModel.dividendModelMajorVersionProperty());
        view.getDividendModelMinorVersion().textProperty().bindBidirectional(viewModel.dividendModelMinorVersionProperty());
        view.getTheoModel().valueProperty().bindBidirectional(viewModel.theoModelProperty());
        view.getTheoModelMajorVersion().textProperty().bindBidirectional(viewModel.theoModelMajorVersionProperty());
        view.getTheoModelMinorVersion().textProperty().bindBidirectional(viewModel.theoModelMinorVersionProperty());
        view.getErrorLabel().textProperty().bind(viewModel.errorProperty());
    }
    
    private void initializeListeners() {
        view.getSubmitButton().setOnAction(event -> {
            try {
                List<ToteRaceEvent> selectedTriggers = view.getTriggerRaceEvents().getCheckModel().getCheckedItems();
                ToteStrategyParameters toteStrategyParameters = viewModel.composeToteStrategyParameters(selectedTriggers);
                caesarApiClient.pushToteStrategyParameters(toteStrategyParameters);
                view.getStage().close();
                onCloseCallbacks.forEach(Runnable::run);
            }
            catch (Exception e) {
                viewModel.setError("Error submitting parameters - check inputs");
            }
        });
        view.getDeleteButton().setOnAction(event -> {
            try {
                caesarApiClient.deleteToteStrategyParameters(viewModel.strategyIdProperty().get());
                view.getStage().close();
                onCloseCallbacks.forEach(Runnable::run);
            }
            catch (Exception e) {
                viewModel.setError(e.getMessage());
            }
        });
    }
}
