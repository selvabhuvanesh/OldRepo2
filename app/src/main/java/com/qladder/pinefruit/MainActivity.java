package com.qladder.pinefruit;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.qladder.pinefruit.data.HomeFragment;
import com.qladder.pinefruit.data.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    Fragment selectedFragment = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_activity_search);

        BottomNavigationView bottomNavigationMenu = findViewById(R.id.bottomNavigationView);
        bottomNavigationMenu.setOnNavigationItemSelectedListener(navListener);
        bottomNavigationMenu.setSelectedItemId(R.id.nav_home);
        }

        private BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                        switch (item.getItemId())
                        {

                            case R.id.nav_home:
                                selectedFragment = new HomeFragment();
                                break;
                            case R.id.nav_notification:
                                selectedFragment = new HomeFragment();
                                break;

                                case R.id.nav_settings:
                            selectedFragment = new SettingsActivity();
                            break;


                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragmentcontainer,
                                selectedFragment).commit();

                         return true;
                    }
                };
}