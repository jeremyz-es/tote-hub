package com.edgestackers.opticon.gui.view.pacman;

import com.edgestackers.opticon.common.datamodel.PacmanRaceFlucsUpdate;
import com.edgestackers.opticon.gui.datamodel.OpticonRaceSwitchKey;
import com.edgestackers.opticon.gui.datamodel.pacman.PacmanRaceFlucsEntry;
import com.edgestackers.opticon.gui.datamodel.pacman.PacmanRunFlucsEntry;
import jakarta.annotation.Nullable;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.Comparator;

import static com.edgestackers.opticon.gui.datamodel.pacman.PacmanRaceFlucsEntryConverter.convertPacmanRaceFlucsUpdateToEntry;

public class PacmanFlucsViewPaneModel {
    private final ObservableList<PacmanRaceFlucsEntry> pacmanRaceFlucs = FXCollections.observableArrayList();
    private final FilteredList<PacmanRaceFlucsEntry> filteredPacmanRaceFlucs = new FilteredList<>(pacmanRaceFlucs);
    private final SortedList<PacmanRaceFlucsEntry> sortedPacmanRaceFlucs = new SortedList<>(filteredPacmanRaceFlucs);
    private final ObservableList<PacmanRunFlucsEntry> pacmanRunFlucs = FXCollections.observableArrayList();
    private final FilteredList<PacmanRunFlucsEntry> filteredPacmanRunFlucs = new FilteredList<>(pacmanRunFlucs);
    private final SortedList<PacmanRunFlucsEntry> sortedPacmanRunFlucs = new SortedList<>(filteredPacmanRunFlucs);
    private final SimpleObjectProperty<OpticonRaceSwitchKey> selectedRace = new SimpleObjectProperty<>();

    public void initialize() {
        sortedPacmanRaceFlucs.setComparator(Comparator.comparing(PacmanRaceFlucsEntry::getEpochNanosTimestamp).reversed());
        sortedPacmanRunFlucs.setComparator(Comparator.comparing(PacmanRunFlucsEntry::getTab));
        Platform.runLater(() -> filteredPacmanRaceFlucs.setPredicate(entry -> false));
    }

    public void switchTo(OpticonRaceSwitchKey opticonRaceSwitchKey) {
        selectedRace.set(opticonRaceSwitchKey);
        updateFilterPredicate();
    }

    public void process(PacmanRaceFlucsUpdate pacmanRaceFlucsUpdate) {
        PacmanRaceFlucsEntry entry = convertPacmanRaceFlucsUpdateToEntry(pacmanRaceFlucsUpdate);
        Platform.runLater(() -> pacmanRaceFlucs.add(entry));
    }

    public void show(PacmanRaceFlucsEntry update) {
        Platform.runLater(() -> {
            pacmanRunFlucs.clear();
            pacmanRunFlucs.addAll(update.getRunFlucs());
        });
    }

    private void updateFilterPredicate() {
        @Nullable OpticonRaceSwitchKey race = selectedRace.get();
        if (race == null) {
            Platform.runLater(() -> filteredPacmanRaceFlucs.setPredicate(entry -> false));
            return;
        }
        Platform.runLater(() -> filteredPacmanRaceFlucs.setPredicate(entry ->
                entry.getTrack().equals(race.track())
                && entry.getRace() == race.race()
                && entry.getRaceType() == race.raceType()
        ));
    }

    public SortedList<PacmanRaceFlucsEntry> getSortedPacmanRaceFlucs() { return sortedPacmanRaceFlucs; }

    public SortedList<PacmanRunFlucsEntry> getSortedPacmanRunFlucs() { return sortedPacmanRunFlucs; }
}
