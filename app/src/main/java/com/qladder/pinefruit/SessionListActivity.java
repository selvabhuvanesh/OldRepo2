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
    ProviderInfo providerInfo;
    SearchListAdapter adapter;

    //int image = 0;
    String sesionName[] = {"S_1","S_2","S_3","S_4","S_5","S_6","S_7","S_8","S_9","S_10","S_11"};
    String sessionDetails[] = {"Detail_1. This line is detailed at length to test the folding feature that is set in the text view for multiple texts. this should not show more than 2 lines in mobile"
            ,"Detail_2","Detail_3","Detail_4","Detail_5","Detail_6","Detail_7","Detail_8","Detail_9","Detail_10","Detail_11"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_list);

        mSessionInfoList = new ArrayList<>();
         db = FirebaseDatabase.getInstance();
        ref = db.getReference("ManObject");
        providerInfo = new ProviderInfo();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                   providerInfo = ds.getValue(ProviderInfo.class);

                  mSessionInfoList.add(new SessionInfo(R.drawable.ic_baseline_person_24,providerInfo.providerName,providerInfo.providerLocality));

                }
                mListView = (ListView) findViewById(R.id.listview);
                adapter = new SearchListAdapter(SessionListActivity.this,R.layout.row, mSessionInfoList);
                mListView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        int index=0;

        while (index<=10)
        {
            //mSessionInfoList.add(new SessionInfo(R.drawable.ic_baseline_person_24,sesionName[index],sessionDetails[index]));

              index++;
        }


    }


}
