package com.example.myapplicationtrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class findCityWeather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_city_weather);
    }



    public void getCity(View view) {
        final EditText edtCityName = findViewById(R.id.edtCityName);
        String newCityName = edtCityName.getText().toString();
        Intent intent = new Intent(this, CurrentWeather.class);
        intent.putExtra("location", newCityName);
        startActivity(intent);
        finish();
    }

}
