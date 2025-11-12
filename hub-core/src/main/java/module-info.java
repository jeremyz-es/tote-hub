module hub.core {

    exports com.edgestackers.tote.hub.core.datamodel.cycle;
    exports com.edgestackers.tote.hub.core.datamodel.message;

    requires com.fasterxml.jackson.annotation;
    requires jakarta.annotation;
    requires jsr305;
    requires java.net.http;
    requires org.slf4j;
    requires com.fasterxml.jackson.databind;

    opens com.edgestackers.tote.hub.core.datamodel.cycle to com.fasterxml.jackson.databind;
    exports com.edgestackers.tote.hub.core.datamodel.message.theos;
    exports com.edgestackers.tote.hub.core.datamodel.message.nitro;

    opens com.edgestackers.tote.hub.core.datamodel.message.nitro to com.fasterxml.jackson.databind;
    exports com.edgestackers.tote.hub.core.datamodel.message.turbo;
    exports com.edgestackers.tote.hub.core.datamodel.message.order;
    exports com.edgestackers.tote.hub.core.datamodel.context;
    exports com.edgestackers.tote.hub.core.datamodel.core;
    exports com.edgestackers.tote.hub.core.datamodel.turbo;
    exports com.edgestackers.tote.hub.core.util;
    exports com.edgestackers.tote.hub.core.metadata;
    exports com.edgestackers.tote.hub.core.parameters;
}