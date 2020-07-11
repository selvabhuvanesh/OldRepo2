package com.qladder.pinefruit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Button proceed;
EditText facility;
EditText provider;
EditText service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proceed = (Button) findViewById(R.id.proceed);
        facility = (EditText) findViewById(R.id.Facility);
        service = (EditText) findViewById(R.id.Service);
        provider = (EditText) findViewById(R.id.Provider);


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent proceedIntent = new Intent(MainActivity.this,ScheduleActivity.class);
                proceedIntent.putExtra("facility",facility.getText().toString());
                proceedIntent.putExtra("service",service.getText().toString());
                proceedIntent.putExtra("provider",provider.getText().toString());
                startActivity(proceedIntent);
            }
        });

        }


}
