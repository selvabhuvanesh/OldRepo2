package com.qladder.pinefruit;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.qladder.pinefruit.data.ProviderInfo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ProviderSessionInfoActivity extends AppCompatActivity {
    Button proceedToSchedule;
    Button choseLocationBtn;
    EditText providerOrg;
    EditText providerName;
    EditText sessionName;
    FusedLocationProviderClient mFusedLocationProviderClient;
    Intent proceedToScheduleIntent;
    double Latitude;
    double Longitude;
    String Locality;
    String city;
    String country;
    String status;
    String providerType;
    String providerID;
    int PLACE_PICKER_REQUEST=1;
    Spinner providerTypeSpinner;
    String userName;
    String userEmail;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_session_name_info);

        proceedToSchedule = (Button) findViewById(R.id.proceed);
        sessionName = (EditText) findViewById(R.id.serviceName);
        providerName = (EditText) findViewById(R.id.providerName);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(ProviderSessionInfoActivity.this);
        if(acct != null)
        {
             userName = acct.getDisplayName();
             userEmail = acct.getEmail();
             userID = acct.getId();

         //   Toast.makeText(this,"Inside the provider Name : "+personName,Toast.LENGTH_LONG).show();

        }

        // Actions when proceed to schedule button is clicked. It checks for mandatory fields and moved to schedule screen
        proceedToSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceedToScheduleIntent = new Intent(getApplicationContext(), ProviderSessionScheduleActivity.class);
                Intent providerInfoIntent = getIntent();
                String msessionName = sessionName.getText().toString();
                String mproviderName = providerName.getText().toString();
                if (!(mproviderName.trim().isEmpty() || msessionName.trim().isEmpty()))
                {
                String mproviderOrg = providerInfoIntent.getStringExtra("providerOrg");
                Latitude = new Double(providerInfoIntent.getStringExtra("providerLatitude"));
                Longitude = new Double(providerInfoIntent.getStringExtra("providerLongitude"));
                status = "Active";

                try {
                    Geocoder geo = new Geocoder(ProviderSessionInfoActivity.this, Locale.getDefault());
                    List<Address> addresses = geo.getFromLocation(Latitude, Longitude, 1);
                    Locality = addresses.get(0).getSubAdminArea();
                    city = addresses.get(0).getAdminArea();
                    country = addresses.get(0).getCountryName();
                    Toast.makeText(getApplicationContext(),"Locality is : "+Locality +
                            "\nCity is : "+city+
                            "\nCountry is : "+country,Toast.LENGTH_LONG).show();
                }catch (IOException e)
                {
                    e.printStackTrace();
                }



                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Provider");
                        providerID = myRef.push().getKey();
                        ProviderInfo providerInfo = new ProviderInfo(
                                providerID,
                                providerType,
                                mproviderOrg,
                                mproviderName,
                                Locality,
                                String.valueOf(Latitude).toString(),
                                String.valueOf(Longitude).toString(),
                                city,
                                country,
                                status,userEmail,userID,userName
                                );
                        myRef.child(providerID).setValue(providerInfo);
                       // myRef.child( myRef.push().getKey()).setValue(providerInfo);
                    proceedToScheduleIntent.putExtra("providerID", providerID);
                    proceedToScheduleIntent.putExtra("providerOrg", mproviderOrg);
                    proceedToScheduleIntent.putExtra("sessionName", msessionName);
                    proceedToScheduleIntent.putExtra("providerName",mproviderName);
                    proceedToScheduleIntent.putExtra("providerLatitude",String.valueOf(Latitude).toString());
                    proceedToScheduleIntent.putExtra("providerLongitude",String.valueOf(Longitude).toString());
                    proceedToScheduleIntent.putExtra("providerCountry",country);
                    proceedToScheduleIntent.putExtra("providerCity",city);
                    proceedToScheduleIntent.putExtra("providerLocality",Locality);



                   startActivity(proceedToScheduleIntent);
                   //finish();
                    }
                   else
                    {
                    Toast.makeText(ProviderSessionInfoActivity.this,"Required \n--------- \nService Name \nProvider Name",Toast.LENGTH_LONG).show();
                    }
            }

        });


    }




}
