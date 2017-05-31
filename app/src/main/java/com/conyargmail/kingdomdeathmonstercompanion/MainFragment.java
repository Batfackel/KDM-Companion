package com.conyargmail.kingdomdeathmonstercompanion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Ryan on 4/30/2017.
 */

public class MainFragment extends android.support.v4.app.Fragment {
    TextView test;
    public MainFragment() {
        // Empty constructor required for fragment subclasses

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_landing_fragment, container, false);
        String huntText = "This is the fragment for hunt events.";
        MainActivity.getMainInstance().setTitle("KDMC");
        return rootView;
    }
}
