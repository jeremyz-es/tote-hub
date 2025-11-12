package com.edgestackers.opticon.gui.datamodel.operations;

import com.edgestackers.tote.hub.core.datamodel.context.NitroContextStatus;
import com.edgestackers.tote.hub.core.datamodel.context.OrderGatewayExecutionStatus;
import com.edgestackers.tote.hub.core.datamodel.context.ToteMarketCycleStatus;
import com.edgestackers.tote.hub.core.datamodel.context.TurboPricingStatus;
import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteRaceEvent;
import com.edgestackers.tote.hub.core.datamodel.turbo.TurboExoticDividendModel;
import com.edgestackers.tote.hub.core.datamodel.turbo.TurboExoticTheoModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class OpticonStrategyContextSummaryEntry {
    private final long raceStartTimeEpochNanos;
    private final String qldTime;
    private final String strategyId;
    private final String strategyName;
    private final RaceType raceType;
    private final String esRaceId;
    private final String track;
    private final int race;
    private final TurboExoticDividendModel dividendModel;
    private final TurboExoticTheoModel theoModel;
    private ToteRaceEvent raceStatus;
    private final ToteBetType betType;
    private final TotePoolJurisdiction jurisdiction;

    private final SimpleObjectProperty<ToteMarketCycleStatus> cyclesStatus;
    private final SimpleObjectProperty<TurboPricingStatus> turboDividendStatus;
    private final SimpleObjectProperty<TurboPricingStatus> turboTheoStatus;
    private final SimpleObjectProperty<NitroContextStatus> nitroStatus;
    private final SimpleObjectProperty<OrderGatewayExecutionStatus> executionStatus;
    private final SimpleDoubleProperty turnover;
    private final SimpleStringProperty winningSelection;
    private final SimpleDoubleProperty payout;
    private final SimpleDoubleProperty profit;
    private final SimpleLongProperty epochNanosTimestamp;

    public OpticonStrategyContextSummaryEntry(long raceStartTimeEpochNanos,
                                              long epochNanosTimestamp,
                                              String qldTime,
                                              String strategyId,
                                              String strategyName,
                                              TurboExoticDividendModel dividendModel,
                                              TurboExoticTheoModel theoModel,
                                              RaceType raceType,
                                              ToteBetType betType,
                                              TotePoolJurisdiction jurisdiction,
                                              String esRaceId,
                                              String track,
                                              int race,
                                              ToteRaceEvent raceStatus,
                                              ToteMarketCycleStatus cyclesStatus,
                                              TurboPricingStatus turboDividendStatus,
                                              TurboPricingStatus turboTheoStatus,
                                              NitroContextStatus nitroStatus,
                                              OrderGatewayExecutionStatus executionStatus,
                                              String winningSelection,
                                              double payout,
                                              double turnover,
                                              double profit)
    {
        this.raceStartTimeEpochNanos = raceStartTimeEpochNanos;
        this.epochNanosTimestamp = new SimpleLongProperty(epochNanosTimestamp);
        this.qldTime = qldTime;
        this.strategyId = strategyId;
        this.strategyName = strategyName;
        this.dividendModel = dividendModel;
        this.theoModel = theoModel;
        this.raceType = raceType;
        this.betType = betType;
        this.jurisdiction = jurisdiction;
        this.esRaceId = esRaceId;
        this.track = track;
        this.race = race;
        this.raceStatus = raceStatus;
        this.cyclesStatus = new SimpleObjectProperty<>(cyclesStatus);
        this.turboDividendStatus = new SimpleObjectProperty<>(turboDividendStatus);
        this.turboTheoStatus = new SimpleObjectProperty<>(turboTheoStatus);
        this.nitroStatus = new SimpleObjectProperty<>(nitroStatus);
        this.executionStatus = new SimpleObjectProperty<>(executionStatus);
        this.winningSelection = new SimpleStringProperty(winningSelection);
        this.payout = new SimpleDoubleProperty(payout);
        this.turnover = new SimpleDoubleProperty(turnover);
        this.profit = new SimpleDoubleProperty(profit);
    }

    public long getRaceStartTimeEpochNanos() {
        return raceStartTimeEpochNanos;
    }

    public String getQldTime() {
        return qldTime;
    }

    public String getStrategyId() {
        return strategyId;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public TurboExoticDividendModel getDividendModel() {
        return dividendModel;
    }

    public TurboExoticTheoModel getTheoModel() {
        return theoModel;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public String getEsRaceId() {
        return esRaceId;
    }

    public String getTrack() {
        return track;
    }

    public int getRace() {
        return race;
    }

    public ToteRaceEvent getRaceStatus() {
        return raceStatus;
    }

    public void setRaceStatus(ToteRaceEvent raceStatus) {
        this.raceStatus = raceStatus;
    }

    public ToteBetType getBetType() {
        return betType;
    }

    public TotePoolJurisdiction getJurisdiction() {
        return jurisdiction;
    }

    public ToteMarketCycleStatus getCyclesStatus() {
        return cyclesStatus.get();
    }

    public SimpleObjectProperty<ToteMarketCycleStatus> cyclesStatusProperty() {
        return cyclesStatus;
    }

    public void setCyclesStatus(ToteMarketCycleStatus cyclesStatus) {
        this.cyclesStatus.set(cyclesStatus);
    }

    public TurboPricingStatus getTurboDividendStatus() {
        return turboDividendStatus.get();
    }

    public SimpleObjectProperty<TurboPricingStatus> turboDividendStatusProperty() {
        return turboDividendStatus;
    }

    public void setTurboDividendStatus(TurboPricingStatus turboDividendStatus) {
        this.turboDividendStatus.set(turboDividendStatus);
    }

    public TurboPricingStatus getTurboTheoStatus() {
        return turboTheoStatus.get();
    }

    public SimpleObjectProperty<TurboPricingStatus> turboTheoStatusProperty() {
        return turboTheoStatus;
    }

    public void setTurboTheoStatus(TurboPricingStatus turboTheoStatus) {
        this.turboTheoStatus.set(turboTheoStatus);
    }

    public NitroContextStatus getNitroStatus() {
        return nitroStatus.get();
    }

    public SimpleObjectProperty<NitroContextStatus> nitroStatusProperty() {
        return nitroStatus;
    }

    public void setNitroStatus(NitroContextStatus nitroStatus) {
        this.nitroStatus.set(nitroStatus);
    }

    public OrderGatewayExecutionStatus getExecutionStatus() {
        return executionStatus.get();
    }

    public SimpleObjectProperty<OrderGatewayExecutionStatus> executionStatusProperty() {
        return executionStatus;
    }

    public void setExecutionStatus(OrderGatewayExecutionStatus executionStatus) {
        this.executionStatus.set(executionStatus);
    }

    public double getTurnover() {
        return turnover.get();
    }

    public SimpleDoubleProperty turnoverProperty() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover.set(turnover);
    }

    public String getWinningSelection() {
        return winningSelection.get();
    }

    public SimpleStringProperty winningSelectionProperty() {
        return winningSelection;
    }

    public void setWinningSelection(String winningSelection) {
        this.winningSelection.set(winningSelection);
    }

    public double getPayout() {
        return payout.get();
    }

    public SimpleDoubleProperty payoutProperty() {
        return payout;
    }

    public void setPayout(double payout) {
        this.payout.set(payout);
    }

    public double getProfit() {
        return profit.get();
    }

    public SimpleDoubleProperty profitProperty() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit.set(profit);
    }

    public long getEpochNanosTimestamp() {
        return epochNanosTimestamp.get();
    }

    public SimpleLongProperty epochNanosTimestampProperty() {
        return epochNanosTimestamp;
    }

    public void setEpochNanosTimestamp(long epochNanosTimestamp) {
        this.epochNanosTimestamp.set(epochNanosTimestamp);
    }
}
