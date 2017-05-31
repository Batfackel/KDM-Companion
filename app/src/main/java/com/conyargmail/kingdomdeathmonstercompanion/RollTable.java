package com.conyargmail.kingdomdeathmonstercompanion;

import java.util.ArrayList;

/**
 * Created by Ryan on 4/30/2017.
 */

public class RollTable {
    ArrayList<Roll> table;

    public RollTable() {
        table = new ArrayList<>();
    }

    public void addRollSet(Roll roll) {
        table.add(roll);
    }

    public ArrayList<Roll> getTable() {
        return table;
    }

    public Roll getSpecificRoll(int index) {
        return table.get(index);
    }
}
