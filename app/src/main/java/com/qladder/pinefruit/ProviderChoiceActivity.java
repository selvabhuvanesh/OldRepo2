package com.qladder.pinefruit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProviderChoiceActivity extends AppCompatActivity {
Button createNewbtn;
Button viewbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_choice);

        createNewbtn = (Button)findViewById(R.id.createSessionbtn);
        viewbtn = (Button)findViewById(R.id.viewSessionbtn);

        // uncomment the below line to disable the view/modify button for production release
        //viewbtn.setEnabled(false);


        createNewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent newIntent = new Intent(ProviderChoiceActivity.this, ProviderInfoActivity.class);
               startActivity(newIntent);
               finish();
            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent = new Intent(ProviderChoiceActivity.this, SessionListActivity.class);
                startActivity(viewIntent);
            }
        });


    }
}
