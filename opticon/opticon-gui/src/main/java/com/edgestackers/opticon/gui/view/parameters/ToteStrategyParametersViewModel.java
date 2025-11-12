package com.edgestackers.opticon.gui.view.parameters;

import com.edgestackers.opticon.gui.datamodel.parameters.ToteStrategyParametersEntry;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.parameters.ToteStrategyParameters;
import jakarta.annotation.Nullable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.*;

import static com.edgestackers.opticon.gui.datamodel.parameters.ToteStrategyParametersEntryConverter.convertToteStrategyParametersToEntry;

public class ToteStrategyParametersViewModel {
    private final ObservableList<ToteStrategyParametersEntry> allEntries = FXCollections.observableArrayList();
    private final FilteredList<ToteStrategyParametersEntry> filteredEntries = new FilteredList<>(allEntries);
    private final SortedList<ToteStrategyParametersEntry> sortedEntries = new SortedList<>(filteredEntries);

    public void initialize() {
        sortedEntries.setComparator(
                Comparator
                        .comparing(ToteStrategyParametersEntry::getRaceType)
                        .thenComparing(ToteStrategyParametersEntry::getToteProvider)
                        .thenComparing(ToteStrategyParametersEntry::getToteBetType)
                        .thenComparing(ToteStrategyParametersEntry::getJurisdiction)
        );
    }

    public void handle(ToteStrategyParameters parameters) {
        Platform.runLater(() -> {
            @Nullable ToteStrategyParametersEntry existingEntry = findEntry(parameters.strategyId());
            if (existingEntry == null) {
                ToteStrategyParametersEntry newEntry = convertToteStrategyParametersToEntry(parameters);
                allEntries.add(newEntry);
            } else {
                updateEntry(existingEntry, parameters);
            }
        });
    }

    public void register(ObservableList<ToteBetType> checkedBetTypes) {
        checkedBetTypes.addListener((javafx.collections.ListChangeListener<ToteBetType>) c -> Platform.runLater(() -> updateFilterPredicate(checkedBetTypes)));
    }

    void clear() {
        Platform.runLater(allEntries::clear);
    }

    private void updateFilterPredicate(ObservableList<ToteBetType> checkedBetTypes) {
        filteredEntries.setPredicate(entry -> checkedBetTypes.contains(entry.getToteBetType()));
    }

    private void updateEntry(ToteStrategyParametersEntry entry, ToteStrategyParameters parameters) {
        ToteStrategyParametersEntry newEntry = convertToteStrategyParametersToEntry(parameters);
        entry.strategyNameProperty().set(newEntry.getStrategyName());
        entry.createdAtQldTimeProperty().set(newEntry.getCreatedAtQldTime());
        entry.lastUpdatedAtQldTimeProperty().set(newEntry.getLastUpdatedAtQldTime());
        entry.raceTypeProperty().set(newEntry.getRaceType());
        entry.toteProviderProperty().set(newEntry.getToteProvider());
        entry.toteBetTypeProperty().set(newEntry.getToteBetType());
        entry.jurisdictionProperty().set(newEntry.getJurisdiction());
        entry.dividendModelProperty().set(newEntry.getDividendModel());
        entry.dividendModelMajorVersionProperty().set(newEntry.getDividendModelMajorVersion());
        entry.dividendModelMinorVersionProperty().set(newEntry.getDividendModelMinorVersion());
        entry.theoModelProperty().set(newEntry.getTheoModel());
        entry.theoModelMajorVersionProperty().set(newEntry.getTheoModelMajorVersion());
        entry.theoModelMinorVersionProperty().set(newEntry.getTheoModelMinorVersion());
        entry.maxRaceBetProperty().set(newEntry.getMaxRaceBet());
        entry.rebateProperty().set(newEntry.getRebate());
        entry.takeoutProperty().set(newEntry.getTakeout());
        entry.discountProperty().set(newEntry.getDiscount());
        entry.minBetProperty().set(newEntry.getMinBet());
        entry.maxDividendAgeSecondsProperty().set(newEntry.getMaxDividendAgeSeconds());
        entry.minPoolProperty().set(newEntry.getMinPool());
        entry.maxPoolProperty().set(newEntry.getMaxPool());
        entry.maxTheoAgeSecondsProperty().set(newEntry.getMaxTheoAgeSeconds());
        entry.triggerRaceEventsProperty().setAll(newEntry.getTriggerRaceEvents());
    }

    @Nullable
    private ToteStrategyParametersEntry findEntry(String strategyId) {
        return allEntries.stream()
                .filter(entry -> entry.getStrategyId().equals(strategyId))
                .findFirst()
                .orElse(null);
    }

    public SortedList<ToteStrategyParametersEntry> getSortedEntries() {
        return sortedEntries;
    }
}
