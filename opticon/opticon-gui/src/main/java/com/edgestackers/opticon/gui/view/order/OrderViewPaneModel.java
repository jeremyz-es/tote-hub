package com.edgestackers.opticon.gui.view.order;

import com.edgestackers.opticon.common.datamodel.OpticonBetStatus;
import com.edgestackers.opticon.common.datamodel.OpticonOrderStatus;
import com.edgestackers.opticon.common.datamodel.OpticonOrderUpdate;
import com.edgestackers.opticon.gui.datamodel.OpticonRaceSwitchKey;
import com.edgestackers.opticon.gui.datamodel.filter.ExoticInstrumentFilter;
import com.edgestackers.opticon.gui.datamodel.order.OpticonBetUpdateEntry;
import com.edgestackers.opticon.gui.datamodel.order.OpticonOrderUpdateEntry;
import jakarta.annotation.Nullable;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.Comparator;

import static com.edgestackers.opticon.gui.datamodel.order.OpticonOrderUpdateEntryConverter.convertOpticonOrderUpdateToEntry;

public class OrderViewPaneModel {
    private final ObservableList<OpticonOrderUpdateEntry> orderEntries = FXCollections.observableArrayList();
    private final FilteredList<OpticonOrderUpdateEntry> filteredOrderEntries = new FilteredList<>(orderEntries, order -> true);
    private final SortedList<OpticonOrderUpdateEntry> sortedOrderEntries = new SortedList<>(filteredOrderEntries);
    private final ObservableList<OpticonBetUpdateEntry> betEntries = FXCollections.observableArrayList();
    private final FilteredList<OpticonBetUpdateEntry> filteredBetEntries = new FilteredList<>(betEntries, bet -> true);
    private final SortedList<OpticonBetUpdateEntry> sortedBetEntries =  new SortedList<>(filteredBetEntries);
    private final SimpleBooleanProperty linkRaceFilterProperty = new SimpleBooleanProperty();
    private final SimpleBooleanProperty hideAcceptedOrders = new SimpleBooleanProperty();
    private final SimpleBooleanProperty hideAcceptedBets = new SimpleBooleanProperty();
    private final SimpleObjectProperty<OpticonRaceSwitchKey> selectedRaceKey = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<ExoticInstrumentFilter> selectedInstrument = new SimpleObjectProperty<>();
    private final SimpleStringProperty selectionFilter = new SimpleStringProperty();

    public void initialize() {
        initializeListeners();
        sortedOrderEntries.setComparator(Comparator.comparing(OpticonOrderUpdateEntry::getEpochMillisTimestamp).reversed());
    }

    public void handle(OpticonRaceSwitchKey key) {
        selectedRaceKey.set(key);
    }

    public void process(OpticonOrderUpdate opticonOrderUpdate) {
        Platform.runLater(() -> {
            OpticonOrderUpdateEntry newEntry = convertOpticonOrderUpdateToEntry(opticonOrderUpdate);
            orderEntries.removeIf(orderEntry -> orderEntry.getOpticonOrderStatus() == OpticonOrderStatus.IN_FLIGHT && orderEntry.getClientOrderId().equals(newEntry.getClientOrderId()));
            orderEntries.add(newEntry);
        });
    }

    public void switchTo(OpticonOrderUpdateEntry entry) {
        Platform.runLater(() -> {
            betEntries.clear();
            if (entry != null) betEntries.addAll(entry.getBetEntries());
        });
    }

    private void initializeListeners() {
        selectedRaceKey.addListener((observable, oldValue, newValue) -> updateOrderFilterPredicate());
        selectedInstrument.addListener((observable, oldValue, newValue) -> updateOrderFilterPredicate());
        linkRaceFilterProperty.addListener((observable, oldValue, newValue) -> updateOrderFilterPredicate());
        hideAcceptedOrders.addListener((observable, oldValue, newValue) -> updateOrderFilterPredicate());
        hideAcceptedBets.addListener((observable, oldValue, newValue) -> updateBetFilterPredicate());
        selectionFilter.addListener((observable, oldValue, newValue) -> updateBetFilterPredicate());
    }

    private void updateOrderFilterPredicate() {
        @Nullable OpticonRaceSwitchKey selectedRace = selectedRaceKey.get();
        boolean linkRaceFilter = linkRaceFilterProperty.get();
        boolean hideAcceptedOrders = hideAcceptedOrdersProperty().get();
        Platform.runLater(() -> filteredOrderEntries.setPredicate(entry -> {
            boolean raceCheckPasses = (selectedRace != null && (!linkRaceFilter || entry.getTrack().equals(selectedRace.track()) && entry.getRace() == selectedRace.race())); // TODO: Add RaceType here
            boolean hideCheck = !hideAcceptedOrders || entry.getOpticonOrderStatus() != OpticonOrderStatus.ACCEPTED;
            return raceCheckPasses && hideCheck;
        }));
    }

    private void updateBetFilterPredicate() {
        boolean hideAcceptedBets = hideAcceptedBetsProperty().get();
        String selection = selectionFilter.get();
        Platform.runLater(() -> filteredBetEntries.setPredicate(entry -> {
            boolean selectionFilterCheckPasses = selection.isEmpty() || selection.equals(entry.getSelections());
            return (entry.getOpticonBetStatus() != OpticonBetStatus.ACCEPTED || !hideAcceptedBets) && (selectionFilterCheckPasses);
        }));
    }

    public SimpleBooleanProperty linkRaceFilterPropertyProperty() { return linkRaceFilterProperty; }

    public SimpleBooleanProperty hideAcceptedOrdersProperty() { return hideAcceptedOrders; }

    public SimpleBooleanProperty hideAcceptedBetsProperty() { return hideAcceptedBets; }

    public SortedList<OpticonOrderUpdateEntry> getSortedOrderEntries() { return sortedOrderEntries; }

    public SortedList<OpticonBetUpdateEntry> getSortedBetEntries() { return sortedBetEntries; }

    public SimpleStringProperty selectionFilterProperty() { return selectionFilter; }
}
