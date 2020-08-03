package com.qladder.pinefruit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.qladder.pinefruit.data.ProviderInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SessionListActivity extends AppCompatActivity {

    List<SessionInfo> mSessionInfoList;
    ListView mListView;
    DatabaseReference db;
    DatabaseReference providerDBref;
    SessionInfo sessionInfo;
    DatabaseReference sessionDBref;
    ProviderInfo providerInfo;
    SearchListAdapter adapter;
    String country;
    String city;
    private FusedLocationProviderClient fusedLocationClient;


    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_session_list);
        mListView = (ListView) findViewById(R.id.listview);
        mSessionInfoList = new ArrayList<SessionInfo>();


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            ActivityCompat.requestPermissions(SessionListActivity.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},44);
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location != null) {
                            try {
                                city = "";
                                country = "";
                                Geocoder geo = new Geocoder(SessionListActivity.this, Locale.getDefault());
                                List<Address> addresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                //Locality = addresses.get(0).getLocality();
                                city = addresses.get(0).getSubAdminArea();
                                country = addresses.get(0).getCountryName();
                                Toast.makeText(SessionListActivity.this, "Inside default Location : " + city + country, Toast.LENGTH_LONG).show();
                                db = FirebaseDatabase.getInstance().getReference("Session").child(country).child(city);
                                db.addValueEventListener(new ValueEventListener() {
                                    // The below line commented is to be used for User view to see only records ready for booking
                                    //db.orderByChild("sessionStatus").equalTo("Booking").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot != null) {
                                            for (DataSnapshot ds : snapshot.getChildren()) {
                                                sessionInfo = new SessionInfo("", "", "", "", "", "", "", "", "", "", "", "");
                                                sessionInfo.sessionID = ds.child("sessionID").getValue().toString();
                                                sessionInfo.sessionStatus = ds.child("sessionStatus").getValue().toString();
                                                sessionInfo.mfromtime = ds.child("mfromtime").getValue().toString();
                                                sessionInfo.mtotime = ds.child("mtotime").getValue().toString();
                                                sessionInfo.sessionName = ds.child("sessionName").getValue().toString();
                                                mSessionInfoList.add(sessionInfo);
                                                adapter = new SearchListAdapter(getApplicationContext(), R.layout.row, mSessionInfoList);
                                                mListView.setAdapter(adapter);
                                            }
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                        // ERRRRRRRROR
                                        System.out.println("**************ONCANCALLED***********8");
                                    }


                                });

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });






        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "ITEM SELECTED IS  :  " + mSessionInfoList.get(i).getSessionID(), Toast.LENGTH_LONG).show();
                Intent providerInfoIntent = new Intent(SessionListActivity.this, ProviderInfoActivity.class);
                startActivity(providerInfoIntent);
            }
        });


    }


}
