package com.example.myapplicationtrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class nextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Intent intent = getIntent();
        String myText = intent.getStringExtra("myText");
        Toast toast = Toast.makeText(getApplicationContext(),
                myText,
                Toast.LENGTH_SHORT);

        toast.show();
    }
}
