package com.example.myapplicationtrain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;


import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

public class settingsActivity extends AppCompatActivity {
    EditText cityET;
    String unit="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        cityET = (EditText)findViewById(R.id.city);


        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(i == R.id.cButton)
                    unit = "c";

                if(i == R.id.fButton)
                    unit = "f";

            }
        });

    }


    public void saveCity(View view) {
        Intent intent = new Intent(this,CurrentWeather.class);
        intent.putExtra("city", cityET.getText().toString());
        intent.putExtra("unit", unit);
        startActivity(intent);
    }
}
