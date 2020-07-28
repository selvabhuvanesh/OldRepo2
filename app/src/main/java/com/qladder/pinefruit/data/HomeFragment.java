package com.qladder.pinefruit.data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.qladder.pinefruit.R;

public class HomeFragment extends Fragment{

    Button createNewbtn;
    Button viewbtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      /* super.onCreate(savedInstanceState);
        createNewbtn = (Button) getView().findViewById(R.id.createSessionbtn);
        viewbtn = (Button)getView().findViewById(R.id.viewSessionbtn);

        // uncomment the below line to disable the view/modify button for production release
        //viewbtn.setEnabled(false);


        createNewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(HomeFragment.this.getActivity(), ProviderInfoActivity.class);
                startActivity(newIntent);

            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent = new Intent(HomeFragment.this.getActivity(), SessionListActivity.class);
                startActivity(viewIntent);
            }
        });*/

        return inflater.inflate(R.layout.activity_provider_choice,container,false);

    }
    }

