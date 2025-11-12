package com.edgestackers.pgi.services.event.api.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notifyRunnerStatus", namespace = "http://gws.amtote.com/")
public record RunnerStatusNotificationMessage(@XmlElement(name = "ToteID") String toteID,
                                              @XmlElement(name = "programName") String programName,
                                              @XmlElement(name = "race") int race,
                                              @XmlElement(name = "runner") int runner,
                                              @XmlElement(name = "status") String status) implements PgiNotificationMessage {}
