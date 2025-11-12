package com.edgestackers.opticon.gui.view.execution;

import com.edgestackers.opticon.gui.settings.OpticonSceneSettings;
import com.edgestackers.opticon.gui.view.order.OrderViewPane;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static com.edgestackers.opticon.gui.view.execution.ExecutionViewInitializer.createAndInitializeView;

public class ExecutionView {
    private final Stage stage = new Stage();
    private final AnchorPane rootPane = new AnchorPane();
    private final TabPane tabPane = new TabPane();
    private final Scene scene = new Scene(rootPane);
    private final OrderViewPane orderViewPane;

    public ExecutionView(OrderViewPane orderViewPane) {
        this.orderViewPane = orderViewPane;
    }

    public void initialize(OpticonSceneSettings settings) {
        createView(settings);
    }

    private void createView(OpticonSceneSettings settings) {
        createAndInitializeView(stage, rootPane, tabPane, scene, settings, orderViewPane);
    }

    public Stage getStage() { return stage; }
}
