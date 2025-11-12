package com.edgestackers.opticon.common.datamodel;

import com.edgestackers.opticon.common.message.OpticonMessage;
import com.edgestackers.tote.hub.core.datamodel.context.*;
import com.edgestackers.tote.hub.core.datamodel.core.*;
import com.edgestackers.tote.hub.core.datamodel.turbo.*;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OpticonStrategyContextSummary implements OpticonMessage {
    private final long raceStartTimeEpochNanos;
    private final String strategyId;
    private final String strategyName;
    private final RaceType raceType;
    private final String esRaceId;
    private final String track;
    private final int race;
    private final TurboExoticDividendModel dividendModel;
    private final int dividendModelMajorVersion;
    private final int dividendModelMinorVersion;
    private final TurboExoticTheoModel theoModel;
    private final int theoModelMajorVersion;
    private final int theoModelMinorVersion;
    private final ToteBetType toteBetType;
    private final TotePoolJurisdiction totePoolJurisdiction;
    private ToteRaceEvent raceStatus;
    private ToteMarketCycleStatus cyclesStatus;
    private TurboPricingStatus turboDividendStatus;
    private TurboPricingStatus turboTheoStatus;
    private NitroContextStatus nitroStatus;
    private OrderGatewayExecutionStatus executionStatus;
    private String winningSelection;
    private double payout;
    private double turnover;
    private double profit;
    private long lastUpdatedAtEpochNanos;

    public OpticonStrategyContextSummary(@JsonProperty("lastUpdatedAtEpochNanos") long updatedAtEpochNanos,
                                         @JsonProperty("raceStartTimeEpochNanos") long raceStartTimeEpochNanos,
                                         @JsonProperty("strategyId") String strategyId,
                                         @JsonProperty("strategyName") String strategyName,
                                         @JsonProperty("raceType") RaceType raceType,
                                         @JsonProperty("esRaceId") String esRaceId,
                                         @JsonProperty("track") String track,
                                         @JsonProperty("race") int race,
                                         @JsonProperty("dividendModel") TurboExoticDividendModel dividendModel,
                                         @JsonProperty("dividendModelMajorVersion") int dividendModelMajorVersion,
                                         @JsonProperty("dividendModelMinorVersion") int dividendModelMinorVersion,
                                         @JsonProperty("theoModel") TurboExoticTheoModel theoModel,
                                         @JsonProperty("theoModelMajorVersion") int theoModelMajorVersion,
                                         @JsonProperty("theoModelMinorVersion") int theoModelMinorVersion,
                                         @JsonProperty("toteBetType") ToteBetType toteBetType,
                                         @JsonProperty("totePoolJurisdiction") TotePoolJurisdiction totePoolJurisdiction,
                                         @JsonProperty("raceStatus") ToteRaceEvent raceStatus,
                                         @JsonProperty("cyclesStatus") ToteMarketCycleStatus cyclesStatus,
                                         @JsonProperty("turboDividendStatus") TurboPricingStatus turboDividendStatus,
                                         @JsonProperty("turboTheoStatus") TurboPricingStatus turboTheoStatus,
                                         @JsonProperty("nitroStatus") NitroContextStatus nitroStatus,
                                         @JsonProperty("executionStatus") OrderGatewayExecutionStatus executionStatus,
                                         @JsonProperty("winningSelection") String winningSelection,
                                         @JsonProperty("payout") double payout,
                                         @JsonProperty("turnover") double turnover,
                                         @JsonProperty("profit") double profit)
    {
        this.lastUpdatedAtEpochNanos = updatedAtEpochNanos;
        this.raceStartTimeEpochNanos = raceStartTimeEpochNanos;
        this.strategyId = strategyId;
        this.strategyName = strategyName;
        this.raceType = raceType;
        this.esRaceId = esRaceId;
        this.track = track;
        this.race = race;
        this.dividendModel = dividendModel;
        this.dividendModelMajorVersion = dividendModelMajorVersion;
        this.dividendModelMinorVersion = dividendModelMinorVersion;
        this.theoModel = theoModel;
        this.theoModelMajorVersion = theoModelMajorVersion;
        this.theoModelMinorVersion = theoModelMinorVersion;
        this.toteBetType = toteBetType;
        this.totePoolJurisdiction = totePoolJurisdiction;
        this.raceStatus = raceStatus;
        this.cyclesStatus = cyclesStatus;
        this.turboDividendStatus = turboDividendStatus;
        this.turboTheoStatus = turboTheoStatus;
        this.nitroStatus = nitroStatus;
        this.executionStatus = executionStatus;
        this.winningSelection = winningSelection;
        this.payout = payout;
        this.turnover = turnover;
        this.profit = profit;
    }

    public long getLastUpdatedAtEpochNanos() { return lastUpdatedAtEpochNanos; }
    public long getRaceStartTimeEpochNanos() { return raceStartTimeEpochNanos; }
    public String getStrategyId() { return strategyId; }
    public String getStrategyName() { return strategyName; }
    public RaceType getRaceType() { return raceType; }
    public String getEsRaceId() { return esRaceId; }
    public String getTrack() { return track; }
    public int getRace() { return race; }
    public TurboExoticDividendModel getDividendModel() { return dividendModel; }
    public int getDividendModelMajorVersion() { return dividendModelMajorVersion; }
    public int getDividendModelMinorVersion() { return dividendModelMinorVersion; }
    public TurboExoticTheoModel getTheoModel() { return theoModel; }
    public int getTheoModelMajorVersion() { return theoModelMajorVersion; }
    public int getTheoModelMinorVersion() { return theoModelMinorVersion; }
    public ToteBetType getToteBetType() { return toteBetType; }
    public TotePoolJurisdiction getTotePoolJurisdiction() { return totePoolJurisdiction; }
    public ToteRaceEvent getRaceStatus() { return raceStatus; }
    public ToteMarketCycleStatus getCyclesStatus() { return cyclesStatus; }
    public TurboPricingStatus getTurboDividendStatus() { return turboDividendStatus; }
    public TurboPricingStatus getTurboTheoStatus() { return turboTheoStatus; }
    public NitroContextStatus getNitroStatus() { return nitroStatus; }
    public OrderGatewayExecutionStatus getExecutionStatus() { return executionStatus; }
    public String getWinningSelection() { return winningSelection; }
    public double getPayout() { return payout; }
    public double getTurnover() { return turnover; }
    public double getProfit() { return profit; }

    public void setRaceStatus(ToteRaceEvent raceStatus) { this.raceStatus = raceStatus; }
    public void setCyclesStatus(ToteMarketCycleStatus cyclesStatus) { this.cyclesStatus = cyclesStatus; }
    public void setTurboDividendStatus(TurboPricingStatus turboDividendStatus) { this.turboDividendStatus = turboDividendStatus; }
    public void setTurboTheoStatus(TurboPricingStatus turboTheoStatus) { this.turboTheoStatus = turboTheoStatus; }
    public void setNitroStatus(NitroContextStatus nitroStatus) { this.nitroStatus = nitroStatus; }
    public void setExecutionStatus(OrderGatewayExecutionStatus executionStatus) { this.executionStatus = executionStatus; }
    public void setWinningSelection(String winningSelection) { this.winningSelection = winningSelection; }
    public void setPayout(double payout) { this.payout = payout; }
    public void setTurnover(double turnover) { this.turnover = turnover; }
    public void setProfit(double profit) { this.profit = profit; }
    public void setLastUpdatedAtEpochNanos(long updatedAtEpochNanos) { this.lastUpdatedAtEpochNanos = updatedAtEpochNanos; }
}
