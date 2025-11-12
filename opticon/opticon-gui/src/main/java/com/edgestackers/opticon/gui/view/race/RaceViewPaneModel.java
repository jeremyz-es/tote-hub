package com.edgestackers.opticon.gui.view.race;

import com.edgestackers.opticon.common.datamodel.OpticonRunSummary;
import com.edgestackers.opticon.common.datamodel.OpticonWinPoolUpdate;
import com.edgestackers.opticon.gui.datamodel.OpticonRaceSwitchKey;
import com.edgestackers.opticon.gui.datamodel.OpticonRaceSwitchType;
import com.edgestackers.opticon.gui.datamodel.filter.RaceViewFilter;
import com.edgestackers.opticon.gui.datamodel.run.OpticonRunSummaryEntry;
import com.edgestackers.tote.hub.core.datamodel.core.*;
import com.edgestackers.tote.hub.core.metadata.EsRaceMetadata;
import jakarta.annotation.Nullable;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.*;
import java.util.stream.Collectors;

import static com.edgestackers.opticon.gui.control.merger.OpticonRunSummaryEntryMerger.merge;
import static com.edgestackers.opticon.gui.datamodel.run.OpticonRunSummaryEntryConverter.convertOpticonRunSummaryToEntry;
import static com.edgestackers.opticon.gui.view.util.CalculationUtil.timeUntilRace;
import static com.edgestackers.tote.hub.core.datamodel.core.RaceCode.convertToRaceType;
import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;

public class RaceViewPaneModel {
    private final ObservableList<OpticonRunSummaryEntry> runSummaryEntries = FXCollections.observableArrayList();
    private final FilteredList<OpticonRunSummaryEntry> filteredEntries = new FilteredList<>(runSummaryEntries, entry -> false);
    private final SortedList<OpticonRunSummaryEntry> sortedEntries = new SortedList<>(filteredEntries);
    private final Map<String, OpticonRunSummaryEntry> entriesByEsRunId = new HashMap<>();
    private final SimpleStringProperty timeUntilRaceDescription = new SimpleStringProperty();
    private final TreeMap<Long, OpticonRaceSwitchKey> raceStartTimes = new TreeMap<>();
    private final Map<OpticonRaceSwitchKey, Long> racesToStartTimes = new HashMap<>();
    private final ObservableList<RaceType> selectableRaceTypes = FXCollections.observableArrayList();
    private final ObservableList<String> selectableTracks = FXCollections.observableArrayList();
    private final ObservableList<Integer> selectableRaces = FXCollections.observableArrayList();
    private final Map<OpticonRaceSwitchKey, EsRaceMetadata> availableRaces = new HashMap<>();
    private final SimpleObjectProperty<OpticonRaceSwitchKey> selectedRace = new SimpleObjectProperty<>();
    private final Map<TotePoolJurisdiction, Map<OpticonRaceSwitchKey, SimpleStringProperty>> poolSizeHeaders = new HashMap<>();
    private final SimpleStringProperty nswWinPoolHeaderDesc = new SimpleStringProperty("NSW $0");
    private final SimpleStringProperty vicWinPoolHeaderDesc = new SimpleStringProperty("VIC $0");
    private final SimpleStringProperty qldWinPoolHeaderDesc = new SimpleStringProperty("QLD $0");

    public RaceViewPaneModel() {}

    public void initialize() {
    }

    public void switchToRace(RaceViewFilter raceViewFilter) {
        Platform.runLater(() -> {
            selectedRace.set(new OpticonRaceSwitchKey(raceViewFilter.raceType(), raceViewFilter.track(), raceViewFilter.race()));
            filteredEntries.setPredicate(entry -> entry.getRaceType() == raceViewFilter.raceType() && entry.getTrack().equals(raceViewFilter.track()) && entry.getRace() == raceViewFilter.race());
            updateDisplayedHeadersFor(new OpticonRaceSwitchKey(raceViewFilter.raceType(), raceViewFilter.track(), raceViewFilter.race()));
            updateTimeUntilRace();
        });
    }

    public void handle(EsRaceMetadata esRaceMetadata) {
        if (esRaceMetadata.raceCode() != RaceCode.GALLOP || esRaceMetadata.abandoned()) return;
        Platform.runLater(() -> {
            RaceType raceType = convertToRaceType(esRaceMetadata.raceCode());
            String track = esRaceMetadata.track();
            int race = esRaceMetadata.race();
            OpticonRaceSwitchKey opticonRaceSwitchKey = new OpticonRaceSwitchKey(raceType, track, race);
            availableRaces.put(opticonRaceSwitchKey, esRaceMetadata);
            raceStartTimes.put(esRaceMetadata.raceStartTimeUtcNanos(), opticonRaceSwitchKey);
            racesToStartTimes.put(opticonRaceSwitchKey, esRaceMetadata.raceStartTimeUtcNanos());
            if (!selectableRaceTypes.contains(raceType)) selectableRaceTypes.add(raceType);
            if (!selectableTracks.contains(track)) selectableTracks.add(track);
            if (!selectableRaces.contains(race)) selectableRaces.add(race);
        });
    }

