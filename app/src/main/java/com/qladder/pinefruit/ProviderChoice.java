package com.qladder.pinefruit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProviderChoice extends AppCompatActivity {
Button createNewbtn;
Button viewbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_choice);

        createNewbtn = (Button)findViewById(R.id.createbtn);
        viewbtn = (Button)findViewById(R.id.viewbtn);


        createNewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent newIntent = new Intent(ProviderChoice.this,MainActivity.class);
               startActivity(newIntent);
            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent = new Intent(ProviderChoice.this,SessionListListActivity.class);
                startActivity(viewIntent);
            }
        });


    }}
