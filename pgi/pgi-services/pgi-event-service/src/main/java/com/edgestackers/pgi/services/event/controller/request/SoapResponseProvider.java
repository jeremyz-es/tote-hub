package com.edgestackers.pgi.services.event.controller.request;

public enum SoapResponseProvider {
    ;

    static String provideSoapResponse(String operationName, String namespaceUri) {
        String responseOperation = operationName + "Response";
        String resultOperation = operationName + "Result";
        return
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                    "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                    "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                    "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                    "<soap:Body>" +
                    "<" + responseOperation + " xmlns=\"" + namespaceUri + "\">" +
                    "<" + resultOperation + ">OK</" + resultOperation + ">" +
                    "</" + responseOperation + ">" +
                    "</soap:Body>" +
                    "</soap:Envelope>";
    }
}
