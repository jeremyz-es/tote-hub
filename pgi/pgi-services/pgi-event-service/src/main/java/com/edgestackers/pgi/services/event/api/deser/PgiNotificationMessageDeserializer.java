package com.edgestackers.pgi.services.event.api.deser;

import com.edgestackers.pgi.services.event.api.message.PgiNotificationSoapBody;
import com.edgestackers.pgi.services.event.api.message.PgiNotificationSoapEnvelope;
import com.edgestackers.pgi.services.event.api.message.PgiNotificationMessage;
import com.edgestackers.pgi.services.event.api.message.RaceTimeUpdatedNotificationMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class PgiNotificationMessageDeserializer {

    public static PgiNotificationMessage fromSoapXml(String xml) throws Exception {
        JAXBContext context = JAXBContext.newInstance(
                PgiNotificationSoapEnvelope.class,
                PgiNotificationSoapBody.class,
                RaceTimeUpdatedNotificationMessage.class
        );
        Unmarshaller unmarshaller = context.createUnmarshaller();
        PgiNotificationSoapEnvelope envelope = (PgiNotificationSoapEnvelope) unmarshaller.unmarshal(new StringReader(xml));
        return envelope.body.message;
    }
}
