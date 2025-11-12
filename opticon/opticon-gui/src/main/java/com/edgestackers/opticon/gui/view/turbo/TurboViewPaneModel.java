package com.edgestackers.opticon.gui.view.turbo;

import com.edgestackers.opticon.common.datamodel.OpticonDividendPredictionUpdate;
import com.edgestackers.opticon.common.datamodel.OpticonTheoUpdate;
import com.edgestackers.opticon.gui.datamodel.OpticonRaceSwitchKey;
import com.edgestackers.opticon.gui.datamodel.filter.ExoticInstrumentFilter;
import com.edgestackers.opticon.gui.datamodel.turbo.OpticonDividendPredictionUpdateEntry;
import com.edgestackers.opticon.gui.datamodel.turbo.OpticonTheoUpdateEntry;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import jakarta.annotation.Nullable;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.Comparator;

import static com.edgestackers.opticon.gui.datamodel.turbo.OpticonDividendPredictionUpdateEntryConverter.convertOpticonDividendPredictionUpdateToEntry;
import static com.edgestackers.opticon.gui.datamodel.turbo.OpticonTheoUpdateEntryConverter.convertOpticonTheoUpdateToEntry;

public class TurboViewPaneModel {
    private final ObservableList<OpticonDividendPredictionUpdateEntry> dividends = FXCollections.observableArrayList();
    private final FilteredList<OpticonDividendPredictionUpdateEntry> filteredDividends = new FilteredList<>(dividends, entry -> false);
    private final SortedList<OpticonDividendPredictionUpdateEntry> sortedDividends = new SortedList<>(filteredDividends);
    private final ObservableList<OpticonTheoUpdateEntry> theos = FXCollections.observableArrayList();
    private final FilteredList<OpticonTheoUpdateEntry> filteredTheos = new FilteredList<>(theos, entry -> false);
    private final SortedList<OpticonTheoUpdateEntry> sortedTheos = new SortedList<>(filteredTheos);
    private final SimpleObjectProperty<OpticonRaceSwitchKey> selectedRace = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<ExoticInstrumentFilter> selectedFilter = new SimpleObjectProperty<>();

    public void initialize() {
        sortedDividends.setComparator(Comparator.comparing(OpticonDividendPredictionUpdateEntry::getGeneratedAtEpochMillis).reversed());
        sortedTheos.setComparator(Comparator.comparing(OpticonTheoUpdateEntry::getGeneratedAtEpochMillis).reversed());
    }

    public void switchTo(OpticonRaceSwitchKey opticonRaceSwitchKey) {
        selectedRace.set(opticonRaceSwitchKey);
        updateFilterPredicate();
    }

    public void switchTo(ExoticInstrumentFilter filter) {
        selectedFilter.set(filter);
        updateFilterPredicate();
    }

    public void process(OpticonDividendPredictionUpdate update) {
        Platform.runLater(() -> dividends.add(convertOpticonDividendPredictionUpdateToEntry(update)));
    }

    public void process(OpticonTheoUpdate update) {
        Platform.runLater(() -> theos.add(convertOpticonTheoUpdateToEntry(update)));
    }

    private void updateFilterPredicate() {
        @Nullable ExoticInstrumentFilter filter = selectedFilter.get();
        @Nullable ToteBetType toteBetType = filter == null ? null : filter.toteBetType();
        @Nullable TotePoolJurisdiction jurisdiction = filter == null ? null : filter.totePoolJurisdiction();
        @Nullable OpticonRaceSwitchKey race = selectedRace.get();
        if (race == null) {
            Platform.runLater(() -> filteredDividends.setPredicate(entry -> false));
            Platform.runLater(() -> filteredTheos.setPredicate(entry -> false));
            return;
        }
        Platform.runLater(() -> filteredDividends.setPredicate(entry ->
                entry.getToteBetType().equals(toteBetType)
                        && entry.getJurisdiction().equals(jurisdiction)
                        && entry.getTrack().equals(race.track())
                        && entry.getRace() == race.race()
                        && entry.getRaceType() == race.raceType()
        ));
        Platform.runLater(() -> filteredTheos.setPredicate(entry ->
                entry.getToteBetType().equals(toteBetType)
                        && entry.getTrack().equals(race.track())
                        && entry.getRace() == race.race()
                        && entry.getRaceType() == race.raceType()
        ));
    }

    public SortedList<OpticonDividendPredictionUpdateEntry> getSortedDividends() { return sortedDividends; }

    public SortedList<OpticonTheoUpdateEntry> getSortedTheos() { return sortedTheos; }
}
