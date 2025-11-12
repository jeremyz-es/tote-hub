package com.edgestackers.opticon.gui.view.parameters.crud;

import com.edgestackers.opticon.gui.datamodel.parameters.ToteStrategyParametersEntry;
import com.edgestackers.tote.hub.core.datamodel.core.*;
import com.edgestackers.tote.hub.core.datamodel.turbo.*;
import com.edgestackers.tote.hub.core.parameters.ToteStrategyParameters;
import jakarta.annotation.Nullable;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;
import java.util.UUID;

import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;


public class ToteStrategyParametersCrudViewModel {
    private final SimpleStringProperty lastUpdatedAtQldTime = new SimpleStringProperty();
    private final SimpleStringProperty strategyId = new SimpleStringProperty("");
    private final SimpleStringProperty strategyName = new SimpleStringProperty("");
    private final SimpleObjectProperty<RaceType> raceType = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<ToteProvider> toteProvider = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<ToteBetType> toteBetType = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<TotePoolJurisdiction> jurisdiction = new SimpleObjectProperty<>();
    private final SimpleStringProperty maxRaceBet = new SimpleStringProperty("");
    private final SimpleStringProperty rebate = new SimpleStringProperty("");
    private final SimpleStringProperty takeout = new SimpleStringProperty("");
    private final SimpleStringProperty discount = new SimpleStringProperty("");
    private final SimpleStringProperty minBet = new SimpleStringProperty("");
    private final SimpleObjectProperty<TurboExoticDividendModel> dividendModel = new SimpleObjectProperty<>();
    private final SimpleStringProperty dividendModelMajorVersion = new SimpleStringProperty("");
    private final SimpleStringProperty dividendModelMinorVersion = new SimpleStringProperty("");
    private final SimpleObjectProperty<TurboExoticTheoModel> theoModel = new SimpleObjectProperty<>();
    private final SimpleStringProperty theoModelMajorVersion = new SimpleStringProperty("");
    private final SimpleStringProperty theoModelMinorVersion = new SimpleStringProperty("");
    private final SimpleStringProperty maxDividendAgeSeconds = new SimpleStringProperty("");
    private final SimpleStringProperty maxTheoAgeSeconds = new SimpleStringProperty("");
    private final SimpleStringProperty minPoolSize = new SimpleStringProperty("");
    private final SimpleStringProperty maxPoolSize =  new SimpleStringProperty("");
    private final SimpleStringProperty error = new SimpleStringProperty("");
    private boolean isCreating = false;

    void handle(@Nullable ToteStrategyParametersEntry entry) {
        isCreating = entry == null;
        Platform.runLater(() -> {
            lastUpdatedAtQldTime.setValue(entry != null ? String.format("Last Updated @: %s", entry.getLastUpdatedAtQldTime()) : "");
            strategyId.set(entry != null ? entry.getStrategyId() : "");
            strategyName.set(entry != null ? entry.getStrategyName() : "");
            raceType.setValue(entry != null ? entry.getRaceType() : null);
            toteProvider.setValue(entry != null ? entry.getToteProvider() : null);
            toteBetType.setValue(entry != null ? entry.getToteBetType() : null);
            jurisdiction.setValue(entry != null ? entry.getJurisdiction() : null);
            maxRaceBet.setValue(entry != null ? String.valueOf(entry.getMaxRaceBet()) : "");
            rebate.setValue(entry != null ? String.valueOf(entry.getRebate()) : "");
            takeout.setValue(entry != null ? String.valueOf(entry.getTakeout()) : "");
            discount.setValue(entry != null ? String.valueOf(entry.getDiscount()) : "");
            minBet.setValue(entry != null ? String.valueOf(entry.getMinBet()) : "");
            dividendModel.set(entry != null ? entry.getDividendModel() : null);
            dividendModelMajorVersion.set(entry != null ? String.valueOf(entry.getDividendModelMajorVersion()) : "");
            dividendModelMinorVersion.set(entry != null ? String.valueOf(entry.getDividendModelMinorVersion()) : "");
            theoModel.set(entry != null ? entry.getTheoModel() : null);
            theoModelMajorVersion.set(entry != null ? String.valueOf(entry.getTheoModelMajorVersion()) : "");
            theoModelMinorVersion.set(entry != null ? String.valueOf(entry.getTheoModelMinorVersion()) : "");
            minPoolSize.set(entry != null ? String.valueOf(entry.getMinPool()) : "");
            maxPoolSize.set(entry != null ? String.valueOf(entry.getMaxPool()) : "");
            maxDividendAgeSeconds.setValue(entry != null ? String.valueOf(entry.getMaxDividendAgeSeconds()) : "");
            maxTheoAgeSeconds.setValue(entry != null ? String.valueOf(entry.getMaxTheoAgeSeconds()) : "");
            error.set("");
        });
    }

