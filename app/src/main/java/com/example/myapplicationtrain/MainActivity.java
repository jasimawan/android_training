package com.example.myapplicationtrain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_FROM_GALLERY = 1;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION =1  ;
    boolean mLocationPermissionGranted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void nextActivity(View view) {
        Intent newActivity = new Intent(this, nextActivity.class);
        EditText editText  = (EditText) findViewById(R.id.myText);
        String myText = editText.getText().toString();
        newActivity.putExtra("myText", myText);
        startActivity(newActivity);

    }

    public void getLocation(View view) {
        Intent mapIntent = new Intent(this, MapsActivity.class);
        startActivity(mapIntent);
    }

    public void getWeather(View view) {
        Intent weather = new Intent(this, findCityWeather.class);
        startActivity(weather);
    }

    public void getDirection(View view) {
        Intent mapsDirection = new Intent(this, MapsDirectionActivity.class);
        startActivity(mapsDirection);
    }
}

