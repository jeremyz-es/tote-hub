package com.edgestackers.opticon.gui.datamodel.parameters;

import com.edgestackers.tote.hub.core.datamodel.core.*;
import com.edgestackers.tote.hub.core.datamodel.turbo.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

import java.util.List;

public class ToteStrategyParametersEntry {
    private final String strategyId;
    private final SimpleStringProperty strategyName;
    private final SimpleStringProperty createdAtQldTime;
    private final SimpleStringProperty lastUpdatedAtQldTime;
    private final SimpleObjectProperty<RaceType> raceType;
    private final SimpleObjectProperty<ToteProvider> toteProvider;
    private final SimpleObjectProperty<ToteBetType> toteBetType;
    private final SimpleObjectProperty<TotePoolJurisdiction> jurisdiction;
    private final SimpleObjectProperty<TurboExoticDividendModel> dividendModel;
    private final SimpleIntegerProperty dividendModelMajorVersion;
    private final SimpleIntegerProperty dividendModelMinorVersion;
    private final SimpleStringProperty dividendModelDescriptor;
    private final SimpleObjectProperty<TurboExoticTheoModel> theoModel;
    private final SimpleIntegerProperty theoModelMajorVersion;
    private final SimpleIntegerProperty theoModelMinorVersion;
    private final SimpleStringProperty theoModelDescriptor;
    private final SimpleDoubleProperty maxRaceBet;
    private final SimpleDoubleProperty rebate;
    private final SimpleDoubleProperty takeout;
    private final SimpleDoubleProperty discount;
    private final SimpleDoubleProperty minBet;
    private final SimpleDoubleProperty minPool;
    private final SimpleDoubleProperty maxPool;
    private final SimpleIntegerProperty maxDividendAgeSeconds;
    private final SimpleIntegerProperty maxTheoAgeSeconds;
    private final SimpleListProperty<ToteRaceEvent> triggerRaceEvents;

    public ToteStrategyParametersEntry(String strategyId,
                                       String strategyName,
                                       String createdAtQldTime,
                                       String lastUpdatedAtQldTime,
                                       RaceType raceType,
                                       ToteProvider toteProvider,
                                       ToteBetType toteBetType,
                                       TotePoolJurisdiction jurisdiction,
                                       double maxRaceBet,
                                       double rebate,
                                       double takeout,
                                       double discount,
                                       double minBet,
                                       double minPool,
                                       double maxPool,
                                       TurboExoticDividendModel dividendModel,
                                       int dividendModelMajorVersion,
                                       int dividendModelMinorVersion,
                                       String dividendModelDescriptor,
                                       TurboExoticTheoModel theoModel,
                                       int theoModelMajorVersion,
                                       int theoModelMinorVersion,
                                       String theoModelDescriptor,
                                       int maxDividendAgeSeconds,
                                       int maxTheoAgeSeconds,
                                       List<ToteRaceEvent> triggerRaceEvents)
    {
        this.strategyId = strategyId;
        this.strategyName = new SimpleStringProperty(strategyName);
        this.createdAtQldTime = new SimpleStringProperty(createdAtQldTime);
        this.lastUpdatedAtQldTime = new SimpleStringProperty(lastUpdatedAtQldTime);
        this.raceType = new SimpleObjectProperty<>(raceType);
        this.toteProvider = new SimpleObjectProperty<>(toteProvider);
        this.toteBetType = new SimpleObjectProperty<>(toteBetType);
        this.jurisdiction = new SimpleObjectProperty<>(jurisdiction);
        this.maxRaceBet = new SimpleDoubleProperty(maxRaceBet);
        this.rebate = new SimpleDoubleProperty(rebate);
        this.takeout = new SimpleDoubleProperty(takeout);
        this.discount = new SimpleDoubleProperty(discount);
        this.minBet = new SimpleDoubleProperty(minBet);
        this.dividendModel = new SimpleObjectProperty<>(dividendModel);
        this.dividendModelMajorVersion = new SimpleIntegerProperty(dividendModelMajorVersion);
        this.dividendModelMinorVersion = new SimpleIntegerProperty(dividendModelMinorVersion);
        this.dividendModelDescriptor = new SimpleStringProperty(dividendModelDescriptor);
        this.theoModel = new SimpleObjectProperty<>(theoModel);
        this.theoModelMajorVersion = new SimpleIntegerProperty(theoModelMajorVersion);
        this.theoModelMinorVersion = new SimpleIntegerProperty(theoModelMinorVersion);
        this.theoModelDescriptor = new SimpleStringProperty(theoModelDescriptor);
        this.minPool = new SimpleDoubleProperty(minPool);
        this.maxPool = new SimpleDoubleProperty(maxPool);
        this.maxDividendAgeSeconds = new SimpleIntegerProperty(maxDividendAgeSeconds);
        this.maxTheoAgeSeconds = new SimpleIntegerProperty(maxTheoAgeSeconds);
        this.triggerRaceEvents = new SimpleListProperty<>(FXCollections.observableArrayList(triggerRaceEvents));
    }

