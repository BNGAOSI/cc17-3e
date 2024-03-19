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

    public void onStart(){
        super.onStart();
        Button rprtbtn = (Button) context.findViewById(R.id.btnInsertData);
        rprtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InsertionActivity.class);
                startActivity(intent);
            }
        });

        Button rprthist = (Button) context.findViewById(R.id.btnFetchData);
        rprthist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FetchingActivity.class);
                startActivity(intent);
            }
        });
    }
}