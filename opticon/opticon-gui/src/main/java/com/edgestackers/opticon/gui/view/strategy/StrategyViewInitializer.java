package com.edgestackers.opticon.gui.view.strategy;

import com.edgestackers.opticon.gui.settings.OpticonSceneSettings;
import com.edgestackers.opticon.gui.view.cycles.CycleViewPane;
import com.edgestackers.opticon.gui.view.pacman.PacmanFlucsViewPane;
import com.edgestackers.opticon.gui.view.turbo.TurboViewPane;
import com.edgestackers.opticon.gui.view.util.OpticonGuiResourceUtil;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static com.edgestackers.opticon.gui.view.util.OpticonViewUtil.setAnchorsAndSize;

public enum StrategyViewInitializer {
    ;
    private static final String STAGE_TITLE = "Opticon | Strategy View";

    public static void createAndInitializeView(Stage stage,
                                               AnchorPane rootPane,
                                               TabPane tabPane,
                                               Scene scene,
                                               OpticonSceneSettings settings,
                                               CycleViewPane cycleViewPane,
                                               TurboViewPane turboViewPane,
                                               PacmanFlucsViewPane pacmanFlucsViewPane,
                                               ComboBox<ToteBetType> betTypeSelector,
                                               ComboBox<TotePoolJurisdiction> jurisdictionSelector)
    {
        Tab cycleViewPaneTab = new Tab("Cycles");
        Tab turboViewPaneTab = new Tab("Turbo");
        Tab pacmanFlucsViewPaneTab = new Tab("Pacman");

        cycleViewPaneTab.setClosable(false);
        turboViewPaneTab.setClosable(false);
        pacmanFlucsViewPaneTab.setClosable(false);

        cycleViewPaneTab.setContent(cycleViewPane.getRootPane());
        turboViewPaneTab.setContent(turboViewPane.getRootPane());
        pacmanFlucsViewPaneTab.setContent(pacmanFlucsViewPane.getRootPane());

        tabPane.getTabs().addAll(cycleViewPaneTab, turboViewPaneTab, pacmanFlucsViewPaneTab);
        setAnchorsAndSize(tabPane, null, null, 10, 10, 45, 10);
        setAnchorsAndSize(betTypeSelector, 25, 150, null, 25, 20, null);
        setAnchorsAndSize(jurisdictionSelector, 25, 100, null, 180, 20, null);
        rootPane.getChildren().addAll(tabPane, betTypeSelector, jurisdictionSelector);

        stage.setX(settings.stageX());
        stage.setY(settings.stageY());
        stage.setHeight(settings.stageHeight());
        stage.setWidth(settings.stageWidth());

        scene.getStylesheets().add(OpticonGuiResourceUtil.OPTICON_CSS);
        stage.setScene(scene);
        stage.setTitle(STAGE_TITLE);
        stage.show();
    }
}
