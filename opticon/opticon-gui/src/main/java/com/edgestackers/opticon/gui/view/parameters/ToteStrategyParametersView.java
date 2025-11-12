package com.edgestackers.opticon.gui.view.parameters;

import com.edgestackers.opticon.gui.datamodel.parameters.ToteStrategyParametersEntry;
import com.edgestackers.opticon.gui.settings.OpticonSceneSettings;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import static com.edgestackers.opticon.gui.view.parameters.ToteStrategyParametersViewInitializer.createAndInitializeView;
import static com.edgestackers.opticon.gui.view.parameters.ToteStrategyParametersViewInitializer.initializeTableView;

public class ToteStrategyParametersView {
    private final Stage stage = new Stage();
    private final AnchorPane rootPane = new AnchorPane();
    private final Scene scene = new Scene(rootPane);
    private final TableView<ToteStrategyParametersEntry> tableView = new TableView<>();
    private final CheckComboBox<ToteBetType> betTypeSelector = new CheckComboBox<>();
    private final Button refreshButton = new Button("Refresh");
    private final Button createButton = new Button("New");

    public void initialize(OpticonSceneSettings sceneSettings) {
        createView(sceneSettings);
        betTypeSelector.getCheckModel().checkAll();
    }

    private void createView(OpticonSceneSettings sceneSettings) {
        createAndInitializeView(stage, rootPane, scene, sceneSettings, betTypeSelector, tableView, refreshButton, createButton);
        initializeTableView(tableView);
    }

    public Stage getStage() { return stage; }

    public CheckComboBox<ToteBetType> getBetTypeSelector() {
        return betTypeSelector;
    }

    public TableView<ToteStrategyParametersEntry> getTableView() { return tableView; }

    public Button getRefreshButton() { return refreshButton; }

    public Button getCreateButton() { return createButton; }
}
