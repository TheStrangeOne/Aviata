package com.example.aslan.aviata;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by maerizvi on 10/11/16.
 */
public class LoadAsyncTask extends AsyncTask<String, Integer, String> {

    static public final String TAG="LoadAsyncTask";
    Context c;
    ListView lv;
    Button b;
    Sheets sl;
    ProgressDialog pd;
    String string;

    public LoadAsyncTask( Context c, ListView lv, Button b) {
        this.c = c;
        this.lv = lv;
        this.b = b;
    }

    @Override
    protected String doInBackground(String... values) {
        // First parameter is the URL
        // Second parameter could be the URL parameters, but not now

        String retMsg = null;
        String result = null;

        try
        {
            // This call to encode is only available in API 19. whereas the simpler one below is deprecated
            // String url_params = URLEncoder.encode(params[0], java.nio.charset.StandardCharsets.US_ASCII.name());
            // String url_params = URLEncoder.encode(values[0]);
            // URL url = new URL(params[1] + url_params );
            URL url = new URL( values[0] );
            Log.d(TAG, "URL is: " + url);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if ( conn.getResponseCode() == HttpURLConnection.HTTP_OK ) {

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));
                result = br.readLine();

                conn.disconnect();

                // Process the whole JSON array
                sl = Sheets.getInstance();
                sl.setData(result);
                if (sl.getLength() == 0)
                    retMsg = "No data returned.";
            }
            else
            {
                // Handle any error
                retMsg = "Error " + conn.getResponseCode() + " returned from website.";
                Log.d( TAG, retMsg );
            }

        } catch (MalformedURLException e) {

            Log.d( TAG, "Malformed URL.");
            e.printStackTrace();
            retMsg = "Bad URL";
        } catch (IOException e) {

            Log.d( TAG, "IOException was thrown.");
            e.printStackTrace();
            retMsg = "IO Error occurred.";
        }

        if ( result != null ) {
            //Log.d(TAG, "result is" + result);
            Log.d(TAG, "result length is " + result.length());
        }
        return retMsg;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        b.setEnabled( false );
        pd = ProgressDialog.show( c, "Loading Spreadsheet Data ...", "Working" );
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if ( s == null ) {

            lv.setAdapter( new ArrayAdapter<SheetObject>( c, R.layout.list_white, R.id.list_content, sl.getData() ));
            setListViewHeightBasedOnChildren(lv);
        }
        else {
            Toast toast = Toast.makeText( c, s, Toast.LENGTH_SHORT );
            toast.show();
        }


        b.setEnabled(true);
        pd.dismiss();
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
