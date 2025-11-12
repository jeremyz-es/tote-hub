package com.edgestackers.pgi.services.event.controller.request;

import com.edgestackers.pgi.services.event.datamodel.api.NotifyCycleDataEventMessage;
import com.edgestackers.pgi.services.event.datamodel.api.NotifyRaceOfficialEventMessage;
import com.edgestackers.pgi.services.event.datamodel.api.SoapEnvelope;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public enum SoapUnmarshaller {
    ;

    static Object unmarshalBodyElement(String requestBody) throws Exception {
        JAXBContext jaxbContext = getJAXBContext();
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        SoapEnvelope envelope = (SoapEnvelope) unmarshaller.unmarshal(new StringReader(requestBody));
        return envelope.getBody().getAny().getFirst();
    }

    private static JAXBContext getJAXBContext() throws Exception {
        return JAXBContext.newInstance(
                SoapEnvelope.class,
                NotifyCycleDataEventMessage.class,
                NotifyRaceOfficialEventMessage.class
        );
    }
}
