package com.qladder.pinefruit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProviderRegitserConfirmActivity extends AppCompatActivity {
    Button modifybtn;
    Button sharebtn;
    TextView providerName;
    TextView serviceName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regitser_confirm);

        sharebtn = (Button) findViewById(R.id.share);
        providerName = (TextView) findViewById(R.id.providername);
        serviceName = (TextView) findViewById(R.id.serviceName);

        final Intent saveIntent = getIntent();
        serviceName.setText("Service : " + saveIntent.getStringExtra("serviceName"));
        providerName.setText("Provider : " + saveIntent.getStringExtra("providerName"));

       // sDate.setText("Lat :  " + saveIntent.getStringExtra("Latitude"));




        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String appName = "PineFruit";
                //String message = "You can now book the consultation using Pine Fruit";
                String message = "Hi, The below consultation is ready for booking. Please use PineFruit app to book your slot " +
                        "\n ----------------------"+
                        "\n Service : "+saveIntent.getStringExtra("serviceName")+
                        "\n Provider : "+saveIntent.getStringExtra("providerName")+
                        "\n Date : "+saveIntent.getStringExtra("date")+
                        "\n From Time : " + saveIntent.getStringExtra("fromTime")+
                        "\n To Time : " + saveIntent.getStringExtra("toTime");

                intent.putExtra(Intent.EXTRA_SUBJECT,appName);
                intent.putExtra(Intent.EXTRA_TEXT,message);
                startActivity(Intent.createChooser(intent,"Share Using"));
            }
        });

    }


}