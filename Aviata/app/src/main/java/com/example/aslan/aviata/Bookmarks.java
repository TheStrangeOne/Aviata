package com.example.aslan.aviata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Bookmarks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
        setTitle("Bookmarks");
        getSupportActionBar().hide();
    }
}
