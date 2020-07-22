package com.qladder.pinefruit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SearchListAdapter extends ArrayAdapter <SessionInfo> {

    Context mContext;
    int mresource;
    List<SessionInfo> mSessionList;

    public SearchListAdapter(Context context, int resource, List<SessionInfo> sessionList) {
        super(context, resource, sessionList);
        this.mContext = context;
        this.mresource = resource;
        this.mSessionList = sessionList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.row,null);

        final TextView sessionName = view.findViewById(R.id.sessionName);
        final TextView sessionDetails = view.findViewById(R.id.sessionDetails);
        ImageView imageView = view.findViewById(R.id.providerimage);

        final SessionInfo sessionInfo = mSessionList.get(position);

        sessionName.setText(sessionInfo.getSessionName());
        sessionDetails.setText(sessionInfo.getSessionDetails());
        //I am using only a standard image for test purpose.
        // this has to be replaced by
        // image array or initial or date or session id which i have to decide later
        imageView.setImageDrawable(mContext.getResources().getDrawable(sessionInfo.getImage()));

        view.findViewById(R.id.changebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Toast.makeText(getContext(),"You clicked : "+ sessionInfo.sessionName,Toast.LENGTH_LONG).show();
            }
        });
        return view;


    }

}
