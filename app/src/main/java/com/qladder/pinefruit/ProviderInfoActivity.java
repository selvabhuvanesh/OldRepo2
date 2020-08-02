package com.qladder.pinefruit;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker.IntentBuilder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.qladder.pinefruit.data.ProviderInfo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.google.android.gms.location.places.ui.PlacePicker.getPlace;

public class ProviderInfoActivity extends AppCompatActivity {
    Button proceedToSchedule;
    Button choseLocationBtn;
    EditText providerOrg;
    EditText providerName;
    EditText serviceName;
    FusedLocationProviderClient mFusedLocationProviderClient;
    Intent proceedToScheduleIntent;
    double Latitude;
    double Longitude;
    String Locality;
    String city;
    String country;
    String status;
    String providerType;
    String providerId;
    int PLACE_PICKER_REQUEST=1;
    Spinner providerTypeSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_info);

        proceedToSchedule = (Button) findViewById(R.id.proceed);
        providerOrg = (EditText) findViewById(R.id.providerOrg);
        serviceName = (EditText) findViewById(R.id.serviceName);
        providerName = (EditText) findViewById(R.id.providerName);
        choseLocationBtn = (Button) findViewById(R.id.choseLocation);

        //setting default values for provider type dropdown list - this should be fetched from DB during first load
        String categories[] = {"Select Type", "Restaurant", "Hospital", "Fitness Center", "Community", "Coffee Shop", "Library", "Auto Service Station", "General Service", "Others"};
        providerTypeSpinner = findViewById(R.id.proType);

        final ArrayAdapter datadapter;
        datadapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categories);
        providerTypeSpinner.setAdapter(datadapter);

        providerTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                providerType = providerTypeSpinner.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //This is to open the map and select the location of the provider. this captures Lat, Long and the Locality
        choseLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentBuilder builder = new IntentBuilder();
                try {
                    startActivityForResult(builder.build(ProviderInfoActivity.this),PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                };
            }
        });












        // Actions when proceed to schedule button is clicked. It checks for mandatory fields and moved to schedule screen
        proceedToSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceedToScheduleIntent = new Intent(getApplicationContext(),SessionScheduleActivity.class);
                String mproviderOrg = providerOrg.getText().toString();
                String mserviceName = serviceName.getText().toString();
                String mproviderName = providerName.getText().toString();

                   if (!(mproviderName.trim().isEmpty() || mserviceName.trim().isEmpty() || Latitude ==0.0 ))
                    {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Provider");
                        providerId = myRef.push().getKey();
                        ProviderInfo providerInfo = new ProviderInfo(
                                providerId,
                                providerType,
                                mproviderOrg,
                                mproviderName,
                                Locality,
                                String.valueOf(Latitude).toString(),
                                String.valueOf(Longitude).toString(),
                                city,
                                country,
                                status
                                );
                        myRef.child(providerId ).setValue(providerInfo);
                       // myRef.child( myRef.push().getKey()).setValue(providerInfo);
                    proceedToScheduleIntent.putExtra("providerId", providerId);
                    proceedToScheduleIntent.putExtra("providerOrg", mproviderOrg);
                    proceedToScheduleIntent.putExtra("serviceName", mserviceName);
                    proceedToScheduleIntent.putExtra("providerName",mproviderName);
                    startActivity(proceedToScheduleIntent);
                   finish();
                    }
                  // else
                    {
                    Toast.makeText(ProviderInfoActivity.this,"Required \n--------- \nLocation \nService Name \nProvider Name",Toast.LENGTH_LONG).show();
                    }
            }

        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PLACE_PICKER_REQUEST)
        {if(resultCode == RESULT_OK)
            {
                Place  place = getPlace(data,this);
                Latitude = place.getLatLng().latitude;
                Longitude = place.getLatLng().longitude;
                status = "Active";

                try {
                    Geocoder geo = new Geocoder(ProviderInfoActivity.this, Locale.getDefault());
                    List<Address> addresses = geo.getFromLocation(Latitude, Longitude, 1);
                    Locality = addresses.get(0).getLocality();
                    city = addresses.get(0).getSubAdminArea();
                    country = addresses.get(0).getCountryName();
                    Toast.makeText(this,"Locality is : "+Locality +
                            "\nCity is : "+city+
                            "\nCountry is : "+country,Toast.LENGTH_LONG).show();
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
    }
}
