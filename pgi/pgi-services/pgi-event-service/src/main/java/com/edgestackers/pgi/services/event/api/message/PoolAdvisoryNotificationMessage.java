package com.edgestackers.pgi.services.event.api.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notifyPoolAdvisory", namespace = "http://gws.amtote.com/")
public record PoolAdvisoryNotificationMessage(@XmlElement(name = "ToteID") String toteID,
                                              @XmlElement(name = "programName") String programName,
                                              @XmlElement(name = "race") int race,
                                              @XmlElement(name = "advisory") String advisory) implements PgiNotificationMessage {}
