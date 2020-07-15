package com.qladder.pinefruit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ProviderInfo extends AppCompatActivity {
Button proceed;
EditText facility;
EditText provider;
EditText service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proceed = (Button) findViewById(R.id.proceed);
        facility = (EditText) findViewById(R.id.facility);
        service = (EditText) findViewById(R.id.Service);
        provider = (EditText) findViewById(R.id.Provider);

        String categories[]={"Select Type","Restaurant","Hospital","Fitness Center", "Community"};
        Spinner test = findViewById(R.id.proType);
        ArrayAdapter<String> datadapter;
        datadapter = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,categories);
        test.setAdapter(datadapter);



        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent proceedIntent = new Intent(ProviderInfo.this,ScheduleActivity.class);
                proceedIntent.putExtra("facility",facility.getText().toString());
                proceedIntent.putExtra("service",service.getText().toString());
                proceedIntent.putExtra("provider",provider.getText().toString());
                startActivity(proceedIntent);
            }
        });

        }


}
