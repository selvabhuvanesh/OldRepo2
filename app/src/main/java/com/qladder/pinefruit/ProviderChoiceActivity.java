package com.qladder.pinefruit;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class ProviderChoiceActivity extends AppCompatActivity {
Button createNewbtn;
Button viewbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_provider_choice);
        AccountManager am = AccountManager.get(this); // "this" references the current Context
        Account[] accounts = am.getAccountsByType("com.google");
        createNewbtn = (Button)findViewById(R.id.createSessionbtn);
        viewbtn = (Button)findViewById(R.id.viewSessionbtn);

        // uncomment the below line to disable the view/modify button for production release
        //viewbtn.setEnabled(false);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(ProviderChoiceActivity.this);
        if(acct != null)
        {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            TextView  unam= (TextView)findViewById(R.id.userName);
            Toast.makeText(this,"Inside the provider Name : "+personName,Toast.LENGTH_LONG).show();
            unam.setText(personName);

        }



        createNewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent newIntent = new Intent(ProviderChoiceActivity.this, ProviderOrgInfoActivity.class);
               startActivity(newIntent);
               finish();
            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Intent viewIntent = new Intent(ProviderChoiceActivity.this, ProviderSessionListActivity.class);
                    startActivity(viewIntent);

            }
        });


    }
}
