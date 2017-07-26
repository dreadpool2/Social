package com.example.sanyogghosh.social;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class PhoneNumbers extends AppCompatActivity {
    private PhoneNumberAdapter mAdapter;
    private int variable=0;
    private int vari[]= new int[10];
    private ArrayList<Integer> numbers = new ArrayList<Integer>();
    private int a=0;
    private int y,za=0;
    public  int position=0;
    public TextView shower;
    public LinearLayout aak;
    protected int i,j =0;

    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_numbers);
        ProgressBar aloo  = (ProgressBar)findViewById(R.id.progress2);
        aloo.setVisibility(View.VISIBLE);
        final ListView a = (ListView)findViewById(R.id.phone_list);
        mAdapter = new PhoneNumberAdapter(this, new ArrayList<PhoneEvent>());
        shower= (TextView)findViewById(R.id.show_er);
        aak = (LinearLayout)findViewById(R.id.goforgood);

        a.setAdapter(mAdapter);
        a.setDivider(null);
        PhoneNumber ph = new PhoneNumber();
        ph.execute("https://sheets.googleapis.com/v4/spreadsheets/1ubkMsI0vpREGoHpPlU3Rps-JBex88sFr08UG2oeyozU/values/Sheet1!A1:E110?key=AIzaSyBa8lxt2dSjV5aw9RkU0uh38h6jYCI9mm8");

        final TextView a1 = (TextView)findViewById(R.id.administration);
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.clearFocus();
                aak = (LinearLayout)findViewById(R.id.goforgood);

                a.setSelection(vari[0]);
                aak.setVisibility(View.GONE);
                a.setSelection(vari[0]);
                a.post(new Runnable() {
                    @Override
                    public void run() {
                        a.setSelection(vari[0]);

                    }
                });

            }
        });
        final TextView a2 = (TextView)findViewById(R.id.deans);
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.clearFocus();
                aak = (LinearLayout)findViewById(R.id.goforgood);

                a.setSelection(vari[1]);
                aak.setVisibility(View.GONE);

                a.setSelection(vari[1]);
                a.post(new Runnable() {
                    @Override
                    public void run() {
                        a.setSelection(vari[1]);

                    }
                });

            }
        });
        final TextView a3 = (TextView)findViewById(R.id.associate_deans);
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aak = (LinearLayout)findViewById(R.id.goforgood);
                a.clearFocus();

                aak.setVisibility(View.GONE);
                a.post(new Runnable() {
                    @Override
                    public void run() {
                        a.setSelection(vari[2]-1);

                    }
                });


            }
        });
        final TextView a4 = (TextView)findViewById(R.id.associate_deans_Off_Campus);
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.clearFocus();
                a.setSelection(vari[3]-2);
                aak.setVisibility(View.GONE);
                a.post(new Runnable() {
                    @Override
                    public void run() {
                        a.setSelection(vari[3]-2);

                    }
                });

            }
        });
        final TextView a5 = (TextView)findViewById(R.id.unit_chiefs);
        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.clearFocus();
                a.setSelection(vari[4]-2);
                aak.setVisibility(View.GONE);

                a.post(new Runnable() {
                    @Override
                    public void run() {
                        a.setSelection(vari[4]-2);

                    }
                });


            }
        });
        final TextView a6 = (TextView)findViewById(R.id.faculty_in_charge);
        a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.clearFocus();

                a.setSelection(vari[5]-2);
                aak.setVisibility(View.GONE);

                a.post(new Runnable() {
                    @Override
                    public void run() {
                        a.setSelection(vari[5]-2);

                    }
                });


            }
        });
        final TextView a7 = (TextView)findViewById(R.id.heads_of_Departments);
        a7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.clearFocus();

                a.setSelection(vari[6]-2);
                aak.setVisibility(View.GONE);

                a.post(new Runnable() {
                    @Override
                    public void run() {
                        a.setSelection(vari[6]-2);

                    }
                });


            }
        });
        final TextView a8 = (TextView)findViewById(R.id.wardens);
        a8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.clearFocus();

                a.setSelection(vari[7]-2);
                aak.setVisibility(View.GONE);

                a.post(new Runnable() {
                    @Override
                    public void run() {
                        a.setSelection(vari[7]-2);

                    }
                });


            }
        });
        final TextView a9 = (TextView)findViewById(R.id.centre_specific_contacts);
        a9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.clearFocus();

                a.setSelection(vari[8]-2);
                aak.setVisibility(View.GONE);

                a.post(new Runnable() {
                    @Override
                    public void run() {
                        a.setSelection(vari[8]-2);

                    }
                });


            }
        });
        final TextView a10 = (TextView)findViewById(R.id.issue_specific_contacts);
        a10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.clearFocus();

                a.setSelection(vari[9]-2);
                aak.setVisibility(View.GONE);

                a.post(new Runnable() {
                    @Override
                    public void run() {
                        a.setSelection(vari[9]-2);

                    }
                });

            }
        });


        shower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aak.setVisibility(View.VISIBLE);
                LinearLayout aa = (LinearLayout)findViewById(R.id.godown);
                aa.setVisibility(View.VISIBLE);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        a.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int mLastFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {



            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {



                if(mLastFirstVisibleItem<firstVisibleItem)
                {
                    za=0;
                    if(y==0) {
                        LinearLayout options = (LinearLayout) findViewById(R.id.godown);
                        Animation a = AnimationUtils.loadAnimation(PhoneNumbers.this, R.anim.abc_slide_out_bottom);
                        options.startAnimation(a);
                        options.setVisibility(View.GONE);
                    }

                    Log.i("SCROLLING DOWN","TRUE");
                    y++;
                }
                if(mLastFirstVisibleItem>firstVisibleItem)

                {  y=0;

                    if(za==0) {
                        LinearLayout options = (LinearLayout) findViewById(R.id.godown);
                        options.setVisibility(View.VISIBLE);
                        Animation a = AnimationUtils.loadAnimation(PhoneNumbers.this, R.anim.abc_slide_in_bottom);
                        options.startAnimation(a);
                    }


                    Log.i("SCROLLING UP","TRUE");
                    za++;
                }
             position++;


              if(position!=0) {
                  int an = a.getFirstVisiblePosition();
                  

                  a1.setTextColor(Color.parseColor("#000000"));

                  a2.setTextColor(Color.parseColor("#000000"));

                  a3.setTextColor(Color.parseColor("#000000"));

                  a4.setTextColor(Color.parseColor("#000000"));

                  a5.setTextColor(Color.parseColor("#000000"));

                  a6.setTextColor(Color.parseColor("#000000"));

                  a7.setTextColor(Color.parseColor("#000000"));


                  a8.setTextColor(Color.parseColor("#000000"));

                  a9.setTextColor(Color.parseColor("#000000"));

                  a10.setTextColor(Color.parseColor("#000000"));

                  if(vari[0]<=an&&an<vari[1]){
                  //if (a.name.equals("Administrative Contacts")) {
                      shower.setText(a1.getText().toString());
                      a1.setTextColor(Color.parseColor("#FF03B40C"));
                 // }
                  }
                  //if (a.name.equals("Deans")) {
                  if(vari[1]<=an&&an<(vari[2]-1)){
                      //if (a.name.equals("Administrative Contacts")) {
                      shower.setText(a2.getText().toString());
                      a2.setTextColor(Color.parseColor("#FF03B40C"));
                      // }
                  }
                  // }

                  // if (a.name.equals("Associate Deans")) {

                  if((vari[2]-1)<=an&&an<(vari[3]-2)){
                      //if (a.name.equals("Administrative Contacts")) {
                      shower.setText(a3.getText().toString());
                      a3.setTextColor(Color.parseColor("#FF03B40C"));
                      // }
                  }                  //}

                  //if (a.name.equals("Professor in Charge")) {
                  if((vari[3]-2)<=an&&an<(vari[4]-2)){
                      //if (a.name.equals("Administrative Contacts")) {
                      shower.setText(a4.getText().toString());
                      a4.setTextColor(Color.parseColor("#FF03B40C"));
                      // }
                  }
                  //}

                  //if (a.name.equals("Faculty in Charge(s)")) {
                  if((vari[4]-2)<=an&&an<(vari[5]-2)){
                      //if (a.name.equals("Administrative Contacts")) {
                      shower.setText(a5.getText().toString());

                      a5.setTextColor(Color.parseColor("#FF03B40C"));
                      // }
                  }                      //}


                  // if (a.name.equals("Head of Department")) {
                  if((vari[5]-2)<=an&&an<(vari[6]-2)){
                      //if (a.name.equals("Administrative Contacts")) {
                      shower.setText(a6.getText().toString());

                      a6.setTextColor(Color.parseColor("#FF03B40C"));
                      // }
                  }                  //}
                  //if (a.name.equals("Centre-Specific Contacts")) {
                  if((vari[6]-2)<=an&&an<(vari[7]-2)){
                      //if (a.name.equals("Administrative Contacts")) {
                      a7.setTextColor(Color.parseColor("#FF03B40C"));
                      shower.setText(a7.getText().toString());

                      // }
                  }                  // }
                // if (a.name.equals("Incharge for General Functions")) {
                  if((vari[7]-2)<=an&&an<(vari[8]-2)){
                      //if (a.name.equals("Administrative Contacts")) {
                      shower.setText(a8.getText().toString());

                      a8.setTextColor(Color.parseColor("#FF03B40C"));
                      // }
                  }                  // }
                  // if (a.name.equals("Hostel Wardens")) {
                  if((vari[8]-2)<=an&&an<(vari[9]-2)){
                      //if (a.name.equals("Administrative Contacts")) {
                      shower.setText(a9.getText().toString());

                      a9.setTextColor(Color.parseColor("#FF03B40C"));
                      // }
                  }                  // }
                  // if (a.name.equals("Issue-Specific Contacts")) {
                  if((vari[9]-2)<=an){
                      //if (a.name.equals("Administrative Contacts")) {
                      shower.setText(a10.getText().toString());

                      a10.setTextColor(Color.parseColor("#FF03B40C"));
                      // }
                  }                  // }
              }
                mLastFirstVisibleItem=firstVisibleItem;

            }
        });


    }


    /** Sample JSON response for a USGS query */
    /**
     * Create a private constructor because no one should ever create a {@link } object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */


    public final String LOG_TAG = "Not Working!";

    /**
     * Return a list of {@link PhoneEvent} objects that has been built up from
     * parsing a JSON response.
     */

    /**
     * Returns new URL object from the given string URL.
     */
    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(20000 /* milliseconds */);
            urlConnection.setConnectTimeout(30000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the phone number results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link PhoneEvent} objects that has been built up from
     * parsing the given JSON response.
     */
    public List<PhoneEvent> extractFeatureFromJson(String earthquakeJSON) {
        // If the JSON string is empty or null, then return early.


        if (TextUtils.isEmpty(earthquakeJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        List<PhoneEvent> earthquakes = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);

            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or earthquakes).
            JSONArray earthquakeArray = baseJsonResponse.getJSONArray("values");

            // For each earthquake in the earthquakeArray, create an {@link Earthquake} object
            for (int i = 0; i < earthquakeArray.length(); i++) {

                // Get a single earthquake at position i within the list of earthquakes
                JSONArray currentEarthquake = earthquakeArray.getJSONArray(i);
               if(i==0){
                   if(currentEarthquake.length()==3){

                       String name = currentEarthquake.getString(0);
                       String name_purified = name.replaceAll("\\*","");
                       String ph1 = currentEarthquake.getString(1).replaceAll("\\*","");
                       String ph2 = currentEarthquake.getString(2).replaceAll("\\*","");

                       PhoneEvent earthquake = new PhoneEvent("Administrative Contacts" ,Html.fromHtml("&#127970;")+ ph1, Html.fromHtml("&#128241")+ph2);
                       vari[0]=i;
                       earthquakes.add(earthquake);



                   }
               }
               else {
                    if (currentEarthquake.getString(0).equals("Divider")) {
                        if (currentEarthquake.length() == 3) {
                            variable = 0;
                            // name = currentEarthquake.getString(0);
                            a++;


                        }
                    }
//dependent on a -> the name (its easy)!!
                    if (variable == 1) {
                        String name=currentEarthquake.getString(0);
                            vari[a]=i;

                            name = name(a);

                        if (currentEarthquake.length() == 1) {
                            // name = currentEarthquake.getString(0);
                            String name_purified = name.replaceAll("\\*", "");

                            PhoneEvent earthquake = new PhoneEvent(name_purified, "", "");
                            earthquakes.add(earthquake);

                        }
                        if (currentEarthquake.length() == 3) {

                            // name = currentEarthquake.getString(0);
                            String name_purified = name.replaceAll("\\*", "");
                            String ph1 = currentEarthquake.getString(1).replaceAll("\\*", "");
                            String ph2 = currentEarthquake.getString(2).replaceAll("\\*", "");

                            PhoneEvent earthquake = new PhoneEvent(name_purified, Html.fromHtml("&#127970;") + ph1, Html.fromHtml("&#128241") + ph2);

                            earthquakes.add(earthquake);


                        }
                        if (currentEarthquake.length() == 4) {

                            // name = currentEarthquake.getString(0);
                            String name_purified = name.replaceAll("\\*", "");
                            String ph1 = currentEarthquake.getString(1).replaceAll("\\*", "");
                            String ph2 = currentEarthquake.getString(2).replaceAll("\\*", "") + Html.fromHtml("<br>&#127970;" + currentEarthquake.getString(3).replaceAll("\\*", ""));


                            PhoneEvent earthquake = new PhoneEvent(name_purified, ph1, ph2);

                            earthquakes.add(earthquake);


                        }
                        if (currentEarthquake.length() == 5) {

                            // name = currentEarthquake.getString(0);
                            String name_purified = name.replaceAll("\\*", "");
                            String ph1 = currentEarthquake.getString(1).replaceAll("\\*", "");
                            String ph2 = currentEarthquake.getString(2).replaceAll("\\*", "") + Html.fromHtml("<br>&#127970;" + currentEarthquake.getString(3).replaceAll("\\*", "") + "<br>&#128241;" + currentEarthquake.getString(4).replaceAll("\\*", ""));


                            PhoneEvent earthquake = new PhoneEvent(name_purified, ph1, ph2);

                            earthquakes.add(earthquake);


                        }


                    } else {

                        if (currentEarthquake.length() == 1) {
                            String name = currentEarthquake.getString(0);

                            String name_purified = name.replaceAll("\\*", "");

                            PhoneEvent earthquake = new PhoneEvent(name_purified, "", "");
                            earthquakes.add(earthquake);

                        }
                        if (currentEarthquake.length() == 3) {

                            String name = currentEarthquake.getString(0);
                            String name_purified = name.replaceAll("\\*", "");
                            String ph1 = currentEarthquake.getString(1).replaceAll("\\*", "");
                            String ph2 = currentEarthquake.getString(2).replaceAll("\\*", "");

                            PhoneEvent earthquake = new PhoneEvent(name_purified, Html.fromHtml("&#127970;") + ph1, Html.fromHtml("&#128241") + ph2);

                            earthquakes.add(earthquake);


                        }
                        if (currentEarthquake.length() == 4) {

                            String name = currentEarthquake.getString(0);
                            String name_purified = name.replaceAll("\\*", "");
                            String ph1 = currentEarthquake.getString(1).replaceAll("\\*", "");
                            String ph2 = currentEarthquake.getString(2).replaceAll("\\*", "") + Html.fromHtml("<br>&#127970;" + currentEarthquake.getString(3).replaceAll("\\*", ""));


                            PhoneEvent earthquake = new PhoneEvent(name_purified, ph1, ph2);

                            earthquakes.add(earthquake);


                        }
                        if (currentEarthquake.length() == 5) {

                            String name = currentEarthquake.getString(0);
                            String name_purified = name.replaceAll("\\*", "");
                            String ph1 = currentEarthquake.getString(1).replaceAll("\\*", "");
                            String ph2 = currentEarthquake.getString(2).replaceAll("\\*", "") + Html.fromHtml("<br>&#127970;" + currentEarthquake.getString(3).replaceAll("\\*", "") + "<br>&#128241;" + currentEarthquake.getString(4).replaceAll("\\*", ""));


                            PhoneEvent earthquake = new PhoneEvent(name_purified, ph1, ph2);

                            earthquakes.add(earthquake);


                        }


                    }

                   if(i>5){
                   variable++;

                   }
                }


                // Add the new {@link Earthquake} to the list of earthquakes.
            }
        } catch (JSONException e) {
           Log.e("Wow!", "Problem parsing the Phone JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

    public List<PhoneEvent> fetchEarthquakeData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<PhoneEvent> earthquakes = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return earthquakes;
    }


    public class PhoneNumber extends AsyncTask<String, Void, List<PhoneEvent>> {
        @Override
//#b75757 colour is the best for forecasts

        protected  List<PhoneEvent> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<PhoneEvent> result = fetchEarthquakeData(urls[0]);

            return result;

        }

        @Override
        protected void onPostExecute(List<PhoneEvent> data) {

            mAdapter.clear();

            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);

                ProgressBar aloo  = (ProgressBar)findViewById(R.id.progress2);
                aloo.setVisibility(View.GONE);


            }
        }
        // Clear the adapter of previous earthquake data




    }


    private String name(int h){
        String s="";

        switch(h){
            case 0:
                s="Administrative Contacts";
                break;
            case 1:
                s="Deans";
                break;

            case 2:
                s="Associate Deans";
                break;


            case 3:

               s="Professor in Charge";
                break;


            case 4:

                s="Faculty in Charge(s)";
                break;


            case 5:

                s="Head of Department";
                break;

            case 6:

                s="Centre-Specific Contacts";
                break;

            case 7:

                s="Incharge for General Functions";
                break;

            case 8:

                s="Hostel Wardens";
                break;

            case 9:

                s="Issue-Specific Contacts";
                break;



        }



        return s;
    }

    public void articles(View view){

        Intent i = new Intent(this, Articles.class);
        startActivity(i);

        overridePendingTransition(R.anim.slide_down,R.anim.slide_up);

        finish();

            }













    public class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener (Context ctx){
            gestureDetector = new GestureDetector(ctx, new GestureListener());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                            result = true;
                        }
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                        result = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

    public void onSwipeRight() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }
}

}