package com.edgestackers.pgi.services.event.controller.request;

import com.edgestackers.pgi.services.event.datamodel.api.PgiEventMessage;
import com.edgestackers.pgi.services.event.handler.PgiEventMessageProcessor;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import javax.xml.bind.annotation.XmlRootElement;

import static com.edgestackers.pgi.services.event.controller.request.SoapResponseProvider.provideSoapResponse;
import static com.edgestackers.pgi.services.event.controller.request.SoapUnmarshaller.unmarshalBodyElement;

public class NotificationRequestHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationRequestHandler.class);
    private static final String RESPONSE_CONTENT_TYPE = "application/soap+xml; charset=utf-8";
    private final PgiEventMessageProcessor pgiEventMessageProcessor;

    public NotificationRequestHandler(PgiEventMessageProcessor pgiEventMessageProcessor) {
        this.pgiEventMessageProcessor = pgiEventMessageProcessor;
    }

    public void handle(Context context) {
        String requestBody = context.body();
        try {
            Object bodyElement = unmarshalBodyElement(requestBody);
            if (bodyElement instanceof PgiEventMessage pgiEventMessage) {
                Class<?> msgClass = pgiEventMessage.getClass();
                XmlRootElement root = msgClass.getAnnotation(XmlRootElement.class);
                String operationName = root.name();
                String namespaceUri = root.namespace();
                sendSoapResponse(context, operationName, namespaceUri);
                pgiEventMessageProcessor.handle(pgiEventMessage);
                // TODO: Do we care about asking for another copy of the message?
            }
            else if (bodyElement instanceof Element element) {
                String operationName = element.getLocalName();
                String namespaceUri = element.getNamespaceURI();
                sendSoapResponse(context, operationName, namespaceUri);
            }
        }
        catch (Exception e) {
            LOGGER.error("Failed to handle SOAP action due to [{}] - {}, raw request body: {}", e.getClass().getSimpleName(), e.getMessage(), context.body());
        }
    }

    private void sendSoapResponse(Context context, String operationName, String namespaceUri) {
        String responseXml = provideSoapResponse(operationName, namespaceUri);
        context.contentType(RESPONSE_CONTENT_TYPE);
        context.result(responseXml);
        context.status(HttpStatus.OK);
    }
}
