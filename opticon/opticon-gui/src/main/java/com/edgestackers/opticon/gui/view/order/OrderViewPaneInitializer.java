package com.edgestackers.opticon.gui.view.order;

import com.edgestackers.opticon.common.datamodel.OpticonBetStatus;
import com.edgestackers.opticon.common.datamodel.OpticonOrderStatus;
import com.edgestackers.opticon.gui.datamodel.order.OpticonBetUpdateEntry;
import com.edgestackers.opticon.gui.datamodel.order.OpticonOrderUpdateEntry;
import com.edgestackers.opticon.gui.settings.OpticonSceneSettings;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.lang.reflect.Field;
import java.util.Map;

import static com.edgestackers.opticon.gui.view.util.OpticonGuiResourceUtil.OPTICON_CSS;
import static com.edgestackers.opticon.gui.view.util.OpticonViewUtil.setAnchorsAndSize;

public enum OrderViewPaneInitializer {
    ;
    private static final Map<String, String> ORDER_TABLE_VIEW_COLUMN_HEADER_NAMES = Map.ofEntries(
            Map.entry("orderGeneratedAtQldTime", "Gen @ (QLD)"),
            Map.entry("strategyName", "Strategy"),
            Map.entry("jurisdiction", "J"),
            Map.entry("betType", "BT"),
            Map.entry("track", "T"),
            Map.entry("race", "R#"),
            Map.entry("raceEventTrigger", "Trg"),
            Map.entry("marketTotalPoolSize", "Mkt Pool"),
            Map.entry("predictedTotalPoolSize", "Pred Pool"),
            Map.entry("orderAmount", "O.$"),
            Map.entry("orderAmountAccepted", "O.Accepted.$")
    );
    private static final Map<String, String> BET_TABLE_VIEW_COLUMN_HEADER_NAMES = Map.ofEntries(
            Map.entry("selections", "S"),
            Map.entry("selectionPool", "S. Pool"),
            Map.entry("betAmount", "Bet Amt"),
            Map.entry("predictedMarketProbability", "Pred Mkt.P"),
            Map.entry("predictedMarketPrice", "Pred Mkt.$"),
            Map.entry("theoProb", "Theo.P"),
            Map.entry("theoPrice", "Theo.$"),
            Map.entry("opticonBetStatus", "Status")
    );

    static void createAndInitializeView(AnchorPane rootPane,
                                        CheckBox linkRaceView,
                                        CheckBox hideAcceptedOrders,
                                        CheckBox hideAcceptedBets,
                                        TableView<OpticonOrderUpdateEntry> orderTableView,
                                        TableView<OpticonBetUpdateEntry> betTableView,
                                        TextField selectionFilter)
    {
        selectionFilter.setPromptText("Selection Filter");
        HBox controlsHBox = new HBox();
        controlsHBox.setSpacing(10);
        Region spacer = new Region();
        linkRaceView.setTextFill(Color.WHITE);
        hideAcceptedOrders.setTextFill(Color.WHITE);
        hideAcceptedBets.setTextFill(Color.WHITE);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        controlsHBox.getChildren().addAll(linkRaceView, spacer, hideAcceptedOrders, hideAcceptedBets);

        VBox vBox = new VBox();
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.getItems().addAll(orderTableView, betTableView);
        splitPane.setDividerPositions(0.3);
        orderTableView.setMaxHeight(Double.MAX_VALUE);
        betTableView.setMaxHeight(Double.MAX_VALUE);
        vBox.getChildren().addAll(controlsHBox, splitPane);
        vBox.setSpacing(20);
        VBox.setVgrow(splitPane, Priority.ALWAYS);
        orderTableView.setMinHeight(100);
        orderTableView.setPrefHeight(250);
        setAnchorsAndSize(vBox, null, null, 20, 20, 65, 20);
        setAnchorsAndSize(selectionFilter, 25, null, null, 25, 20, null);
        rootPane.getChildren().addAll(vBox, selectionFilter);
    }

