package com.ambiongltb.fulldashboardededdneddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ambiongltb.fulldashboardededdneddy.reportactvities.FetchingActivity;
import com.ambiongltb.fulldashboardededdneddy.reportactvities.InsertionActivity;
import com.ambiongltb.fulldashboardededdneddy.R;


public class SendReportFragment extends Fragment {

    Activity context;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        // Inflate the layout for this fragment
       view = inflater.inflate(R.layout.fragment_send_report, container, false);
       return view;
    }

}