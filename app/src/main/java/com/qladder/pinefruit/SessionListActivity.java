package com.qladder.pinefruit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SessionListActivity extends AppCompatActivity {

    List<SessionInfo> mSessionInfoList;
    ListView mListView;
    DatabaseReference db;
    DatabaseReference providerDBref;
    SessionInfo sessionInfo;
    DatabaseReference sessionDBref;
    ProviderInfo providerInfo;
    SearchListAdapter adapter;



    @Override
    protected void onStart() {
        super.onStart();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_session_list);
        mListView = (ListView) findViewById(R.id.listview);
        mSessionInfoList = new ArrayList<SessionInfo>();
        db = FirebaseDatabase.getInstance().getReference("Session");


        //db.addListenerForSingleValueEvent(new ValueEventListener() {
            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot != null)
                    {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        sessionInfo = new SessionInfo("","","asdasd","asdasda","Open");
                        sessionInfo.sessionID = ds.child("sessionID").getValue().toString();
                        System.out.println("****************AM IN FOR *******************" + ds.child("sessionID").getValue().toString());
                        sessionInfo.sessionStatus = ds.child("sessionStatus").getValue().toString();
                        sessionInfo.mfromtime = ds.child("mfromtime").getValue().toString();
                        sessionInfo.mdate = ds.child("mdate").getValue().toString();
                        mSessionInfoList.add(sessionInfo);
                        adapter = new SearchListAdapter(getApplicationContext(),R.layout.row, mSessionInfoList);
                        mListView.setAdapter(adapter);
                    }
                    }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                // ERRRRRRRROR
                System.out.println("**************ONCANCALLED***********8");
            }



        });

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(getApplicationContext(),"YOUR DOCTOR FOR MODIFICATION is  :  "+mSessionInfoList.get(i).getSessionID(),Toast.LENGTH_LONG).show();
                    Intent providerInfoIntent = new Intent(SessionListActivity.this,ProviderInfoActivity.class);
                    startActivity(providerInfoIntent);
                }
            });



    }
}
