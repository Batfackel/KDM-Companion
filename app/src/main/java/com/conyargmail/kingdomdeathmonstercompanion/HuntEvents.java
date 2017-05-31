package com.conyargmail.kingdomdeathmonstercompanion;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Ryan on 4/17/2017.
 */

public class HuntEvents extends android.support.v4.app.Fragment {
    TextView huntText;
    Button randomEvent;
    int minimum, maximum;
    NodeList nodeList;
    String eventText;
    ArrayList<Event> events;
    private static final String nameSpace = null;

    public HuntEvents() {
        // Empty constructor required for fragment subclasses

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hunt_events, container, false);
        String huntText = "This is the fragment for hunt events.";
        XmlResourceParser parser = MainActivity.getMainInstance().getResources().getXml(R.xml.hunt_events);
        try {
            int eventType = parser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT) {
                Event event = new Event();
                if(eventType == XmlPullParser.START_TAG) {
                    String tag = parser.getName();
                    switch (tag) {
                        case "event": event.setName(parser.getAttributeValue(nameSpace, "name"));
                            event.setEventNumber(Integer.parseInt(parser.getAttributeValue(nameSpace, "number")));
                            parser.next();
                            break;
                        case "text": event.setEventText(parser.getAttributeValue(nameSpace, "text"));
                            parser.next();
                            break;
                        case "rolltable": parser.next();
                    }
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        events = readEvents(parser);
        randomEvent = (Button) rootView.findViewById(R.id.random_event_button);
        randomEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int number = minimum + rand.nextInt((maximum - minimum) + 1);
//                Element e = (Element) nodeList.item(number);
                eventText = "shit stuff";
            }
        });
        MainActivity.getMainInstance().setTitle("Hunt Events");
        return rootView;
    }

    private ArrayList<Event> readEvents(XmlPullParser parser) {
        ArrayList<Event> events = new ArrayList<>();

        try {
            parser.require(XmlPullParser.START_TAG, nameSpace, "huntevents");
            while(parser.next() != XmlPullParser.END_TAG) {
                Event event = new Event();
                if(parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                if(name.equals("event")) {
                    event.setName(readEventName(parser));
                    event.setEventNumber(readEventNumber(parser));
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }

    private int readEventNumber(XmlPullParser parser) {
        try {
            parser.require(XmlPullParser.START_TAG, nameSpace, "event");
            int eventNumber = Integer.parseInt(parser.getAttributeValue(nameSpace, "number"));
            return eventNumber;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String readEventName(XmlPullParser parser) {
        try {
            parser.require(XmlPullParser.START_TAG, nameSpace, "event");
            String name = parser.getAttributeValue(nameSpace, "name");
            return name;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
