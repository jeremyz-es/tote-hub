package com.edgestackers.opticon.gui.view.order;

import com.edgestackers.opticon.common.datamodel.OpticonOrderUpdate;
import com.edgestackers.opticon.gui.datamodel.OpticonRaceSwitchKey;

public class OrderViewPaneController {
    private final OrderViewPane view;
    private final OrderViewPaneModel viewModel;

    public OrderViewPaneController(OrderViewPane view,
                                   OrderViewPaneModel viewModel)
    {
        this.view = view;
        this.viewModel = viewModel;
    }

    public void initialize() {
        view.initialize();
        viewModel.initialize();
        initializeBindings();
        initializeListeners();
    }

    public void handle(OpticonRaceSwitchKey raceSwitchKey) {
        viewModel.handle(raceSwitchKey);
    }

    public void process(OpticonOrderUpdate opticonOrderUpdate) {
        viewModel.process(opticonOrderUpdate);
    }

    public void initializeBindings() {
        view.getLinkRaceFilter().selectedProperty().bindBidirectional(viewModel.linkRaceFilterPropertyProperty());
        view.getHideAcceptedOrders().selectedProperty().bindBidirectional(viewModel.hideAcceptedOrdersProperty());
        view.getHideAcceptedBets().selectedProperty().bindBidirectional(viewModel.hideAcceptedBetsProperty());
        view.getOrderTableView().setItems(viewModel.getSortedOrderEntries());
        view.getBetTableView().setItems(viewModel.getSortedBetEntries());
        viewModel.getSortedBetEntries().comparatorProperty().bind(view.getBetTableView().comparatorProperty());
        view.getSelectionFilter().textProperty().bindBidirectional(viewModel.selectionFilterProperty());
    }

    public void initializeListeners() {
        view.getOrderTableView().getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> viewModel.switchTo(newVal));
    }
}
