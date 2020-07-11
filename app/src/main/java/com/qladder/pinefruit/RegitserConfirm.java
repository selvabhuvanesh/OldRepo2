package com.qladder.pinefruit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegitserConfirm extends AppCompatActivity {
Button modifybtn;
Button sharebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regitser_confirm);

        modifybtn = (Button)findViewById(R.id.modify);
        sharebtn = (Button)findViewById(R.id.share);
        modifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent modifyIntent = new Intent(RegitserConfirm.this,MainActivity.class);
                startActivity(modifyIntent);
            }
        });

        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent modifyIntent = new Intent(RegitserConfirm.this,MainActivity.class);
                startActivity(modifyIntent);
            }
        });
    }



}
