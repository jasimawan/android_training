package com.example.myapplicationtrain;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class CurrentWeather extends AppCompatActivity {

    String[] days = new String[7];
    String[] temperatures = new String[7];
    String[] condition = new String[7];
    DetailsData[] extraDetails = new DetailsData[7];


    private MyAdapter mAdapter;
    private RecyclerView rv;

    String cityName, countryName, unit = "";

    TextView mainTemp, mainDay, mainCondition, location;

    ImageView mainImage;

    ImageButton setting,refresh;

    Boolean refreshClicked = false;

    JSONObject data = null;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);
        Intent intent = getIntent();
        unit = intent.getStringExtra("unit");
        cityName = intent.getStringExtra("city");

        Toast.makeText(getApplicationContext(), cityName, Toast.LENGTH_SHORT).show();


        mainTemp = (TextView) findViewById(R.id.mainTemperature);
        mainDay = (TextView)findViewById(R.id.mainDay);
        location = (TextView)findViewById(R.id.location);
        mainCondition =(TextView)findViewById(R.id.mainCondition);

        mainImage = (ImageView)findViewById(R.id.mainImage);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        rv = (RecyclerView)findViewById(R.id.recyclerView);

        setting = (ImageButton)findViewById(R.id.settingsButton);
        refresh = (ImageButton)findViewById(R.id.refreshButton);

        progressBar.setVisibility(View.VISIBLE);
        mainImage.setVisibility(View.GONE);
        setting.setVisibility(View.GONE);
        refresh.setVisibility(View.GONE);

        FetchWeather(cityName);

        setDays();



        refreshClicked = false;
    }


    public void FetchWeather(final String city) {

        new AsyncTask<Void, Void, Void>() {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {

                    URL url = new URL("https://api.apixu.com/v1/forecast.json?key=c5b83a2d91de4121b4e60859190409&q="+city+"&days=7");
                    Log.e("Error", String.valueOf(url));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    StringBuffer json = new StringBuffer(1024);
                    String tmp = "";

                    while((tmp = reader.readLine()) != null)
                        json.append(tmp).append("\n");

                    reader.close();

                    data = new JSONObject(json.toString());



                } catch (Exception e) {

                    //Toast.makeText(getApplicationContext(), "asd", Toast.LENGTH_LONG).show();

                }

                return null;
            }

            @Override
            protected void onPostExecute(Void Void) {
                if(data!=null){
                    try{

                        cityName = data.getJSONObject("location").getString("name");
                        countryName = data.getJSONObject("location").getString("country");

                        Log.d("unit", unit);

                        if(unit.equals("c")){
                            for(int i=0; i< temperatures.length; i++){
                                temperatures[i] = data.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(i).getJSONObject("day").getString("avgtemp_c")+"°C";
                                Log.e("Error", temperatures[i]);
                            }
                        }

                        else{
                            for(int i=0; i< temperatures.length; i++){
                                temperatures[i] = data.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(i).getJSONObject("day").getString("avgtemp_f")+"°F";
                                Log.e("Error", temperatures[i]);
                            }
                        }

                        for(int i=0; i< condition.length; i++){
                            condition[i] = data.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(i).getJSONObject("day").getJSONObject("condition").getString("text");
                            Log.e("Error", condition[i]);
                        }

                        for(int i=0; i< extraDetails.length; i++){

                            String date = data.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(i).getString("date");

                            String maxtemp_c = data.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(i).getJSONObject("day").getString("maxtemp_c");
                            String mintemp_c = data.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(i).getJSONObject("day").getString("mintemp_c");;

                            String maxwind_mph = data.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(i).getJSONObject("day").getString("maxwind_mph");
                            String maxwind_kph = data.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(i).getJSONObject("day").getString("maxwind_kph");

                            String totalprecip_mm = data.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(i).getJSONObject("day").getString("totalprecip_mm");
                            String totalprecip_in = data.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(i).getJSONObject("day").getString("totalprecip_in");

                            String avgvis_km = data.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(i).getJSONObject("day").getString("avgvis_km");
                            String avgvis_miles = data.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(i).getJSONObject("day").getString("avgvis_miles");

                            String avghumidity = data.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(i).getJSONObject("day").getString("avghumidity");

                            String sunrise = data.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(i).getJSONObject("astro").getString("sunrise");
                            String sunset = data.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(i).getJSONObject("astro").getString("sunset");

                            Log.e("Error", date);
                            Log.e("Error", maxtemp_c);
                            Log.e("Error", mintemp_c);
                            Log.e("Error", maxwind_mph);
                            Log.e("Error", maxwind_kph);
                            Log.e("Error", totalprecip_mm);
                            Log.e("Error", totalprecip_in);
                            Log.e("Error", avgvis_km);
                            Log.e("Error", avgvis_miles);
                            Log.e("Error", avghumidity);
                            Log.e("Error", sunrise);
                            Log.e("Error", sunset);

                            DetailsData detailedData = new DetailsData(date, maxtemp_c, mintemp_c, maxwind_mph, maxwind_kph, totalprecip_mm, totalprecip_in, avgvis_km, avgvis_miles, avghumidity, sunrise, sunset);

                            extraDetails[i] = detailedData;
                        }

                        setMainPanel();

                        mAdapter = new MyAdapter(days,temperatures,condition);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        rv.setLayoutManager(mLayoutManager);
                        rv.setItemAnimator(new DefaultItemAnimator());
                        rv.setAdapter(mAdapter);


                        for (int i =0 ;i<days.length; i++){
                            Log.e("Error", days[i]);
                        }





                    }

                    catch(Exception e){


                    }
                }

            }
        }.execute();

    }

    void setMainPanel(){
        mainCondition.setText(condition[0]);
        mainTemp.setText(temperatures[0]);

        location.setText(cityName + ", " + countryName);

        String conditionIcon= condition[0]; // conditionIcon = Partly cloudy, Light drizzle, etc
        conditionIcon = conditionIcon.replaceAll("\\s","").toLowerCase(); //Partlycloudy -> partlycloudy
        Log.d("cond", conditionIcon);

        progressBar.setVisibility(View.GONE);
        mainImage.setVisibility(View.VISIBLE);
        mainDay.setVisibility(View.VISIBLE);
        setting.setVisibility(View.VISIBLE);
        refresh.setVisibility(View.VISIBLE);

        setIcon(conditionIcon, mainImage);

    }

    void setDays(){

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        String currentDay = (String) DateFormat.format("EEEE", today);

        days[0] = currentDay;

        for(int i = 1; i <= 6; i++){
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            Date tomorrow = calendar.getTime();
            String dayOfTheWeek = (String) DateFormat.format("EEEE", tomorrow);
            days[i] = dayOfTheWeek;
        }

        mainDay.setText(currentDay);

        mainDay.setVisibility(View.GONE);

    }

    public void setIcon(String condition, ImageView image){

        switch(condition){
            case "blizzard":
                image.setImageResource(R.drawable.blizzard);
                break;
            case "cloudy":
                image.setImageResource(R.drawable.cloudy);
                break;
            case "lightdrizzle":
                image.setImageResource(R.drawable.lightdrizzle);
                break;
            case "mist":
                image.setImageResource(R.drawable.blizzard);
                break;
            case "lightrain":
                image.setImageResource(R.drawable.lightdrizzle);
                break;
            case "overcast":
                image.setImageResource(R.drawable.overcast);
                break;
            case "partlycloudy":
                image.setImageResource(R.drawable.partlycloudy);
                break;
            case "patchyrainpossible":
                image.setImageResource(R.drawable.patchrain);
                break;
            case "rain":
                image.setImageResource(R.drawable.rain);
                break;
            case "moderaterain":
                image.setImageResource(R.drawable.rain);
                break;
            case "storm":
                image.setImageResource(R.drawable.storm);
                break;
            case "sunny":
                image.setImageResource(R.drawable.sunny);
                break;
            case "heavysnow":
                image.setImageResource(R.drawable.snow);
                break;
            case "lightsnow":
                image.setImageResource(R.drawable.snow);
                break;
            default:
                image.setImageResource(R.drawable.stock);

        }
    }

    void setIntentData(Intent intent, int i){

        intent.putExtra("day",days[i+1]);

        intent.putExtra("date",extraDetails[i+1].date);

        intent.putExtra("mintemp_c",extraDetails[i+1].mintemp_c);
        intent.putExtra("maxtemp_c",extraDetails[i+1].maxtemp_c);

        intent.putExtra("maxwind_mph",extraDetails[i+1].maxwind_mph);
        intent.putExtra("maxwind_kph",extraDetails[i+1].maxwind_kph);

        intent.putExtra("totalprecip_mm",extraDetails[i+1].totalprecip_mm);
        intent.putExtra("totalprecip_in",extraDetails[i+1].totalprecip_in);

        intent.putExtra("avgvis_km",extraDetails[i+1].avgvis_km);
        intent.putExtra("avgvis_miles",extraDetails[i+1].avgvis_miles);

        intent.putExtra("avghumidity",extraDetails[i+1].avghumidity);

        intent.putExtra("sunrise",extraDetails[i+1].sunrise);
        intent.putExtra("sunset",extraDetails[i+1].sunset);
    }

    public void launchDetailsActivity(View view) {

        Intent intent = new Intent(this, weatherDetails.class);

        setIntentData(intent, -1);

        startActivity(intent);
    }


    public void locationClicked(View view) {
        startActivity(new Intent(this, settingsActivity.class));
    }

    public void refreshClicked(View view) {
        refreshClicked = true;
        onStart();
        Toast.makeText(this, "Data refreshed!", Toast.LENGTH_SHORT).show();
    }

    public void settingsClicked(View view){

        startActivity(new Intent(this, settingsActivity.class));

    }


}

