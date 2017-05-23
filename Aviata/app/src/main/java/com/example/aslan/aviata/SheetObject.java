package com.example.aslan.aviata;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by maerizvi on 10/11/16.
 */
public class SheetObject {

    int id, flightNum;
    String depAir, arrAir;
    String depDateStr, arrDateStr;
    String status;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
    Date depDate, arrDate;

    String flightType;
    String estDepDateStr=null, estArrDateStr=null;
    Date estDepDate=null, estArrDate=null;
    int duration;
    Data depAirport, arrAirport;
    String airline;
    String airlCode;
    String output="-";
    public SheetObject(JSONObject jo, HashMap<String, Data> airports, HashMap<String,String> airlines ) {
        JSONObject obj;
        try {
            id = jo.getInt( "flightId" );
            flightNum = jo.getInt("flightNumber");
            depAir = jo.getString( "departureAirportFsCode" );
            arrAir = jo.getString( "arrivalAirportFsCode" );
            depDateStr = jo.getJSONObject( "departureDate" ).getString( "dateLocal" );
            arrDateStr = jo.getJSONObject( "arrivalDate" ).getString( "dateLocal" );
            depDate = format.parse(depDateStr);
            arrDate = format.parse(arrDateStr);
            Log.d("datesOL", depDate+" "+arrDate);

            status = jo.getString( "status" );/*A — Active
                                                C — Canceled
                                                D — Diverted — Была произведена смена пункта назначения (например, по метео-условиям)
                                                DN — Data source needed — Неоткуда получить информацию о статусе
                                                L — Landed
                                                NO — Not Operational
                                                R — Redirected
                                                S — Scheduled
                                                U — Unknown*/
            Log.d("statusOl", status);
            flightType = jo.getJSONObject( "schedule" ).getString( "flightType" );/*J — Scheduled Passanger — Пассажириский по расписанию
                                                                                    M — Scheduled Cargo/Mail(MailOnly) — Грузовой, но только с письмами.
                                                                                    W — Military — Военный*/


            obj=jo.getJSONObject("operationalTimes");

            if(obj.has("estimatedRunwayDeparture")){
                estDepDateStr=obj.getJSONObject("estimatedRunwayDeparture").getString("dateLocal");
                estDepDate=format.parse(estDepDateStr);
            }
            if(obj.has("estimatedRunwayArrival")){
                estArrDateStr=obj.getJSONObject("estimatedRunwayArrival").getString("dateLocal");
                estArrDate=format.parse(estArrDateStr);
            }
            else if(obj.has("estimatedGateArrival")){
                estArrDateStr=obj.getJSONObject("estimatedGateArrival").getString("dateLocal");
                estArrDate=format.parse(estArrDateStr);
            }


            duration = jo.getJSONObject("flightDurations").getInt("scheduledBlockMinutes");

            depAirport = airports.get(depAir);
            arrAirport = airports.get(arrAir);
            airlCode = jo.getString("carrierFsCode");
            airline = airlines.get(airlCode);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getNames(){
        return depAirport.getName()+" "+arrAirport.getName();
    }
    public String toString() {
        output = depAirport.getName()+" ⇨ "+arrAirport.getName();
        return output;
    }
}
