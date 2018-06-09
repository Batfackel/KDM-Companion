package com.conyargmail.kingdomdeathmonstercompanion.model;

import java.util.ArrayList;

/**
 * Created by Ryan on 4/30/2017.
 */

public class Event {
    private String name;
    private String eventText;
    private int eventNumber;
    private ArrayList<RollTable> table;

    public Event() {
        this.setName("blank");
        this.setEventText("blank");
        this.setEventNumber(0);
    }

    public Event(String name, String eventText, int eventNumber) {
        this.setName(name);
        this.setEventText(eventText);
        this.setEventNumber(eventNumber);
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

    public ArrayList<RollTable> getTable() {
        return table;
    }

    public String getTableFormatted() {
        String formattedTable = "------\n";
        if(table != null) {
            for (int i = 0; i < table.size(); i++) {
                formattedTable += table.get(i).getFormattedTable();
            }
        }
        return formattedTable;
    }

    public String getFormattedEvent() {
        String formattedEvent = String.format("Name: %s\n\nEvent Number: %s\n\nText: %s\n\nRoll Table: %s", getName(), getEventNumber(), getEventText(), getTableFormatted());
        return formattedEvent;
    }

    public void setTable(ArrayList<RollTable> table) {
        this.table = table;
    }
}
