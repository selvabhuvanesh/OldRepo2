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

public class SettingsActivity extends Fragment{

    Button createNewbtn;
    Button viewbtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // super.onCreate(savedInstanceState);
        // super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.settings,container,false);


        return view;
    }
}

