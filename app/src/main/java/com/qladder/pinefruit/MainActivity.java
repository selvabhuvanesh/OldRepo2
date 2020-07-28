package com.qladder.pinefruit;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.qladder.pinefruit.data.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationMenu = findViewById(R.id.bottomNavigationView);
        bottomNavigationMenu.setOnNavigationItemSelectedListener(navListener);
        }

        private BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;

                        switch (item.getItemId())
                        {
                            case R.id.nav_home:
                                selectedFragment = new HomeFragment();
                                break;
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragmentcontainer,
                                selectedFragment).commit();

                         return true;
                    }
                };
}