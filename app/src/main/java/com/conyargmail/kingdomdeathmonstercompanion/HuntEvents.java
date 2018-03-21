package com.conyargmail.kingdomdeathmonstercompanion;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.transform.Result;

/**
 * Created by Ryan on 4/17/2017.
 */

public class HuntEvents extends android.support.v4.app.Fragment {
    Button randomEvent;
    int minimum, maximum;
    NodeList nodeList;
    ArrayList<Event> events;
    Button rollButton, specificHuntButton, survivor;
    TextView eventText, eventTable, survivorRolls;
    EditText specificHunt;
    int hunt, survivorOneRoll, survivorTwoRoll, survivorThreeRoll, survivorFourRoll;

    public HuntEvents() {
        // Empty constructor required for fragment subclasses

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_hunt_events, container, false);
        String huntText = "This is the fragment for hunt events.";
        // // TODO: 1/14/2018 Async task for reading from xml files
        AsyncXMLRead xmlRead = new AsyncXMLRead(this.getContext());
        try {
            events = xmlRead.execute().get();
            if(events == null) {
                xmlRead.onPostExecute("Failed to Load");
            } else {
                xmlRead.onPostExecute("Loaded Successfully");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        rollButton = (Button) rootView.findViewById(R.id.random_event_button);
        specificHunt = (EditText) rootView.findViewById(R.id.specific_hunt_text);
        survivorRolls = (TextView) rootView.findViewById(R.id.suvivor_rolls_view);
        survivor = (Button) rootView.findViewById(R.id.survivor_rolls);
        survivor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                survivorOneRoll = ThreadLocalRandom.current().nextInt(0, 10) + 1;
                survivorTwoRoll = ThreadLocalRandom.current().nextInt(0, 10) + 1;
                survivorThreeRoll = ThreadLocalRandom.current().nextInt(0, 10) + 1;
                survivorFourRoll = ThreadLocalRandom.current().nextInt(0, 10) + 1;
                String rolls = String.format("Survivor One: %d Survivor Two: %d\nSurvivor Three: %d Survivor Four: %d", survivorOneRoll, survivorTwoRoll, survivorThreeRoll, survivorFourRoll);
                survivorRolls.setText(rolls);
            }
        });
        specificHuntButton = (Button) rootView.findViewById(R.id.specific_hunt_button);
        specificHuntButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hunt = Integer.valueOf(specificHunt.getText().toString());
                if(hunt < 1) {
                    hunt = 1;
                }
                if(hunt > events.size()) {
                    hunt = events.size();
                }
                hunt--;
                eventText.setText(events.get(hunt).getFormattedEvent());
            }
        });
        eventText = (TextView) rootView.findViewById((R.id.event_text));
        rollButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int randomNumber = ThreadLocalRandom.current().nextInt(0, events.size());
                eventText.setText(events.get(randomNumber).getFormattedEvent());
            }
        });
        MainActivity.getMainInstance().setTitle("Hunt Events");
        return rootView;
    }
}
