package com.qladder.pinefruit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ProviderInfo extends AppCompatActivity {
Button proceed;
EditText facility;
EditText provider;
EditText service;
FusedLocationProviderClient mFusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proceed = (Button) findViewById(R.id.proceed);
        facility = (EditText) findViewById(R.id.facility);
        service = (EditText) findViewById(R.id.Service);
        provider = (EditText) findViewById(R.id.Provider);

        Toast.makeText(ProviderInfo.this,"Toast Top",Toast.LENGTH_LONG).show();

        //setting default values for provider type dropdown list - this should be fetched from DB during first load
        String categories[] = {"Select Type", "Restaurant", "Hospital", "Fitness Center", "Community"};
        Spinner test = findViewById(R.id.proType);
        ArrayAdapter<String> datadapter;
        datadapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categories);
        test.setAdapter(datadapter);

        //get the current location of the provider---------Starts Here--------------------------
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(ProviderInfo.this);

        Toast.makeText(ProviderInfo.this,"Toast 1",Toast.LENGTH_LONG).show();

        if (ActivityCompat.checkSelfPermission(ProviderInfo.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            mFusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location loc = task.getResult();
                    if (loc != null)
                    {
                        try {
                            Geocoder geo = new Geocoder(ProviderInfo.this, Locale.getDefault());

                            List<Address> addresses = geo.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                            Toast.makeText(ProviderInfo.this, "inside toast value"+addresses.get(0).getLocality(), Toast.LENGTH_LONG).show();
                        }catch (IOException e)
                        {
                            e.printStackTrace();
                        }


                    }
                }
            });
        } else {
            //Toast.makeText(ProviderInfo.this, "No Access Toast Called", Toast.LENGTH_LONG).show();

            ActivityCompat.requestPermissions(ProviderInfo.this
                    , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }


        //get the current location of the provider---------End Here--------------------------

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent proceedToScheduleIntent = new Intent(ProviderInfo.this, ScheduleActivity.class);
                proceedToScheduleIntent.putExtra("facility", facility.getText().toString());
                proceedToScheduleIntent.putExtra("service", service.getText().toString());
                proceedToScheduleIntent.putExtra("provider", provider.getText().toString());
                startActivity(proceedToScheduleIntent);
            }
        });


    }

    private void getLocation() {

    }

}
