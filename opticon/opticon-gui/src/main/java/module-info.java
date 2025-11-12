
module opticon.gui {

    requires opticon.common;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires jakarta.annotation;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires org.controlsfx.controls;
    requires org.java_websocket;
    requires org.slf4j;
    requires javax.inject;
    requires dagger;
    requires eu.hansolo.tilesfx;
    requires hub.core;

    exports com.edgestackers.opticon.gui to javafx.graphics;

    opens com.edgestackers.opticon.gui.datamodel.cycles to javafx.base;

    opens com.edgestackers.opticon.gui.datamodel to javafx.base;
    opens com.edgestackers.opticon.gui.datamodel.run to javafx.base;

    exports com.edgestackers.opticon.gui.settings to com.fasterxml.jackson.databind;
    opens com.edgestackers.opticon.gui.settings to com.fasterxml.jackson.databind;

    exports com.edgestackers.opticon.gui.view;

    exports com.edgestackers.opticon.gui.view.race;
    exports com.edgestackers.opticon.gui.control.listener;
    exports com.edgestackers.opticon.gui.datamodel;
    exports com.edgestackers.opticon.gui.message;
    exports com.edgestackers.opticon.gui.view.cycles;
    exports com.edgestackers.opticon.gui.view.util;
    exports com.edgestackers.opticon.gui.datamodel.filter;
    exports com.edgestackers.opticon.gui.view.tote;
    exports com.edgestackers.opticon.gui.datamodel.run;
    exports com.edgestackers.opticon.gui.datamodel.cycles;
    exports com.edgestackers.opticon.gui.view.order;
    exports com.edgestackers.opticon.gui.view.turbo;
    exports com.edgestackers.opticon.gui.view.strategy;
    exports com.edgestackers.opticon.gui.view.execution;
    exports com.edgestackers.opticon.gui.view.pacman;
    exports com.edgestackers.opticon.gui.view.operations;
    exports com.edgestackers.opticon.gui.view.parameters;
    exports com.edgestackers.opticon.gui.view.parameters.crud;

    exports com.edgestackers.opticon.gui.datamodel.turbo;
    exports com.edgestackers.opticon.gui.datamodel.order;
    exports com.edgestackers.opticon.gui.datamodel.pacman;
    exports com.edgestackers.opticon.gui.datamodel.operations;
    exports com.edgestackers.opticon.gui.datamodel.parameters;


}