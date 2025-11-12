package com.edgestackers.opticon.gui.view.operations;

import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummary;
import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummaryKey;
import com.edgestackers.opticon.gui.datamodel.OpticonRaceSwitchKey;
import com.edgestackers.opticon.gui.datamodel.operations.OpticonStrategyContextSummaryEntry;
import com.edgestackers.tote.hub.core.datamodel.core.*;
import jakarta.annotation.Nullable;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.*;

import static com.edgestackers.opticon.gui.datamodel.operations.OpticonStrategyContextSummaryEntryConverter.convertOpticonExoticSummaryToEntry;
import static com.edgestackers.opticon.gui.view.operations.OperationsViewModelUtil.updateEntry;

public class OperationsViewModel {
    private final Map<OpticonStrategyContextSummaryKey, OpticonStrategyContextSummaryEntry> cache = new HashMap<>();
    private final ObservableList<OpticonStrategyContextSummaryEntry> operationalUpdateEntries = FXCollections.observableArrayList();
    private final FilteredList<OpticonStrategyContextSummaryEntry> filteredUpdateEntries = new FilteredList<>(operationalUpdateEntries);
    private final SortedList<OpticonStrategyContextSummaryEntry> sortedUpdateEntries = new SortedList<>(filteredUpdateEntries);
    private final SimpleBooleanProperty linkRaceFilterProperty = new SimpleBooleanProperty();
    private final SimpleObjectProperty<OpticonRaceSwitchKey> selectedRaceKey = new SimpleObjectProperty<>();
    private final ObservableList<ToteBetType> selectedBetTypes = FXCollections.observableArrayList();
    private final ObservableList<TotePoolJurisdiction> selectedJurisdictions = FXCollections.observableArrayList();

    public void initialize() {
        sortedUpdateEntries.setComparator(
                Comparator
                        .comparing(OpticonStrategyContextSummaryEntry::getRaceStartTimeEpochNanos).reversed()
                        .thenComparing(e -> e.getBetType().ordinal())
                        .thenComparing(e -> e.getJurisdiction().ordinal())
        );
        selectedBetTypes.addListener((ListChangeListener<ToteBetType>) c -> updateFilterPredicate());
        selectedJurisdictions.addListener((ListChangeListener<TotePoolJurisdiction>) c -> updateFilterPredicate());
        selectedRaceKey.addListener((obs, oldVal, newVal) -> updateFilterPredicate());
        linkRaceFilterProperty.addListener((obs, oldVal, newVal) -> updateFilterPredicate());
        Platform.runLater(() -> linkRaceFilterProperty.set(true));
    }

    public void handle(OpticonRaceSwitchKey key) {
        selectedRaceKey.set(key);
    }

    public void process(OpticonStrategyContextSummary summary) {
        OpticonStrategyContextSummaryKey key = new OpticonStrategyContextSummaryKey(summary.getStrategyId(), summary.getEsRaceId(), summary.getToteBetType(), summary.getTotePoolJurisdiction());
        if (!cache.containsKey(key)) {
            OpticonStrategyContextSummaryEntry entry = convertOpticonExoticSummaryToEntry(summary);
            cache.put(key, entry);
            Platform.runLater(() -> operationalUpdateEntries.add(entry));
        }
        else {
            Platform.runLater(() -> updateEntry(cache.get(key), summary));
        }
    }

    void register(ObservableList<ToteBetType> checkedBetTypes, ObservableList<TotePoolJurisdiction> checkedJurisdictions) {
        checkedBetTypes.addListener((ListChangeListener<ToteBetType>) c -> Platform.runLater(() -> { selectedBetTypes.clear(); selectedBetTypes.addAll(checkedBetTypes); }));
        checkedJurisdictions.addListener((ListChangeListener<TotePoolJurisdiction>) c -> { selectedJurisdictions.clear(); selectedJurisdictions.addAll(checkedJurisdictions); });
    }

    private void updateFilterPredicate() {
        @Nullable String selectedTrack = selectedRaceKey.get() == null ? null : selectedRaceKey.get().track();
        @Nullable Integer selectedRace = selectedRaceKey.get() == null ? null : selectedRaceKey.get().race();
        @Nullable RaceType selectedRaceType = selectedRaceKey.get() == null ? null : selectedRaceKey.get().raceType();
        Platform.runLater(() -> filteredUpdateEntries.setPredicate(entry -> selectedBetTypes.contains(entry.getBetType())
                && selectedJurisdictions.contains(entry.getJurisdiction())
                && (!linkRaceFilterProperty.get() || (entry.getRaceType() == selectedRaceType && entry.getTrack().equals(selectedTrack) && Objects.equals(selectedRace, entry.getRace()))))
        );
    }

    public SimpleBooleanProperty linkRaceFilterPropertyProperty() { return linkRaceFilterProperty; }

    public SortedList<OpticonStrategyContextSummaryEntry> getSortedUpdateEntries() { return sortedUpdateEntries; }
}
