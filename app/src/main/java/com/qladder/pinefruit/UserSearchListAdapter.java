package com.qladder.pinefruit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.util.List;

public class UserSearchListAdapter extends ArrayAdapter <SessionInfo> {

    Context mContext;
    int mresource;
    List<SessionInfo> mSessionList;
    SessionInfo sessionInfo;


    public UserSearchListAdapter(Context context, int resource, List<SessionInfo> sessionList) {
        super(context, resource, sessionList);
        this.mContext = context;
        this.mresource = resource;
        this.mSessionList = sessionList;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(R.layout.u_row,null);

        TextView sessionName = view.findViewById(R.id.sessionName);
        TextView sessionDetails = view.findViewById(R.id.sessionDetails);
        TextView fromTime = view.findViewById(R.id.fromtime);
        TextView toTime = view.findViewById(R.id.toTime);
        TextView sessionStatus = view.findViewById(R.id.sessionStatus);
        ImageView imageView = view.findViewById(R.id.providerimage);
        TextView starRate = view.findViewById(R.id.rate);
        TextView mdate = view.findViewById(R.id.mdate);


        sessionInfo = mSessionList.get(position);
        sessionName.setText(sessionInfo.providerName);
        sessionDetails.setText(sessionInfo.sessionName);
        fromTime.setText(sessionInfo.mfromtime);
        toTime.setText(sessionInfo.mtotime);
        sessionStatus.setText(sessionInfo.sessionStatus);
        LatLng latLng = new LatLng(Double.parseDouble(sessionInfo.getProviderLatitude()),Double.parseDouble(sessionInfo.getProviderLongitude()));
        starRate.setText(calculateDistance(latLng));
        mdate.setText(sessionInfo.mdate.substring(4,10));
        // using only a standard image for test purpose.
        // this has to be replaced by
        // image array or initial or date or session id which i have to decide later
        imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.doctor));



        return view;


    }

    private String calculateDistance(LatLng latLng){
        LatLng point2 = latLng;
        Location locationA = new Location("point A");
        locationA.setLatitude(UserSearchActivity.latLng.latitude);
        locationA.setLongitude(UserSearchActivity.latLng.longitude);
        Location locationB = new Location("point B");
        locationB.setLatitude(point2.latitude);
        locationB.setLongitude(point2.longitude);
        double distance = locationA.distanceTo(locationB);
        double dist = distance/1600f;
        String pr = new DecimalFormat("##.#").format(dist);
        return pr+" Km";
    }
}
