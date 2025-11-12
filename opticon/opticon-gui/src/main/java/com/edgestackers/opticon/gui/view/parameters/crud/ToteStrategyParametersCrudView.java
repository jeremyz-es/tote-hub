package com.edgestackers.opticon.gui.view.parameters.crud;

import com.edgestackers.tote.hub.core.datamodel.core.*;
import com.edgestackers.tote.hub.core.datamodel.turbo.*;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import static com.edgestackers.opticon.gui.view.parameters.crud.ToteStrategyParametersCrudViewInitializer.createAndInitializeView;

public class ToteStrategyParametersCrudView {
    private final Stage stage = new Stage();
    private final AnchorPane rootPane = new AnchorPane();
    private final Scene scene = new Scene(rootPane);
    private final Label errorLabel = new Label();
    private final Button deleteButton = new Button("Delete");
    private final Button submitButton = new Button("Submit");

    private final Label lastUpdatedAtQldTime = new Label();
    private final TextField strategyNameField = new TextField();
    private final ComboBox<RaceType> raceType = new ComboBox<>(FXCollections.observableArrayList(RaceType.values()));
    private final ComboBox<ToteProvider> toteProvider = new ComboBox<>(FXCollections.observableArrayList(ToteProvider.values()));
    private final ComboBox<ToteBetType> toteBetType = new ComboBox<>(FXCollections.observableArrayList(ToteBetType.values()));
    private final ComboBox<TotePoolJurisdiction> jurisdiction = new ComboBox<>(FXCollections.observableArrayList(TotePoolJurisdiction.values()));
    private final ComboBox<TurboExoticDividendModel> dividendModel = new ComboBox<>(FXCollections.observableArrayList(TurboExoticDividendModel.values()));
    private final TextField dividendModelMajorVersion = new TextField();
    private final TextField dividendModelMinorVersion = new TextField();
    private final ComboBox<TurboExoticTheoModel> theoModel = new ComboBox<>(FXCollections.observableArrayList(TurboExoticTheoModel.values()));
    private final TextField theoModelMajorVersion = new TextField();
    private final TextField theoModelMinorVersion = new TextField();

    private final TextField maxRaceBetField = new TextField();
    private final TextField rebateField = new TextField();
    private final TextField takeoutField = new TextField();
    private final TextField discountField = new TextField();
    private final TextField minBetField = new TextField();
    private final TextField minPoolField = new TextField();
    private final TextField maxPoolField = new TextField();
    private final TextField maxDividendAgeSecondsField = new TextField();
    private final TextField maxTheoAgeSecondsField = new TextField();
    private final CheckComboBox<ToteRaceEvent> triggerRaceEvents = new CheckComboBox<>();


    public void initialize() {
        createView();
    }

    public void open() {
        stage.show();
    }

    private void createView() {
        createAndInitializeView(stage, rootPane, scene, lastUpdatedAtQldTime,
                strategyNameField, raceType, toteProvider, toteBetType, jurisdiction, dividendModel, dividendModelMajorVersion, dividendModelMinorVersion,
                theoModel, theoModelMajorVersion, theoModelMinorVersion,
                maxRaceBetField, rebateField, takeoutField,
                discountField, minBetField, minPoolField, maxPoolField, maxDividendAgeSecondsField, maxTheoAgeSecondsField,
                triggerRaceEvents, errorLabel, deleteButton, submitButton);
    }

    public Stage getStage() { return stage; }

    public Label getLastUpdatedAtQldTime() { return lastUpdatedAtQldTime; }

    public TextField getStrategyNameField() { return strategyNameField; }

    public ComboBox<RaceType> getRaceType() { return raceType; }

    public ComboBox<ToteProvider> getToteProvider() { return toteProvider; }

    public ComboBox<ToteBetType> getToteBetType() { return toteBetType; }

    public ComboBox<TotePoolJurisdiction> getJurisdiction() { return jurisdiction; }

    public ComboBox<TurboExoticDividendModel> getDividendModel() { return dividendModel; }

    public TextField getDividendModelMajorVersion() { return dividendModelMajorVersion; }

    public TextField getDividendModelMinorVersion() { return dividendModelMinorVersion; }

    public ComboBox<TurboExoticTheoModel> getTheoModel() { return theoModel; }

    public TextField getTheoModelMajorVersion() { return theoModelMajorVersion; }

    public TextField getTheoModelMinorVersion() { return theoModelMinorVersion; }

    public TextField getMaxRaceBetField() { return maxRaceBetField; }

    public TextField getRebateField() { return rebateField; }

    public TextField getTakeoutField() { return takeoutField; }

    public TextField getDiscountField() { return discountField; }

    public TextField getMinBetField() {return minBetField; }

    public TextField getMinPoolField() { return minPoolField; }

    public TextField getMaxPoolField() { return maxPoolField; }

    public TextField getMaxDividendAgeSecondsField() { return maxDividendAgeSecondsField; }

    public TextField getMaxTheoAgeSecondsField() { return maxTheoAgeSecondsField; }

    public CheckComboBox<ToteRaceEvent> getTriggerRaceEvents() { return triggerRaceEvents; }

    public Button getDeleteButton() { return deleteButton; }

    public Button getSubmitButton() { return submitButton; }

    public Label getErrorLabel() { return errorLabel; }
}
