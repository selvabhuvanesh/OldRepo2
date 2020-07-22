package com.qladder.pinefruit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ProviderInfoActivity extends AppCompatActivity {
    Button proceed;
    Button choseBtn;
    EditText facility;
    EditText provider;
    EditText service;
    FusedLocationProviderClient mFusedLocationProviderClient;
    Intent proceedToScheduleIntent;
    double Latitude;
    double Longitude;
    String Locality;
    int PLACE_PICKER_REQUEST=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proceed = (Button) findViewById(R.id.proceed);
        facility = (EditText) findViewById(R.id.facilityName);
        service = (EditText) findViewById(R.id.serviceName);
        provider = (EditText) findViewById(R.id.providerName);
        choseBtn = (Button) findViewById(R.id.choseLocation);

        //setting default values for provider type dropdown list - this should be fetched from DB during first load
        String categories[] = {"Select Type", "Restaurant", "Hospital", "Fitness Center", "Community", "Coffee Shop", "Library", "Auto Service Station", "General Service", "Others"};
        Spinner providerTypeSpinner = findViewById(R.id.proType);
        ArrayAdapter datadapter;
        datadapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categories);
        providerTypeSpinner.setAdapter(datadapter);


        //This is to open the map and select the location that user wants
        choseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(ProviderInfoActivity.this),PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                };
            }
        });




        proceedToScheduleIntent = new Intent(ProviderInfoActivity.this, SessionScheduleActivity.class);

        //get the current location of the provider---------Starts Here--------------------------
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(ProviderInfoActivity.this);


       if (ActivityCompat.checkSelfPermission(ProviderInfoActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(ProviderInfoActivity.this
                        , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

        } else {
           mFusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
               @Override
               public void onComplete(@NonNull Task<Location> task) {
                   Location loc = task.getResult();
                   if (loc != null)
                   {
                       try {
                           Geocoder geo = new Geocoder(ProviderInfoActivity.this, Locale.getDefault());
                           List<Address> addresses = geo.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                         //  Toast.makeText(ProviderInfoActivity.this, "Your Location is\n" +
                          //         "------------------------------\n"+addresses.get(0).getLocality(), LENGTH_LONG).show();
                           Latitude = addresses.get(0).getLatitude();
                           Longitude = addresses.get(0).getLongitude();
                           Locality = addresses.get(0).getLocality();


                       }catch (IOException e)
                       {
                           e.printStackTrace();
                       }


                   }
               }
           });
        }


        //get the current location of the provider---------End Here--------------------------





        // Actions when proceed to schedule button is clicked. It checks for mandatory fields and moved to schedule screen
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mFacility = facility.getText().toString();
                String mService = service.getText().toString();
                String mProvider = provider.getText().toString();

                    if (!(mFacility.trim().isEmpty() || mService.trim().isEmpty()))
                    {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("ManObject");
                        String myId = myRef.push().getKey();
                        ProviderInfo providerInfo = new ProviderInfo(
                                myId,
                                mFacility,
                                mService,
                                Locality,
                                String.valueOf(Latitude).toString(),String.valueOf(Longitude).toString());
                        myRef.child(myId ).setValue(providerInfo);
                       // myRef.child( myRef.push().getKey()).setValue(providerInfo);
                    proceedToScheduleIntent.putExtra("facility", mFacility);
                    proceedToScheduleIntent.putExtra("service", mService);
                    proceedToScheduleIntent.putExtra("provider", mProvider);
                    proceedToScheduleIntent.putExtra("Latitude", Latitude);
                    proceedToScheduleIntent.putExtra("Longitude",Longitude);
                    proceedToScheduleIntent.putExtra("Locality", Locality);
                    startActivity(proceedToScheduleIntent);
                    }
                   else
                    {
                    Toast.makeText(ProviderInfoActivity.this,"Required \nCompany Name \n Service Name",Toast.LENGTH_LONG).show();
                    }
            }

        });


    }

    private void getLocation() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PLACE_PICKER_REQUEST)
        {if(resultCode == RESULT_OK)
            {
                Place  place = PlacePicker.getPlace(data,this);
                StringBuilder stringBuilder = new StringBuilder();
                String latitude = String.valueOf(place.getLatLng().latitude).toString();
                String longitude = String.valueOf(place.getLatLng().longitude).toString();
                //Locale address = place.getLocale();
                stringBuilder.append("Latitude -> "+latitude);
                stringBuilder.append("\n Longitude -> "+longitude);
                facility.setText("******");


            }
        }
    }
}
