package com.qladder.pinefruit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SessionList extends AppCompatActivity {
Button changebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_list);

    changebtn = (Button)findViewById(R.id.changebtn);
    changebtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent changeIntent = new Intent(SessionList.this, ProviderInfo.class);
            startActivity(changeIntent);
            Log.d("sssssssssssssss------>","sdfsdfsdf");
        }
    });



    }


}
