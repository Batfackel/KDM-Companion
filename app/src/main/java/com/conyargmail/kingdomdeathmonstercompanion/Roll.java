package com.conyargmail.kingdomdeathmonstercompanion;

/**
 * Created by Ryan on 4/30/2017.
 */

public class Roll {
    private int minimumValue;
    private int maximumValue;
    private String text;

    public Roll(int minimumValue, int maximumValue, String text) {
        setMinimumValue(minimumValue);
        setMaximumValue(maximumValue);
        setText(text);
    }

    public int getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(int minimumValue) {
        this.minimumValue = minimumValue;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(int maximumValue) {
        this.maximumValue = maximumValue;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
