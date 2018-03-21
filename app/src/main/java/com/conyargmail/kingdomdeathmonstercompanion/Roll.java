package com.conyargmail.kingdomdeathmonstercompanion;

/**
 * Created by Ryan on 4/30/2017.
 */

public class Roll {
    private String minimumValue;
    private String maximumValue;
    private String text;

    public Roll(String minimumValue, String maximumValue, String text) {
        setMinimumValue(minimumValue);
        setMaximumValue(maximumValue);
        setText(text);
    }

    public Roll(String minimumValue, String maximumValue) {
        setMinimumValue(minimumValue);
        setMaximumValue(maximumValue);
    }

    public String getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(String minimumValue) {
        this.minimumValue = minimumValue;
    }

    public String getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(String maximumValue) {
        this.maximumValue = maximumValue;
    }

    public String getText() {
        return text;
    }
    public String getRollFormatted() {
        String data = String.format("%s - %s : %s \n\n", minimumValue, maximumValue, text);
        return data;
    }

    public void setText(String text) {
        this.text = text;
    }
}
