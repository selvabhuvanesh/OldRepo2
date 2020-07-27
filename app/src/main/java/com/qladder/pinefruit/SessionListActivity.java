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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_list);

        mSessionInfoList = new ArrayList<>();
       /* db = FirebaseDatabase.getInstance().getReference();
        sessionDBref = db.child("Session");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot ds : snapshot.getChildren()) {
                        sessionInfo = ds.getValue(SessionInfo.class);
                        mSessionInfoList.add(sessionInfo);

                    }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };


        sessionDBref.addValueEventListener(eventListener);

        */

        sessionInfo = new SessionInfo("001","hfhfh","asdasd","asdasda","Open");
       int i=0;
       while (i<=5)
        {
             //mSessionInfoList.add(R.drawable.fui_idp_button_background_phone,sessionInfo);
            sessionInfo.sessionID = i+"Million Customer";
            mSessionInfoList.add(sessionInfo);
            i++;
        }
        mListView = (ListView) findViewById(R.id.listview);
        adapter = new SearchListAdapter(this,R.layout.row, mSessionInfoList);
        mListView.setAdapter(adapter);

       }


}
