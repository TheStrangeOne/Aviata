package com.example.aslan.aviata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();

        TextView id = (TextView) findViewById(R.id.ID);
        TextView flNum = (TextView) findViewById(R.id.flNum);
        TextView deptAir = (TextView) findViewById(R.id.deptAirport);
        TextView arrAir = (TextView) findViewById(R.id.arrAirport);
        TextView deptDate = (TextView) findViewById(R.id.deptDate);
        TextView arrDate = (TextView) findViewById(R.id.arrDate);
        TextView status = (TextView) findViewById(R.id.status);
        TextView duration = (TextView) findViewById(R.id.duration);
//        TextView s2 = (TextView) findViewById(R.id.student2);
//        TextView title = (TextView) findViewById(R.id.description);
//        TextView appr = (TextView) findViewById(R.id.approved);

        int index = getIntent().getIntExtra( "position", 0 );
        Sheets sl = Sheets.getInstance();
        ArrayList<SheetObject> al = sl.getData();
        SheetObject so = al.get( index );
        if ( so != null )
        {
           id.setText(so.id+" ");
           flNum.setText(so.airlCode+so.flightNum);
           deptAir.setText(so.depAirport.getName()+"("+so.depAir+")");
           arrAir.setText(so.arrAirport.getName()+"("+so.arrAir+")");
            deptDate.setText(""+so.depDate);
            arrDate.setText(""+so.arrDate);
            if(so.status.equals("A"))
            status.setText("Active ");
            else if(so.status.equals("C"))
                status.setText("Canceled ");
            else if(so.status.equals("L"))
                status.setText("Landed ");
            else if(so.status.equals("S"))
                status.setText("Scheduled ");
            else
            status.setText(so.status+" ");


            duration.setText(so.duration+"minutes");
           /* s1.setText(so.s1First + " " + so.s1Last);
            s2.setText("School and major: " + so.school+", "+so.major);
            title.setText("Year of study: " + so.year);
            appr.setText("E-mail address: " + so.approved);*/
        }
        else
        {
            Toast toast = Toast.makeText( this, "No data", Toast.LENGTH_SHORT );
            toast.show();
        }
    }
}
