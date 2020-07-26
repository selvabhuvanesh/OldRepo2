package com.qladder.pinefruit;

import android.os.Bundle;
import android.widget.ListView;

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
    FirebaseDatabase db;
    DatabaseReference ref;
    SessionInfo sessionInfo;
    SearchListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_list);

        mSessionInfoList = new ArrayList<>();
         db = FirebaseDatabase.getInstance();
        ref = db.getReference("ProviderInfo");
        //providerInfo = new ProviderInfo();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                   sessionInfo = ds.getValue(SessionInfo.class);

                  mSessionInfoList.add(new SessionInfo(sessionInfo.getSessionID(),
                          sessionInfo.getProviderID(),
                          sessionInfo.getMfromtime(),
                          sessionInfo.getMtotime(),
                          sessionInfo.getMdate(),
                          sessionInfo.getSessionStatus()));

                }
                mListView = (ListView) findViewById(R.id.listview);
                adapter = new SearchListAdapter(SessionListActivity.this,R.layout.row, mSessionInfoList);
                mListView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       }


}
