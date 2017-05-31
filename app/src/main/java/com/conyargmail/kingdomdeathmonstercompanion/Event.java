package com.conyargmail.kingdomdeathmonstercompanion;

/**
 * Created by Ryan on 4/30/2017.
 */

public class Event {
    private String name;
    private String eventText;
    private int eventNumber;
    private RollTable table;

    public Event() {
        this.setName("blank");
        this.setEventText("blank");
        this.setEventNumber(0);
        this.setTable(new RollTable());
    }

    public Event(String name, String eventText, int eventNumber, RollTable table) {
        this.setName(name);
        this.setEventText(eventText);
        this.setEventNumber(eventNumber);
        this.setTable(table);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventText() {
        return eventText;
    }

    public void setEventText(String eventText) {
        this.eventText = eventText;
    }

    public int getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(int eventNumber) {
        this.eventNumber = eventNumber;
    }

    public RollTable getTable() {
        return table;
    }

    public void setTable(RollTable table) {
        this.table = table;
    }
}
