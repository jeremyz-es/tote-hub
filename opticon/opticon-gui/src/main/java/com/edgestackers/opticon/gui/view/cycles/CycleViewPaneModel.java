package com.edgestackers.opticon.gui.view.cycles;

import com.edgestackers.core.datamodel.tote.ToteBetType;
import com.edgestackers.opticon.common.datamodel.OpticonMarketCycleUpdate;
import com.edgestackers.opticon.gui.datamodel.OpticonRaceSwitchKey;
import com.edgestackers.opticon.gui.datamodel.cycles.OpticonMarketCycleUpdateEntry;
import com.edgestackers.opticon.gui.datamodel.filter.ExoticInstrumentFilter;
import com.edgestackers.core.datamodel.tote.TotePoolJurisdiction;
import jakarta.annotation.Nullable;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.Comparator;

import static com.edgestackers.opticon.gui.datamodel.cycles.OpticonMarketCycleUpdateEntryConverter.convertOpticonMarketCycleUpdateToEntry;

public class CycleViewPaneModel {
    private final ObservableList<OpticonMarketCycleUpdateEntry> updates = FXCollections.observableArrayList();
    private final FilteredList<OpticonMarketCycleUpdateEntry> filteredUpdates = new FilteredList<>(updates, update -> false);
    private final SortedList<OpticonMarketCycleUpdateEntry> sortedUpdates = new SortedList<>(filteredUpdates);
    private final SimpleObjectProperty<OpticonRaceSwitchKey> selectedRace = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<ExoticInstrumentFilter> selectedFilter = new SimpleObjectProperty<>();

    public void initialize() {
        sortedUpdates.setComparator(Comparator.comparing(OpticonMarketCycleUpdateEntry::getReceivedAtQldTime).reversed());
    }

    public void handle(OpticonMarketCycleUpdate update) {
        OpticonMarketCycleUpdateEntry entry = convertOpticonMarketCycleUpdateToEntry(update);
        Platform.runLater(() -> updates.add(entry));
    }

    public void switchTo(OpticonRaceSwitchKey opticonRaceSwitchKey) {
        selectedRace.set(opticonRaceSwitchKey);
        updateFilterPredicate();
    }

    public void switchTo(ExoticInstrumentFilter filter) {
        selectedFilter.set(filter);
        updateFilterPredicate();
    }

    private void updateFilterPredicate() {
        @Nullable ExoticInstrumentFilter filter = selectedFilter.get();
        @Nullable ToteBetType toteBetType = filter == null ? null : filter.toteBetType();
        @Nullable TotePoolJurisdiction jurisdiction = filter == null ? null : filter.totePoolJurisdiction();
        @Nullable OpticonRaceSwitchKey race = selectedRace.get();
        if (race == null) {
            Platform.runLater(() -> filteredUpdates.setPredicate(entry -> false));
            return;
        }
        Platform.runLater(() -> filteredUpdates.setPredicate(entry ->
                entry.getToteBetType().equals(toteBetType)
                && entry.getJurisdiction().equals(jurisdiction)
                && entry.getTrack().equals(race.track())
                && entry.getRace() == race.race()
                && entry.getRaceType() == race.raceType()
        ));
    }

    public SortedList<OpticonMarketCycleUpdateEntry> getSortedUpdates() { return sortedUpdates; }
}
