package com.edgestackers.opticon.gui.view.parameters.crud;

import com.edgestackers.tote.hub.core.datamodel.core.*;
import com.edgestackers.tote.hub.core.datamodel.turbo.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import static com.edgestackers.opticon.gui.view.util.OpticonGuiResourceUtil.OPTICON_CSS;
import static com.edgestackers.opticon.gui.view.util.OpticonViewUtil.setAnchorsAndSize;

public enum ToteStrategyParametersCrudViewInitializer {
    ;
    private static final String STAGE_TITLE = "Opticon | Tote Strategy Parameters CRUD";
    private static final double BUTTON_WIDTH = 100.0;

    static void createAndInitializeView(Stage stage,
                                        AnchorPane rootPane,
                                        Scene scene,
                                        Label lastUpdatedAtQldTime,
                                        TextField strategyNameField,
                                        ComboBox<RaceType> raceTypeField,
                                        ComboBox<ToteProvider> toteProviderField,
                                        ComboBox<ToteBetType> toteBetTypeField,
                                        ComboBox<TotePoolJurisdiction> jurisdictionField,
                                        ComboBox<TurboExoticDividendModel> dividendModel,
                                        TextField dividendModelMajorVersionField,
                                        TextField dividendModelMinorVersionField,
                                        ComboBox<TurboExoticTheoModel> theoModel,
                                        TextField theoModelMajorVersionField,
                                        TextField theoModelMinorVersionField,
                                        TextField maxRaceBetField,
                                        TextField rebateField,
                                        TextField takeoutField,
                                        TextField discountField,
                                        TextField minBetField,
                                        TextField minPoolField,
                                        TextField maxPoolField,
                                        TextField maxDividendAgeSecondsField,
                                        TextField maxTheoAgeSecondsField,
                                        CheckComboBox<ToteRaceEvent> triggerRaceEvents,
                                        Label errorLabel,
                                        Button deleteButton,
                                        Button submitButton)
    {

        triggerRaceEvents.getItems().addAll(ToteRaceEvent.values());

        setAnchorsAndSize(lastUpdatedAtQldTime, 25, null, 25, 20, null, 20);
        lastUpdatedAtQldTime.setTextFill(Color.WHITE);
        lastUpdatedAtQldTime.setAlignment(Pos.CENTER);
        rootPane.getChildren().add(lastUpdatedAtQldTime);

        appendComponents(rootPane, "Strategy Name", strategyNameField, 70);
        appendComponents(rootPane, "Race Type", raceTypeField, 110);
        appendComponents(rootPane, "Tote Provider", toteProviderField, 150);
        appendComponents(rootPane, "Bet Type", toteBetTypeField, 190);
        appendComponents(rootPane, "Jurisdiction", jurisdictionField, 230);
        appendComponents(rootPane, "Div Model", dividendModel, 270);
        appendComponents(rootPane, "Div Model Major Ver", dividendModelMajorVersionField, 310);
        appendComponents(rootPane, "Div Model Minor Ver", dividendModelMinorVersionField, 350);
        appendComponents(rootPane, "Theo Model", theoModel, 390);
        appendComponents(rootPane, "Theo Model Major Ver", theoModelMajorVersionField, 430);
        appendComponents(rootPane, "Theo Model Minor Ver", theoModelMinorVersionField, 470);

        appendComponents(rootPane, "Max Race Bet", maxRaceBetField, 510);
        appendComponents(rootPane, "Rebate", rebateField, 550);
        appendComponents(rootPane, "Takeout", takeoutField, 590);
        appendComponents(rootPane, "Discount", discountField, 630);
        appendComponents(rootPane, "Min Bet", minBetField, 670);
        appendComponents(rootPane, "Min Pool", minPoolField, 710);
        appendComponents(rootPane, "Max Pool", maxPoolField, 750);
        appendComponents(rootPane, "Max Dividend Age", maxDividendAgeSecondsField, 790);
        appendComponents(rootPane, "Max Theo Age", maxTheoAgeSecondsField, 830);
        appendComponents(rootPane, "Triggers", triggerRaceEvents, 870);

        submitButton.setPrefWidth(BUTTON_WIDTH);
        submitButton.setPrefHeight(40.0);
        HBox buttonsBox = new HBox();
        buttonsBox.setSpacing(10);
        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        buttonsBox.getChildren().addAll(leftSpacer, deleteButton, submitButton, rightSpacer);
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);
        Region bottomSpacer = new Region();
        bottomSpacer.setMinHeight(10);
        setAnchorsAndSize(errorLabel, 25, null, 930, 10, null, 10);
        setAnchorsAndSize(buttonsBox, 25, null, 990, 10, null, 10);
        setAnchorsAndSize(bottomSpacer, 25, null, 1020, 10, null, 10);
        rootPane.getChildren().addAll(errorLabel, buttonsBox, bottomSpacer);
        errorLabel.setTextFill(Color.WHITE);
        errorLabel.setAlignment(Pos.CENTER);

        scene.getStylesheets().add(OPTICON_CSS);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(rootPane);
        scene.setRoot(scrollPane);
        scrollPane.setFitToWidth(true);

        stage.setHeight(600);
        stage.setWidth(400);

        stage.setScene(scene);
        stage.setTitle(STAGE_TITLE);
    }

    private static void appendComponents(AnchorPane anchorPane, String labelText, Region field, int topOffset) {
        Label label = new Label(labelText);
        label.setTextFill(Color.WHITE);
        setAnchorsAndSize(label, 25, 130, topOffset, 25, null, null);
        setAnchorsAndSize(field, 25, 100, topOffset, 180, null, 25);
        anchorPane.getChildren().addAll(label, field);
    }
}

