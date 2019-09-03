package com.example.myapplicationtrain;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ShareLocation extends AppCompatActivity {
    String longitude, latitude, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_location);
        Intent intent = getIntent();
        longitude = intent.getStringExtra("longitude");
        latitude = intent.getStringExtra("latitude");
        address = getAddress(latitude,longitude);

        EditText latitudeText = (EditText) findViewById(R.id.latitude);
        latitudeText.setText(latitude);
        EditText longitudeText = (EditText) findViewById(R.id.longitude);
        longitudeText.setText(longitude);
        EditText addressText = (EditText) findViewById(R.id.address);
        addressText.setText(address);




    }


    @SuppressLint("LongLogTag")
    public String getAddress(String lat, String lon)
    {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lon), 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }


    public void shareLocation(View view){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,  address);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
