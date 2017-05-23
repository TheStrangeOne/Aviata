package com.example.aslan.aviata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Flights extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);
        setTitle("Flights");
        getSupportActionBar().hide();
    }
}
