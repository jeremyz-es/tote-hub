package com.edgestackers.opticon.gui.view.order;

import com.edgestackers.opticon.gui.datamodel.order.OpticonBetUpdateEntry;
import com.edgestackers.opticon.gui.datamodel.order.OpticonOrderUpdateEntry;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import static com.edgestackers.opticon.gui.view.order.OrderViewPaneInitializer.*;

public class OrderViewPane {
    private final AnchorPane rootPane = new AnchorPane();
    private final CheckBox linkRaceFilter = new CheckBox("Link Race View");
    private final CheckBox hideAcceptedOrders = new CheckBox("Hide Accepted Orders");
    private final CheckBox hideAcceptedBets = new CheckBox("Hide Accepted Bets");
    private final TableView<OpticonOrderUpdateEntry> orderTableView = new TableView<>();
    private final TableView<OpticonBetUpdateEntry> betTableView = new TableView<>();
    private final TextField selectionFilter = new TextField();

    public void initialize() {
        createView();
    }

    private void createView() {
        createAndInitializeView(rootPane, linkRaceFilter, hideAcceptedOrders, hideAcceptedBets, orderTableView, betTableView, selectionFilter);
        initializeOrderSummaryTableView(orderTableView);
        initializeBetTableView(betTableView);
    }

    public AnchorPane getRootPane() { return rootPane; }

    public CheckBox getLinkRaceFilter() { return linkRaceFilter; }

    public CheckBox getHideAcceptedOrders() { return hideAcceptedOrders; }

    public CheckBox getHideAcceptedBets() { return hideAcceptedBets; }

    public TableView<OpticonOrderUpdateEntry> getOrderTableView() { return orderTableView; }

    public TableView<OpticonBetUpdateEntry> getBetTableView() { return betTableView; }

    public TextField getSelectionFilter() { return selectionFilter; }
}
