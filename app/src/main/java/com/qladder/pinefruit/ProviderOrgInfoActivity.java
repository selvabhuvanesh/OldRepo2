package com.qladder.pinefruit;

import android.content.Intent;
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
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker.IntentBuilder;

import static com.google.android.gms.location.places.ui.PlacePicker.getPlace;

public class ProviderOrgInfoActivity extends AppCompatActivity {
    Button proceedToSchedule;
    Button choseLocationBtn;
    EditText providerOrg;
    Intent proceedToScheduleIntent;
    double Latitude;
    double Longitude;
    String providerType;
    int PLACE_PICKER_REQUEST=1;
    Spinner providerTypeSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_info);

        proceedToSchedule = (Button) findViewById(R.id.proceed);
        providerOrg = (EditText) findViewById(R.id.providerOrg);
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
                    startActivityForResult(builder.build(ProviderOrgInfoActivity.this),PLACE_PICKER_REQUEST);
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
                proceedToScheduleIntent = new Intent(getApplicationContext(),SessionInfoActivity.class);
                String mproviderOrg = providerOrg.getText().toString().trim();


                   if (!(Latitude ==0.0 || mproviderOrg == null))
                    {

                    proceedToScheduleIntent.putExtra("providerOrg", mproviderOrg);
                    proceedToScheduleIntent.putExtra("providerLatitude",String.valueOf(Latitude).toString());
                    proceedToScheduleIntent.putExtra("providerLongitude",String.valueOf(Longitude).toString());
                    proceedToScheduleIntent.putExtra("providerType",providerType);
                    startActivity(proceedToScheduleIntent);

                    }
                   else
                    {
                    Toast.makeText(ProviderOrgInfoActivity.this,"Required \n------------ \nLocation",Toast.LENGTH_LONG).show();
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

            }

        }
    }
}
