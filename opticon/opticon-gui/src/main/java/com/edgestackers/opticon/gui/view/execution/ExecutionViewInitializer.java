package com.edgestackers.opticon.gui.view.execution;

import com.edgestackers.opticon.gui.settings.OpticonSceneSettings;
import com.edgestackers.opticon.gui.view.order.OrderViewPane;
import com.edgestackers.opticon.gui.view.util.OpticonGuiResourceUtil;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static com.edgestackers.opticon.gui.view.util.OpticonViewUtil.setAnchorsAndSize;

public enum ExecutionViewInitializer {
    ;
    private static final String STAGE_TITLE = "Opticon | Execution View";

    public static void createAndInitializeView(Stage stage,
                                               AnchorPane rootPane,
                                               TabPane tabPane,
                                               Scene scene,
                                               OpticonSceneSettings settings,
                                               OrderViewPane orderViewPane)
    {
        Tab orderViewPaneTab = new Tab("Order View");
        orderViewPaneTab.setContent(orderViewPane.getRootPane());

        orderViewPaneTab.setClosable(false);

        tabPane.getTabs().add(orderViewPaneTab);

        setAnchorsAndSize(tabPane, null, null, 10, 10, 10, 10);
        rootPane.getChildren().add(tabPane);

        stage.setX(settings.stageX());
        stage.setY(settings.stageY());
        stage.setHeight(settings.stageHeight());
        stage.setWidth(settings.stageWidth());

        scene.getStylesheets().add(OpticonGuiResourceUtil.OPTICON_CSS);
        stage.setTitle(STAGE_TITLE);
        stage.setScene(scene);
        stage.show();
    }
}
