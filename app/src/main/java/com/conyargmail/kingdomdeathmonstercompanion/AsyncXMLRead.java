package com.conyargmail.kingdomdeathmonstercompanion;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.AsyncTask;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.transform.Result;

/**
 * Created by Ryan on 1/14/2018.
 */

public class AsyncXMLRead extends AsyncTask<Void, Void, ArrayList<Event>> {
    private String response;
    private Context context;
    private String fileToLoad;

    public AsyncXMLRead(Context context, String fileToLoad) {
        this.context = context;
        this.fileToLoad = fileToLoad;
    }

    public AsyncXMLRead(Context context) {
        this.context = context;
    }

    @Override
    protected ArrayList<Event> doInBackground(Void... params) {
        String stringBuffer;
        Resources resources = MainActivity.getMainInstance().getResources();
        XmlResourceParser resourceParser = resources.getXml(R.xml.hunt_events);
        ArrayList<Event> events = new ArrayList<>();
        Event event = new Event();
        ArrayList<RollTable> rollTableList = new ArrayList<>();
        try {
            resourceParser.next();
            int eventType = resourceParser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT) {
                int rollTableCounter = 0;
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        stringBuffer = resourceParser.getName();
                        if(stringBuffer.compareTo("event") == 0){
                            String eventName = resourceParser.getAttributeValue(0);
                            String eventNumber = resourceParser.getAttributeValue(1);
                            event.setName(eventName);//event name
                            event.setEventNumber(Integer.valueOf(eventNumber));
                        }
                        else if(stringBuffer.compareTo("text") == 0) {
                            resourceParser.next();
                            String eventText = resourceParser.getText();
                            event.setEventText(eventText);
                        }
                        else if(stringBuffer.compareTo("rolltable") == 0) {
                            rollTableList.add(new RollTable());

                        }
                        else if(stringBuffer.compareTo("roll") == 0) {
                            Roll roll = new Roll(resourceParser.getAttributeValue(0), resourceParser.getAttributeValue(1));
                            resourceParser.next();
                            roll.setText(resourceParser.getText());
                            rollTableList.get(rollTableCounter).addRollSet(roll);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        stringBuffer = resourceParser.getName();
                        if(stringBuffer.compareTo("event") == 0) {
                            events.add(event);
                            event = new Event();
                            rollTableList = new ArrayList<>();
                        }
                        else if(stringBuffer.compareTo("rolltable") == 0) {
                            event.setTable(rollTableList);
                            rollTableList = new ArrayList<>();
                            rollTableCounter++;
                        }
                }
                eventType = resourceParser.next();
            }
            resourceParser.close();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }

    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
