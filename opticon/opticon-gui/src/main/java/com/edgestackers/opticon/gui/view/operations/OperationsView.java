package com.edgestackers.opticon.gui.view.operations;

import com.edgestackers.opticon.gui.datamodel.operations.OpticonStrategyContextSummaryEntry;
import com.edgestackers.opticon.gui.settings.OpticonSceneSettings;
import com.edgestackers.tote.hub.core.datamodel.core.*;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import static com.edgestackers.opticon.gui.view.operations.OperationsViewInitializer.createAndInitializeView;
import static com.edgestackers.opticon.gui.view.operations.OperationsViewInitializer.initializeTableView;

public class OperationsView {
    private final Stage stage = new Stage();
    private final AnchorPane rootPane = new AnchorPane();
    private final Scene scene = new Scene(rootPane);
    private final TableView<OpticonStrategyContextSummaryEntry> tableView = new TableView<>();
    private final CheckComboBox<ToteBetType> betTypeSelector = new CheckComboBox<>();
    private final CheckComboBox<TotePoolJurisdiction> jurisdictionSelector = new CheckComboBox<>();
    private final CheckBox linkRaceFilter = new CheckBox("Link Race View");

    public void initialize(OpticonSceneSettings sceneSettings) {
        createView(sceneSettings);
        betTypeSelector.getCheckModel().checkAll();
        jurisdictionSelector.getCheckModel().checkAll();
    }

    private void createView(OpticonSceneSettings sceneSettings) {
        createAndInitializeView(stage, rootPane, scene, sceneSettings, betTypeSelector, jurisdictionSelector, tableView, linkRaceFilter);
        initializeTableView(tableView);
    }

    public Stage getStage() { return stage; }

    public CheckComboBox<ToteBetType> getBetTypeSelector() { return betTypeSelector; }

    public CheckComboBox<TotePoolJurisdiction> getJurisdictionSelector() { return jurisdictionSelector; }

    public TableView<OpticonStrategyContextSummaryEntry> getTableView() { return tableView; }

    public CheckBox getLinkRaceFilter() { return linkRaceFilter; }
}
