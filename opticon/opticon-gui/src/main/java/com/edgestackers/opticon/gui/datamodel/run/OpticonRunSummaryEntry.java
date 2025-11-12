package com.edgestackers.opticon.gui.datamodel.run;

import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;
import java.util.Map;

public class OpticonRunSummaryEntry {
    private final SimpleStringProperty lastUpdatedAtQldTime;
    private final SimpleStringProperty lastUpdatedAtSydTime;
    private final RaceType raceType;
    private final String date;
    private final String esRaceId;
    private final String esRunnerId;
    private final String esRunId;
    private final String track;
    private final int race;
    private final int tab;
    private final String runnerName;
    private final SimpleStringProperty nswDesc;
    private final SimpleStringProperty qldDesc;
    private final SimpleStringProperty vicDesc;
    private final Map<String, SimpleStringProperty> theoColumns;

    public OpticonRunSummaryEntry(RaceType raceType,
                                  String date,
                                  String esRaceId,
                                  String esRunnerId,
                                  String esRunId,
                                  String track,
                                  int race,
                                  int tab,
                                  String runnerName,
                                  String lastUpdatedAtQldTime,
                                  String lastUpdatedAtSydTime,
                                  String nswDesc,
                                  String qldDesc,
                                  String vicDesc)
    {
        this.raceType = raceType;
        this.date = date;
        this.esRaceId = esRaceId;
        this.esRunnerId = esRunnerId;
        this.esRunId = esRunId;
        this.track = track;
        this.race = race;
        this.tab = tab;
        this.runnerName = runnerName;
        this.lastUpdatedAtQldTime = new SimpleStringProperty(lastUpdatedAtQldTime);
        this.lastUpdatedAtSydTime = new SimpleStringProperty(lastUpdatedAtSydTime);
        this.nswDesc = new SimpleStringProperty(nswDesc);
        this.qldDesc = new SimpleStringProperty(qldDesc);
        this.vicDesc = new SimpleStringProperty(vicDesc);
        this.theoColumns = new HashMap<>();
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public String getDate() {
        return date;
    }

    public String getEsRaceId() {
        return esRaceId;
    }

    public String getEsRunnerId() {
        return esRunnerId;
    }

    public String getEsRunId() {
        return esRunId;
    }

    public String getTrack() {
        return track;
    }

    public int getRace() {
        return race;
    }

    public int getTab() {
        return tab;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public String getLastUpdatedAtQldTime() {
        return lastUpdatedAtQldTime.get();
    }

    public SimpleStringProperty lastUpdatedAtQldTimeProperty() {
        return lastUpdatedAtQldTime;
    }

    public String getLastUpdatedAtSydTime() {
        return lastUpdatedAtSydTime.get();
    }

    public SimpleStringProperty lastUpdatedAtSydTimeProperty() {
        return lastUpdatedAtSydTime;
    }

    public void setLastUpdatedAtQldTime(String lastUpdatedAtQldTime) {
        this.lastUpdatedAtQldTime.set(lastUpdatedAtQldTime);
    }

    public void setLastUpdatedAtSydTime(String lastUpdatedAtSydTime) {
        this.lastUpdatedAtSydTime.set(lastUpdatedAtSydTime);
    }

    public String getNswDesc() {
        return nswDesc.get();
    }

    public SimpleStringProperty nswDescProperty() {
        return nswDesc;
    }

    public String getQldDesc() {
        return qldDesc.get();
    }

    public SimpleStringProperty qldDescProperty() {
        return qldDesc;
    }

    public String getVicDesc() {
        return vicDesc.get();
    }

    public SimpleStringProperty vicDescProperty() {
        return vicDesc;
    }

    public void setNswDesc(String nswDesc) {
        this.nswDesc.set(nswDesc);
    }

    public void setQldDesc(String qldDesc) {
        this.qldDesc.set(qldDesc);
    }

    public void setVicDesc(String vicDesc) {
        this.vicDesc.set(vicDesc);
    }

    public String getTheo(String key) {
        SimpleStringProperty property = theoColumns.get(key);
        return property != null ? property.get() : null;
    }

    public SimpleStringProperty getTheoProperty(String key) {
        return theoColumns.computeIfAbsent(key, k -> new SimpleStringProperty(""));
    }

    public void setTheo(String key, String value) {
        getTheoProperty(key).set(value != null ? value : "");
    }

    public Map<String, SimpleStringProperty> getTheoColumns() {
        return theoColumns;
    }
}
