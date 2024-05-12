package com.ambiongltb.fulldashboardededdneddy;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BarangayMapFragment extends Fragment {

    Activity context;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        // Inflate the layout for this fragment
       view = inflater.inflate(R.layout.fragment_barangay_map, container, false);
       return view;
    }

}