package com.qladder.pinefruit;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class UserSessionDetailScreen extends AppCompatActivity {

    private TextView name,org,session_name,addr,time,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_activity_detail_screen);

        name = findViewById(R.id.name);
        org = findViewById(R.id.org);
        session_name = findViewById(R.id.session_name);
        addr = findViewById(R.id.addr);
        time = findViewById(R.id.time);
        date = findViewById(R.id.date);

        Intent gotIntent = getIntent();
        name.setText(gotIntent.getStringExtra("providerName"));
        org.setText(gotIntent.getStringExtra("providerOrg"));
        session_name.setText(gotIntent.getStringExtra("sessionName"));
        time.setText(gotIntent.getStringExtra("mFromTime")+" - "+gotIntent.getStringExtra("mToTime"));
        date.setText(gotIntent.getStringExtra("mDate").substring(4,10));

        LatLng latLng = new LatLng(Double.parseDouble(gotIntent.getStringExtra("providerLatitude")), Double.parseDouble(gotIntent.getStringExtra("providerLongitude")));
        getAddress(latLng);

        findViewById(R.id.appointment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserSessionDetailScreen.this, "Appointment Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getAddress(LatLng latLng) {
        Geocoder geocoder = new Geocoder(UserSessionDetailScreen.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses.size()>0){
                Address obj = addresses.get(0);
               addr.setText(obj.getAddressLine(0));
            }
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
