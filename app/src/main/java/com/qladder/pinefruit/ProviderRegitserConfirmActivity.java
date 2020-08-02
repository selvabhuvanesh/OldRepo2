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
    TextView providerNameText;
    TextView serviceNameText;
    TextView providerOrgText;
    TextView mfromTimeText;
    TextView mtoTimeText;
    TextView mDateText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regitser_confirm);

        sharebtn = (Button) findViewById(R.id.shareBtn);
        providerNameText = (TextView) findViewById(R.id.providerNameText);
        serviceNameText = (TextView) findViewById(R.id.serviceNameText);
        providerOrgText = (TextView) findViewById(R.id.providerOrgText);
        mDateText = (TextView) findViewById(R.id.dateText);
        mfromTimeText = (TextView) findViewById(R.id.fromTimeText);
        mtoTimeText =(TextView) findViewById(R.id.toTimeText);


        final Intent saveIntent = getIntent();
        providerOrgText.setText("Provider Org : "+saveIntent.getStringExtra("providerOrg"));
        serviceNameText.setText("Service : " + saveIntent.getStringExtra("serviceName"));
        providerNameText.setText("Provider : " + saveIntent.getStringExtra("providerName"));
        mDateText.setText("Date : "+saveIntent.getStringExtra("mDate"));
        mfromTimeText.setText("Start Time : "+saveIntent.getStringExtra("mfromTime"));
        mtoTimeText.setText("End Time : "+saveIntent.getStringExtra("mtoTime"));




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
                        "\n Date : "+saveIntent.getStringExtra("mDate")+
                        "\n Start Time : " + saveIntent.getStringExtra("mfromTime")+
                        "\n End Time : " + saveIntent.getStringExtra("mtoTime");

                intent.putExtra(Intent.EXTRA_SUBJECT,appName);
                intent.putExtra(Intent.EXTRA_TEXT,message);
                startActivity(Intent.createChooser(intent,"Share Using"));
            }
        });

    }


}