module opticon.common {
    exports com.edgestackers.opticon.common.datamodel;
    exports com.edgestackers.opticon.common.service;
    exports com.edgestackers.opticon.common.client;
    exports com.edgestackers.opticon.common.message;
    requires com.fasterxml.jackson.annotation;
    requires jakarta.annotation;
    requires java.net.http;
    requires hub.core;
    requires com.fasterxml.jackson.databind;
    requires org.java_websocket;

    opens com.edgestackers.opticon.common.datamodel to com.fasterxml.jackson.databind;
    exports com.edgestackers.opticon.common.util;
}