    public void handle(OpticonRunSummary opticonRunSummary) {
        OpticonRunSummaryEntry newEntry = convertOpticonRunSummaryToEntry(opticonRunSummary);
        String esRunId = newEntry.getEsRunId();
        if (entriesByEsRunId.containsKey(esRunId)) {
            OpticonRunSummaryEntry oldEntry = entriesByEsRunId.get(esRunId);
            Platform.runLater(() -> merge(oldEntry, newEntry));
            return;
        }
        entriesByEsRunId.put(esRunId, newEntry);
        Platform.runLater(() -> runSummaryEntries.add(newEntry));
    }

    public void handle(OpticonWinPoolUpdate update) {
        Platform.runLater(() -> {
            OpticonRaceSwitchKey key = new OpticonRaceSwitchKey(update.raceType(), update.track(), update.race());
            TotePoolJurisdiction jurisdiction = update.totePoolJurisdiction();
            String value = String.format("%s $%.0f", jurisdiction.name(), update.winPoolSize());
            poolSizeHeaders
                    .computeIfAbsent(jurisdiction, __ -> new HashMap<>())
                    .computeIfAbsent(key, __ -> new SimpleStringProperty())
                    .set(value);
            if (key.equals(selectedRace.get())) updateDisplayedHeadersFor(key);
        });
    }

    private void updateDisplayedHeadersFor(OpticonRaceSwitchKey key) {
        nswWinPoolHeaderDesc.set(getOrDefault(poolSizeHeaders, TotePoolJurisdiction.NSW, key, "NSW $0"));
        vicWinPoolHeaderDesc.set(getOrDefault(poolSizeHeaders, TotePoolJurisdiction.VIC, key, "VIC $0"));
        qldWinPoolHeaderDesc.set(getOrDefault(poolSizeHeaders, TotePoolJurisdiction.QLD, key, "QLD $0"));
    }

    private String getOrDefault(Map<TotePoolJurisdiction, Map<OpticonRaceSwitchKey, SimpleStringProperty>> map,
                                TotePoolJurisdiction jurisdiction,
                                OpticonRaceSwitchKey key,
                                String fallback) {
        return map.getOrDefault(jurisdiction, Map.of()).getOrDefault(key, new SimpleStringProperty(fallback)).get();
    }

    public void clearFilter() {
        Platform.runLater(() -> filteredEntries.setPredicate(entry -> false));
    }

    public void updateTimeUntilRace() {
        Platform.runLater(() ->
                timeUntilRaceDescription.set(selectedRace.get() == null ? "" : timeUntilRace(availableRaces.get(selectedRace.get()).raceStartTimeUtcNanos()))
        );
    }

    public void setTracksFilter(RaceType raceType) {
        Set<String> newSelectableTracks = availableRaces.values().stream()
                .filter(availableRace -> convertToRaceType(availableRace.raceCode()).equals(raceType))
                .map(EsRaceMetadata::track)
                .collect(Collectors.toSet());
        Platform.runLater(() -> {
            selectableTracks.clear();
            selectableTracks.addAll(newSelectableTracks);
        });
    }

    public void setRacesFilter(RaceType raceType, String selectedTrack) {
        Set<Integer> newSelectableRaces = availableRaces.values()
                .stream()
                .filter(race -> race.track().equals(selectedTrack))
                .filter(race -> convertToRaceType(race.raceCode()).equals(raceType))
                .map(EsRaceMetadata::race)
                .collect(Collectors.toSet());
        Platform.runLater(() -> {
            selectableRaces.clear();
            selectableRaces.addAll(newSelectableRaces);
        });
    }

    @Nullable
    public OpticonRaceSwitchKey executeRaceSwitch(OpticonRaceSwitchType switchType) {
        if (switchType == OpticonRaceSwitchType.PLAY) {
            long currentTimeUtcNanos = generateEpochNanosTimestamp();
            @Nullable Map.Entry<Long, OpticonRaceSwitchKey> nextEntry = raceStartTimes.higherEntry(currentTimeUtcNanos);
            @Nullable OpticonRaceSwitchKey key = nextEntry == null ? raceStartTimes.lastEntry().getValue() : nextEntry.getValue();
            return key;
        }
        if (selectedRace.get() == null) return null;
        long startTimeOfSelectedRace = racesToStartTimes.get(selectedRace.get());
        @Nullable Map.Entry<Long, OpticonRaceSwitchKey> newRace = switchType == OpticonRaceSwitchType.PREVIOUS
                ? raceStartTimes.lowerEntry(startTimeOfSelectedRace)
                : raceStartTimes.higherEntry(startTimeOfSelectedRace);
        return newRace == null ? null : newRace.getValue();
    }

    public SortedList<OpticonRunSummaryEntry> getSortedEntries() { return sortedEntries; }

    public ObservableList<RaceType> getSelectableRaceTypes() { return selectableRaceTypes; }

    public ObservableList<String> getSelectableTracks() { return selectableTracks; }

    public ObservableList<Integer> getSelectableRaces() { return selectableRaces; }

    public SimpleStringProperty timeUntilRaceDescriptionProperty() { return timeUntilRaceDescription; }

    public SimpleObjectProperty<OpticonRaceSwitchKey> selectedRaceProperty() { return selectedRace; }

    public SimpleStringProperty nswWinPoolHeaderDescProperty() { return nswWinPoolHeaderDesc; }

    public SimpleStringProperty vicWinPoolHeaderDescProperty() { return vicWinPoolHeaderDesc; }

    public SimpleStringProperty qldWinPoolHeaderDescProperty() { return qldWinPoolHeaderDesc; }
}
