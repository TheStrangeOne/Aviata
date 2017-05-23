package com.example.aslan.aviata;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Log log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //String string = R.string.list;
        File file = getApplicationContext().getFilesDir();
        Log.d("File", file.toString());
        int counter =0;
        InputStream input = null;
            try {
            String list[] = getAssets().list("");
                for(int j=0; j<list.length; j++){
                    Log.d("List", list[j]);
                }
                input = getAssets().open("result.txt");


               BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String result="";
                String line;

                Log.e("Result", result);

        } catch (IOException e) {
            e.printStackTrace();
                Log.d("Catch", "Catched");
        }



    }

    public void toAirport(View view){
        Intent airport = new Intent(this, Airport.class);
        startActivity(airport);
    }
    public void toFlights(View view){
        Intent flights = new Intent(this, Flights.class);
        startActivity(flights);
    }
    public void toBookmarks(View view){
        Intent bookmarks = new Intent(this, Bookmarks.class);
        startActivity(bookmarks);
    }
}