    public String getStrategyId() { return strategyId; }

    public String getStrategyName() { return strategyName.get(); }
    public SimpleStringProperty strategyNameProperty() { return strategyName; }

    public String getCreatedAtQldTime() { return createdAtQldTime.get(); }
    public SimpleStringProperty createdAtQldTimeProperty() { return createdAtQldTime; }

    public String getLastUpdatedAtQldTime() { return lastUpdatedAtQldTime.get(); }
    public SimpleStringProperty lastUpdatedAtQldTimeProperty() { return lastUpdatedAtQldTime; }

    public RaceType getRaceType() { return raceType.get(); }
    public SimpleObjectProperty<RaceType> raceTypeProperty() { return raceType; }

    public ToteProvider getToteProvider() { return toteProvider.get(); }
    public SimpleObjectProperty<ToteProvider> toteProviderProperty() { return toteProvider; }

    public ToteBetType getToteBetType() { return toteBetType.get(); }
    public SimpleObjectProperty<ToteBetType> toteBetTypeProperty() { return toteBetType; }

    public TotePoolJurisdiction getJurisdiction() { return jurisdiction.get(); }
    public SimpleObjectProperty<TotePoolJurisdiction> jurisdictionProperty() { return jurisdiction; }

    public double getMaxRaceBet() { return maxRaceBet.get(); }
    public SimpleDoubleProperty maxRaceBetProperty() { return maxRaceBet; }

    public double getRebate() { return rebate.get(); }
    public SimpleDoubleProperty rebateProperty() { return rebate; }

    public double getTakeout() { return takeout.get(); }
    public SimpleDoubleProperty takeoutProperty() { return takeout; }

    public double getDiscount() { return discount.get(); }
    public SimpleDoubleProperty discountProperty() { return discount; }

    public double getMinBet() { return minBet.get(); }
    public SimpleDoubleProperty minBetProperty() { return minBet; }

    public double getMinPool() { return minPool.get(); }
    public SimpleDoubleProperty minPoolProperty() { return minPool; }

    public double getMaxPool() { return maxPool.get(); }
    public SimpleDoubleProperty maxPoolProperty() { return maxPool; }

    public int getMaxDividendAgeSeconds() { return maxDividendAgeSeconds.get(); }
    public SimpleIntegerProperty maxDividendAgeSecondsProperty() { return maxDividendAgeSeconds; }

    public int getMaxTheoAgeSeconds() { return maxTheoAgeSeconds.get(); }
    public SimpleIntegerProperty maxTheoAgeSecondsProperty() { return maxTheoAgeSeconds; }

    public TurboExoticDividendModel getDividendModel() { return dividendModel.get(); }
    public SimpleObjectProperty<TurboExoticDividendModel> dividendModelProperty() { return dividendModel; }

    public int getDividendModelMajorVersion() { return dividendModelMajorVersion.get(); }
    public SimpleIntegerProperty dividendModelMajorVersionProperty() { return dividendModelMajorVersion; }

    public int getDividendModelMinorVersion() { return dividendModelMinorVersion.get(); }
    public SimpleIntegerProperty dividendModelMinorVersionProperty() { return dividendModelMinorVersion; }

    public String getDividendModelDescriptor() { return dividendModelDescriptor.get(); }
    public SimpleStringProperty dividendModelDescriptorProperty() { return dividendModelDescriptor; }

    public TurboExoticTheoModel getTheoModel() { return theoModel.get(); }
    public SimpleObjectProperty<TurboExoticTheoModel> theoModelProperty() { return theoModel; }

    public int getTheoModelMajorVersion() { return theoModelMajorVersion.get(); }
    public SimpleIntegerProperty theoModelMajorVersionProperty() { return theoModelMajorVersion; }

    public int getTheoModelMinorVersion() { return theoModelMinorVersion.get(); }
    public SimpleIntegerProperty theoModelMinorVersionProperty() { return theoModelMinorVersion; }

    public String getTheoModelDescriptor() { return theoModelDescriptor.get(); }
    public SimpleStringProperty theoModelDescriptorProperty() { return theoModelDescriptor; }

    public java.util.List<ToteRaceEvent> getTriggerRaceEvents() { return triggerRaceEvents.get(); }
    public SimpleListProperty<ToteRaceEvent> triggerRaceEventsProperty() { return triggerRaceEvents; }
}