    static void initializeOrderSummaryTableView(TableView<OpticonOrderUpdateEntry> orderTableView)
    {
        for (Field field : OpticonOrderUpdateEntry.class.getDeclaredFields()) {
            if (field.getName().equals("opticonOrderStatus")) {
                TableColumn<OpticonOrderUpdateEntry, OpticonOrderStatus> orderStatusColumn = new TableColumn<>("Status");
                orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                orderStatusColumn.setCellFactory(orderStatusCallback());
                orderTableView.getColumns().add(orderStatusColumn);
                continue;
            }
            if (ORDER_TABLE_VIEW_COLUMN_HEADER_NAMES.containsKey(field.getName())) {
                String columnName = ORDER_TABLE_VIEW_COLUMN_HEADER_NAMES.get(field.getName());
                TableColumn<OpticonOrderUpdateEntry, String> tableColumn = new TableColumn<>(columnName);
                tableColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                orderTableView.getColumns().add(tableColumn);
            }
        }
        orderTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_SUBSEQUENT_COLUMNS);
    }

    static void initializeBetTableView(TableView<OpticonBetUpdateEntry> betTableView)
    {
        for (Field field : OpticonBetUpdateEntry.class.getDeclaredFields()) {
            if (field.getName().equals("opticonBetStatus")) {
                TableColumn<OpticonBetUpdateEntry, OpticonBetStatus> betStatusColumn = new TableColumn<>("Status");
                betStatusColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                betStatusColumn.setCellFactory(betStatusCallback());
                betTableView.getColumns().add(betStatusColumn);
                continue;
            }
            if (BET_TABLE_VIEW_COLUMN_HEADER_NAMES.containsKey(field.getName())) {
                String columnName = BET_TABLE_VIEW_COLUMN_HEADER_NAMES.get(field.getName());
                TableColumn<OpticonBetUpdateEntry, String> tableColumn = new TableColumn<>(columnName);
                tableColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                betTableView.getColumns().add(tableColumn);
            }
        }
        betTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_SUBSEQUENT_COLUMNS);
    }

    private static Callback<TableColumn<OpticonOrderUpdateEntry, OpticonOrderStatus>, TableCell<OpticonOrderUpdateEntry, OpticonOrderStatus>> orderStatusCallback() {
        return column -> new TableCell<>() {
            @Override
            protected void updateItem(OpticonOrderStatus finishingPosition, boolean empty) {
                super.updateItem(finishingPosition, empty);
                if (empty || finishingPosition == null) { setText(null); setStyle(""); }
                else {
                    OpticonOrderUpdateEntry entry = getTableView().getItems().get(getIndex());
                    OpticonOrderStatus status = entry.getOpticonOrderStatus();
                    setText(finishingPosition.toString());
                    setStyle(status == OpticonOrderStatus.ACCEPTED ? "-fx-background-color: green; -fx-text-fill: white; "
                            : status == OpticonOrderStatus.ANOMALOUS ? "-fx-background-color: orange; -fx-text-fill: black; "
                            : status == OpticonOrderStatus.IN_FLIGHT ? "-fx-background-color: blue; -fx-text-fill: white; "
                            : "-fx-background-color: red; -fx-text-fill: black;"
                    );
                }
            }
        };
    }

    private static Callback<TableColumn<OpticonBetUpdateEntry, OpticonBetStatus>, TableCell<OpticonBetUpdateEntry, OpticonBetStatus>> betStatusCallback() {
        return column -> new TableCell<>() {
            @Override
            protected void updateItem(OpticonBetStatus finishingPosition, boolean empty) {
                super.updateItem(finishingPosition, empty);
                if (empty || finishingPosition == null) { setText(null); setStyle(""); }
                else {
                    OpticonBetUpdateEntry entry = getTableView().getItems().get(getIndex());
                    setText(finishingPosition.toString());
                    setStyle(entry.getOpticonBetStatus() == OpticonBetStatus.ACCEPTED ? "-fx-background-color: green; -fx-text-fill: white; " : "-fx-background-color: red; -fx-text-fill: black;");
                }
            }
        };
    }
}