    void setError(String error) {
        Platform.runLater(() -> this.error.set(error));
    }

    ToteStrategyParameters composeToteStrategyParameters(List<ToteRaceEvent> triggerEvents) {
        long timestamp = generateEpochNanosTimestamp();
        return new ToteStrategyParameters(
                isCreating ? UUID.randomUUID().toString() : strategyId.get(),
                strategyName.get(),
                timestamp,
                timestamp,
                raceType.getValue(),
                toteProvider.getValue(),
                toteBetType.getValue(),
                jurisdiction.getValue(),
                Double.parseDouble(maxRaceBet.get()),
                Double.parseDouble(rebate.get()),
                Double.parseDouble(takeout.get()),
                Double.parseDouble(discount.get()),
                Double.parseDouble(minBet.get()),
                Double.parseDouble(minPoolSize.get()),
                Double.parseDouble(maxPoolSize.get()),
                Integer.parseInt(maxDividendAgeSeconds.get()),
                Integer.parseInt(maxTheoAgeSeconds.get()),
                dividendModel.get(),
                Integer.parseInt(dividendModelMajorVersion.get()),
                Integer.parseInt(dividendModelMinorVersion.get()),
                theoModel.get(),
                Integer.parseInt(theoModelMajorVersion.get()),
                Integer.parseInt(theoModelMinorVersion.get()),
                triggerEvents
        );
    }

    public SimpleStringProperty lastedUpdatedAtQldTimeProperty() { return lastUpdatedAtQldTime; }

    public SimpleStringProperty strategyIdProperty() { return strategyId; }

    public SimpleStringProperty strategyNameProperty() { return strategyName; }

    public SimpleObjectProperty<RaceType> raceTypeProperty() { return raceType; }

    public SimpleObjectProperty<ToteProvider> toteProviderProperty() { return toteProvider; }

    public SimpleObjectProperty<ToteBetType> toteBetTypeProperty() { return toteBetType; }

    public SimpleObjectProperty<TotePoolJurisdiction> jurisdictionProperty() { return jurisdiction; }

    public SimpleStringProperty maxRaceBetProperty() { return maxRaceBet; }

    public SimpleStringProperty rebateProperty() { return rebate; }

    public SimpleStringProperty takeoutProperty() { return takeout; }

    public SimpleStringProperty discountProperty() { return discount; }

    public SimpleStringProperty minBetProperty() { return minBet; }

    public SimpleStringProperty minPoolSizeProperty() { return minPoolSize; }

    public SimpleStringProperty maxPoolSizeProperty() { return maxPoolSize; }

    public SimpleStringProperty maxDividendAgeSecondsProperty() { return maxDividendAgeSeconds; }

    public SimpleStringProperty maxTheoAgeSecondsProperty() { return maxTheoAgeSeconds; }

    public SimpleObjectProperty<TurboExoticDividendModel> dividendModelProperty() { return dividendModel; }

    public SimpleStringProperty dividendModelMajorVersionProperty() { return dividendModelMajorVersion; }

    public SimpleStringProperty dividendModelMinorVersionProperty() { return dividendModelMinorVersion; }

    public SimpleStringProperty theoModelMajorVersionProperty() { return theoModelMajorVersion; }

    public SimpleStringProperty theoModelMinorVersionProperty() { return theoModelMinorVersion; }

    public SimpleObjectProperty<TurboExoticTheoModel> theoModelProperty() { return theoModel; }

    public SimpleStringProperty errorProperty() { return error; }
}
