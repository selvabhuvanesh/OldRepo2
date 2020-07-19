package com.qladder.pinefruit;

import android.content.Context;
import android.content.Intent;
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
import java.util.TooManyListenersException;

public class SearchListAdapter extends ArrayAdapter <SessionObject> {

    Context mContext;
    int mresource;
    List<SessionObject> mSessionList;

    public SearchListAdapter(Context context, int resource, List<SessionObject> sessionList) {
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

        final SessionObject sessionObject = mSessionList.get(position);

        sessionName.setText(sessionObject.getSessionName());
        sessionDetails.setText(sessionObject.getSessionDetails());
        //image using only a standard image for test purpose.
        // this has to be replaced by
        // image array or initial or date or session id which i have to decide later
        imageView.setImageDrawable(mContext.getResources().getDrawable(sessionObject.getImage()));

        view.findViewById(R.id.changebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Toast.makeText(getContext(),"You clicked : "+sessionObject.sessionName,Toast.LENGTH_LONG).show();
            }
        });
        return view;


    }

}
