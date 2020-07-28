package com.qladder.pinefruit;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

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


        mSessionInfoList = new ArrayList<>();
       /* db = FirebaseDatabase.getInstance().getReference("Session");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sessionInfo = new SessionInfo("","","asdasd","asdasda","Open");

                for (DataSnapshot ds : snapshot.getChildren()) {
                    sessionInfo.sessionID = ds.child("sessionID").getValue().toString();
                    sessionInfo.sessionStatus = ds.child("sessionStatus").getValue().toString();
                    sessionInfo.mfromtime = ds.child("mfromtime").getValue().toString();
                    sessionInfo.mdate = ds.child("mdate").getValue().toString();
                    mSessionInfoList.add(sessionInfo);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }



        });*/


      int i=0;
       while (i<=5)
        {
            sessionInfo = new SessionInfo(i+" million","hfhfh","asdasd","asdasda","Open");
            mSessionInfoList.add(sessionInfo);
            i++;
        }
        mListView = (ListView) findViewById(R.id.listview);
        adapter = new SearchListAdapter(this,R.layout.row, mSessionInfoList);
        mListView.setAdapter(adapter);

       }


}
