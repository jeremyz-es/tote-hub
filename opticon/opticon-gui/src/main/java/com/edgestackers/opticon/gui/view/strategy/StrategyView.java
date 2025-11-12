package com.edgestackers.opticon.gui.view.strategy;

import com.edgestackers.opticon.gui.settings.OpticonSceneSettings;
import com.edgestackers.opticon.gui.view.cycles.CycleViewPane;
import com.edgestackers.opticon.gui.view.pacman.PacmanFlucsViewPane;
import com.edgestackers.opticon.gui.view.turbo.TurboViewPane;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static com.edgestackers.opticon.gui.view.strategy.StrategyViewInitializer.createAndInitializeView;

public class StrategyView {
    private final Stage stage = new Stage();
    private final AnchorPane rootPane = new AnchorPane();
    private final TabPane tabPane = new TabPane();
    private final Scene scene = new Scene(rootPane);
    private final CycleViewPane cycleViewPane;
    private final TurboViewPane turboViewPane;
    private final PacmanFlucsViewPane pacmanFlucsViewPane;
    private final ComboBox<ToteBetType> betTypeSelector = new ComboBox<>();
    private final ComboBox<TotePoolJurisdiction> jurisdictionSelector = new ComboBox<>();

    public StrategyView(CycleViewPane cycleViewPane,
                        TurboViewPane turboViewPane,
                        PacmanFlucsViewPane pacmanFlucsViewPane)
    {
        this.cycleViewPane = cycleViewPane;
        this.turboViewPane = turboViewPane;
        this.pacmanFlucsViewPane = pacmanFlucsViewPane;
    }

    public void initialize(OpticonSceneSettings sceneSettings) {
        createView(sceneSettings);
    }

    private void createView(OpticonSceneSettings sceneSettings) {
        createAndInitializeView(stage, rootPane, tabPane, scene, sceneSettings, cycleViewPane, turboViewPane, pacmanFlucsViewPane, betTypeSelector, jurisdictionSelector);
    }

    public Stage getStage() { return stage; }

    public ComboBox<ToteBetType> getBetTypeSelector() { return betTypeSelector; }

    public ComboBox<TotePoolJurisdiction> getJurisdictionSelector() { return jurisdictionSelector; }
}
