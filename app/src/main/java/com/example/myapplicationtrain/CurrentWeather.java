package com.example.myapplicationtrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.concurrent.ExecutionException;

public class CurrentWeather extends AppCompatActivity {

    class Weather extends AsyncTask<String, Void, String>{


        @Override
        protected String doInBackground(String... address){
            try {
                URL url = new URL(address[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                int data = isr.read();
                String content = "";
                char ch;
                while(data != -1){
                    ch = (char) data;
                    content = content + ch;
                    data = isr.read();
                }
                return content;
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);
        Intent intent = getIntent();
        String city = intent.getStringExtra("location");
        getWeather(city);
    }


    void getWeather(String loc){
        String content;
        Weather weather = new Weather();
        try {
            content = weather.execute("https://openweathermap.org/data/2.5/weather?q="+loc+"&appid=b6907d289e10d714a6e88b30761fae22").get();
            /*Toast toast = Toast.makeText(getApplicationContext(),
                    content,
                    Toast.LENGTH_LONG);
            toast.show();*/

            JSONObject jsonObject = new JSONObject(content);
            String weatherData = jsonObject.getString("weather");
            /*Toast toast = Toast.makeText(getApplicationContext(),
                    weatherData,
                    Toast.LENGTH_LONG);
            toast.show();*/
            JSONArray array = new JSONArray(weatherData);
            String main= "";
            String desc= "";
            String temp = "";
            String humidity = "";

            for(int i=0; i<array.length(); i++){
                JSONObject weatherPart = array.getJSONObject(i);
                main = weatherPart.getString("main");
                desc = weatherPart.getString("description");
            }
            String mainData = jsonObject.getString("main");
            JSONObject mainTemp = new JSONObject(mainData);
            temp = mainTemp.getString("temp");
            humidity = mainTemp.getString("humidity");

            TextView mainText = (TextView) findViewById(R.id.main);
            TextView descText = (TextView) findViewById(R.id.description);
            TextView tempText = (TextView) findViewById(R.id.temp);
            TextView humText = (TextView) findViewById(R.id.humidity);

            mainText.setText("Main: " +main);
            descText.setText("Description: " +desc);
            tempText.setText("Temperature: " +temp+ " °C");
            humText.setText("Humidity: " +humidity);



            /*Toast toast = Toast.makeText(getApplicationContext(),
                    main+desc,
                    Toast.LENGTH_LONG);
            toast.show();*/


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
