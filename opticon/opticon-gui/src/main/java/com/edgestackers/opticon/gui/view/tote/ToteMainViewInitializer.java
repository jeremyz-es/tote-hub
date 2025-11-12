package com.edgestackers.opticon.gui.view.tote;

import com.edgestackers.core.datamodel.tote.ToteBetType;
import com.edgestackers.opticon.gui.settings.OpticonSceneSettings;
import com.edgestackers.opticon.gui.view.race.RaceViewPane;
import com.edgestackers.opticon.gui.view.util.AccountBalanceTile;
import com.edgestackers.core.datamodel.tote.TotePoolJurisdiction;
import eu.hansolo.tilesfx.Tile;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static com.edgestackers.opticon.gui.view.util.OpticonGuiResourceUtil.OPTICON_CSS;
import static com.edgestackers.opticon.gui.view.util.OpticonViewUtil.setAnchorsAndSize;

enum ToteMainViewInitializer {
    ;
    private static final String STAGE_TITLE = "Opticon | Control View";
    private static final String ACCOUNT_BALANCES_TITLED_PANE_TITLE = "Account Balances";

    static void createAndInitializeView(Stage stage,
                                        AnchorPane rootPane,
                                        Scene scene,
                                        OpticonSceneSettings sceneSettings,
                                        RaceViewPane raceViewPane,
                                        AccountBalanceTile nswTile,
                                        AccountBalanceTile qldTile,
                                        AccountBalanceTile vicTile)
    {
        stage.setTitle(STAGE_TITLE);
        scene.getStylesheets().add(OPTICON_CSS);

        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox();
        vBox.setSpacing(20);

        AnchorPane topPane = new AnchorPane();
        setAnchorsAndSize(raceViewPane.getRootPane(), null, null, 0, 0, 0, 0);
        topPane.getChildren().addAll(raceViewPane.getRootPane());
        vBox.getChildren().addAll(topPane);

        AnchorPane accountBalancesPane = new AnchorPane();
        HBox accountBalancesHBox = new HBox();
        HBox.setHgrow(nswTile, Priority.ALWAYS);
        HBox.setHgrow(qldTile, Priority.ALWAYS);
        HBox.setHgrow(vicTile, Priority.ALWAYS);
        nswTile.setMaxHeight(200);
        qldTile.setMaxHeight(200);
        vicTile.setMaxHeight(200);
        accountBalancesHBox.setSpacing(20);
        accountBalancesHBox.getChildren().addAll(nswTile, qldTile, vicTile);
        accountBalancesHBox.setMinHeight(100);
        accountBalancesHBox.setPrefHeight(200);
        accountBalancesHBox.setMaxHeight(200);
        setAnchorsAndSize(accountBalancesHBox, null, null, 10, 10, 10, 10);
        TitledPane accountBalancesTitledPane = new TitledPane(ACCOUNT_BALANCES_TITLED_PANE_TITLE, accountBalancesHBox);
        accountBalancesTitledPane.setMaxHeight(200);
        accountBalancesTitledPane.setCollapsible(true);
        setAnchorsAndSize(accountBalancesTitledPane, null, null, 0, 20, 20, 20);
        accountBalancesPane.getChildren().add(accountBalancesTitledPane);
        vBox.getChildren().add(accountBalancesPane);

        setAnchorsAndSize(vBox, null, null, 10, 10, 10, 10);
        rootPane.getChildren().add(vBox);
        scrollPane.setContent(rootPane);
        scrollPane.setFitToWidth(true);
        scene.setRoot(scrollPane);

        stage.setX(sceneSettings.stageX());
        stage.setY(sceneSettings.stageY());
        stage.setWidth(sceneSettings.stageWidth());
        stage.setHeight(sceneSettings.stageHeight());
        stage.setScene(scene);
        stage.show();
    }
}
