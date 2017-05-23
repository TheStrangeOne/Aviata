package com.example.aslan.aviata;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by maerizvi on 10/11/16.
 */
public class Sheets {
    ArrayList<SheetObject> sos = null;

    HashMap<String, Data> airports = new HashMap<String, Data>();
    HashMap<String,String> airlines = new HashMap<String, String>();

    private static Sheets ourInstance = new Sheets();

    public static Sheets getInstance() {
        return ourInstance;
    }

    private Sheets() {
    }

    public void setData(String jsonString ) {
        try {
            JSONObject jo = new JSONObject( jsonString );
            JSONObject obj = null;
            JSONArray jarray = jo.getJSONObject("appendix").getJSONArray("airlines");
            for (int j = 0; j < jarray.length(); j++) {
                obj = jarray.getJSONObject(j);
                airlines.put(obj.getString("fs"),obj.getString("name"));
            }
            jarray = jo.getJSONObject("appendix").getJSONArray("airports");
            for (int j = 0; j < jarray.length(); j++) {
                obj = jarray.getJSONObject(j);
                airports.put(obj.getString("fs"),new Data(obj.getString("fs"), obj.getString("name"),
                        obj.getString("city"), obj.getString("countryCode"), obj.getString("countryName"), obj.getString("localTime")));

            }

            jarray = jo.getJSONArray( "flightStatuses" );
            Log.d("Flights", ""+jarray.length());
            sos = new ArrayList<>( jarray.length() );
            for (int j = 0; j < jarray.length(); j++) {
                jo = jarray.getJSONObject(j);
                SheetObject so = new SheetObject(jo, airports, airlines);
                sos.add(so);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<SheetObject> getData() {
        return sos;
    }

    public int getLength() {
        return sos.size();
    }
}
