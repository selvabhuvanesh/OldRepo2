package com.qladder.pinefruit;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.location.LocationManager.GPS_PROVIDER;
import static android.location.LocationManager.NETWORK_PROVIDER;

public class UserSearchActivity extends AppCompatActivity implements LocationListener{
    Button searchBtn;
    EditText searchText;
    public static LatLng latLng;
    private Address ADDRESS;
    private EditText search_et;
    private RecyclerView recyclerView;
    private FirebaseUser user;
    private DatabaseReference reference;
    boolean isGPSEnable = false;
    boolean isNetworkEnable = false;
    double latitude, longitude;
    private LocationManager locationManager;
    private android.location.Location location;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_activity_search);
        AccountManager am = AccountManager.get(this); // "this" references the current Context
        Account[] accounts = am.getAccountsByType("com.google");
        searchBtn = (Button) findViewById(R.id.next);
        searchText = (EditText) findViewById(R.id.symptoms_et);

        // uncomment the below line to disable the view/modify button for production release
        //viewbtn.setEnabled(false);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(UserSearchActivity.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            Toast.makeText(this, "Inside the provider Name : " + personName, Toast.LENGTH_LONG).show();


        }


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserSearchActivity.this,"Search Clicked",Toast.LENGTH_LONG).show();
                Intent newIntent = new Intent(UserSearchActivity.this, UserSessionListActivity.class);
                newIntent.putExtra("searchText", searchText.getText().toString().trim());
                startActivity(newIntent);

            }
        });


        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        fn_getlocation();

        //addDoctor();
    }


    private void fn_getlocation() {
        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        isGPSEnable = locationManager.isProviderEnabled(GPS_PROVIDER);
        isNetworkEnable = locationManager.isProviderEnabled(NETWORK_PROVIDER);

        if (!isGPSEnable && !isNetworkEnable) {

        } else {
            if (isNetworkEnable) {
                location = null;
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                locationManager.requestLocationUpdates(NETWORK_PROVIDER, 1000, 0, (LocationListener) this);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(NETWORK_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        getAddress(latitude, longitude);
                    }
                }

            }


            if (isGPSEnable) {
                location = null;
                locationManager.requestLocationUpdates(GPS_PROVIDER, 1000, 0, (LocationListener) this);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(GPS_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        getAddress(latitude, longitude);
                    }
                }
            }


        }

    }

    public void getAddress(double lat, double lng) {
        latLng = new LatLng(lat, lng);
        Geocoder geocoder = new Geocoder(UserSearchActivity.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            if (addresses.size() > 0) {
                Address obj = addresses.get(0);
                ADDRESS = obj;
                Log.d(TAG, "getAddress:name " + obj.getLocality());
                Log.d(TAG, "getAddress:country " + obj.getCountryName());
            }
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}