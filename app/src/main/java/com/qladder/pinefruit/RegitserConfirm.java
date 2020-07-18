package com.qladder.pinefruit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegitserConfirm extends AppCompatActivity {
    Button modifybtn;
    Button sharebtn;
    TextView providerName;
    TextView fromTime;
    TextView serviceName;
    TextView toTime;
    TextView sDate;
    TextView facility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regitser_confirm);

        modifybtn = (Button) findViewById(R.id.modify);
        sharebtn = (Button) findViewById(R.id.share);
        providerName = (TextView) findViewById(R.id.providername);
        facility = (TextView) findViewById(R.id.facility);
        fromTime = (TextView) findViewById(R.id.fromtime);
        toTime = (TextView) findViewById(R.id.totime);
        sDate = (TextView) findViewById(R.id.date);
        serviceName = (TextView) findViewById(R.id.service);

        final Intent saveIntent = getIntent();
        facility.setText("Facility : " + saveIntent.getStringExtra("facility"));
        serviceName.setText("Service : " + saveIntent.getStringExtra("service"));
        providerName.setText("Provider : " + saveIntent.getStringExtra("provider"));
        fromTime.setText("From Time : " + saveIntent.getStringExtra("fromtime"));
        toTime.setText("To Time : " + saveIntent.getStringExtra("totime"));
        sDate.setText("Date :  " + saveIntent.getStringExtra("sdate"));
       // sDate.setText("Lat :  " + saveIntent.getStringExtra("Latitude"));


        modifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent modifyIntent = new Intent(RegitserConfirm.this, ProviderInfo.class);
                startActivity(modifyIntent);
            }
        });

        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String appName = "PineFruit";
                //String message = "You can now book the consultation using Pine Fruit";
                String message = "Hi, The below consultation is ready for booking. Please use PineFruit app to book your slot " +
                        "\n ----------------------"+
                        "\n Facility : "+saveIntent.getStringExtra("facility")+
                        "\n Service : "+saveIntent.getStringExtra("service")+
                        "\n Provider : "+saveIntent.getStringExtra("provider")+
                        "\n Date : "+saveIntent.getStringExtra("sdate")+
                        "\n From Time : " + saveIntent.getStringExtra("fromtime")+
                        "\n To Time : " + saveIntent.getStringExtra("totime");

                intent.putExtra(Intent.EXTRA_SUBJECT,appName);
                intent.putExtra(Intent.EXTRA_TEXT,message);
                startActivity(Intent.createChooser(intent,"Share Using"));
            }
        });

    }


}