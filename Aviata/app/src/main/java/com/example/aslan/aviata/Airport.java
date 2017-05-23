package com.example.aslan.aviata;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

public class Airport extends AppCompatActivity implements AdapterView.OnItemClickListener {
    SearchableSpinner codes, names;
    DatePicker date;
    ListView listView;
    Button b;
    String dateDay, dateMonth, dateYear, dateHour, airport;
    NumberPicker numberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport);
        //setTitle("Airport");
        getSupportActionBar().hide();
        codes = (SearchableSpinner)findViewById(R.id.codes);
        names = (SearchableSpinner)findViewById(R.id.names);
        date = (DatePicker)findViewById(R.id.date);
        b = (Button)findViewById(R.id.b);
        listView = (ListView)findViewById(R.id.lv);
        listView.setOnItemClickListener(this);

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });



        Resources res = getApplicationContext().getResources();
        String[] code_arr = res.getStringArray(R.array.code);
        String[] name_arr = res.getStringArray(R.array.name);
        ArrayAdapter<String> adapter  = new ArrayAdapter<String>(this, R.layout.spinner_item, code_arr);
        codes.setAdapter(adapter);
        adapter  = new ArrayAdapter<String>(this, R.layout.spinner_item, name_arr);
        names.setAdapter(adapter);


        numberPicker = (NumberPicker)findViewById(R.id.hours);
        numberPicker.setMaxValue(23);
        numberPicker.setMinValue(0);


        codes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                names.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        names.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                codes.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void toSearch(View view){
       // String s = (String)codes.getSelectedItem();
        dateYear =""+ date.getYear();
        dateHour =""+numberPicker.getValue();
        dateDay=""+date.getDayOfMonth();
        dateMonth="" + date.getMonth();
        dateYear="2017";
        airport=(String)codes.getSelectedItem();


        //Log.d("Selected", s);
        LoadAsyncTask lat = new LoadAsyncTask( this, listView, b );
        lat.execute( "https://api.flightstats.com/flex/flightstatus/historical/rest/v3/json/airpo" +
                "rt/status/"+airport+"/dep/"+dateYear+"/"+dateMonth+"/"+dateDay+"/"+dateHour+"?appId=c60c1b52&appKey" +
                "=c0c5809ac7f8440c7879c1d4d004ffb7&utc=false&numHours=6" );
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       // Log.d( TAG, "Starting the new activity.");
        Intent intent = new Intent( this, DetailActivity.class );
        intent.putExtra( "position", i );
        startActivity( intent );
    }
}
