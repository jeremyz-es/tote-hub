package com.edgestackers.pgi.services.event.datamodel.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PriceData", namespace = "http://gws.amtote.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class NotifyRaceOfficialEventMessage implements PgiEventMessage {

    @XmlElement(name = "ToteID", namespace = "http://gws.amtote.com/")
    private String toteId;

    @XmlElement(name = "programName", namespace = "http://gws.amtote.com/")
    private String programName;

    @XmlElement(name = "race", namespace = "http://gws.amtote.com/")
    private int race;

    public String getToteId() {
        return toteId;
    }

    public void setToteId(String toteId) {
        this.toteId = toteId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }
}
