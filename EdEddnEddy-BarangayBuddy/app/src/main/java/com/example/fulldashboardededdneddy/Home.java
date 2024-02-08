package com.example.fulldashboardededdneddy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.fulldashboardededdneddy.reportactvities.ReportActivity;


public class Home extends Fragment {

    Activity context;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getActivity();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    public void onStart() {
        super.onStart();
        Button securedocs = (Button) context.findViewById(R.id.appointment_button);
        securedocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SecureDocument.class);
                startActivity(intent);
            }
        });

        Button rprtsuggbtn = (Button) context.findViewById(R.id.report_button);
        rprtsuggbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReportActivity.class);
                startActivity(intent);
            }
        });

        Button mapbtn = (Button) context.findViewById(R.id.locate_button);
        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapActivity.class);
                startActivity(intent);
            }
        });

        Button announcementbtn = (Button) context.findViewById(R.id.announcement_button);
        announcementbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, announcementsRefinedScreen.class);
                startActivity(intent);
            }
        });

        Button suggestbtn = (Button) context.findViewById(R.id.suggestButton);
        suggestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Suggest.class);
                startActivity(intent);
            }
        });

    }

}