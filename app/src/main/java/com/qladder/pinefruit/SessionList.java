package com.qladder.pinefruit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SessionList extends AppCompatActivity {

    List<SessionObject> mSessionObjectList;
    ListView mListView;
    //int image = 0;
    String sesionName[] = {"S_1","S_2","S_3","S_4","S_5","S_6","S_7","S_8","S_9","S_10","S_11"};
    String sessionDetails[] = {"Detail_1","Detail_2","Detail_3","Detail_4","Detail_5","Detail_6","Detail_7","Detail_8","Detail_9","Detail_10","Detail_11"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_list);

        mSessionObjectList = new ArrayList<>();


        int index=0;

        while (index<=10)
        {
            mSessionObjectList.add(new SessionObject(R.drawable.ammu,sesionName[index],sessionDetails[index]));

              index++;
        }
        mListView = (ListView) findViewById(R.id.listview);
        SearchListAdapter adapter = new SearchListAdapter(this,R.layout.row, mSessionObjectList);
        mListView.setAdapter(adapter);







    }


}
