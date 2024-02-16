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


public class SecureDocumentsFragment extends Fragment {

    Activity context;
View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getActivity();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_secure_documents, container, false);
        return view;


    }

    public void onStart(){
        super.onStart();
        Button brngyclearbtn = (Button) context.findViewById(R.id.barangayClearancebtn);
        brngyclearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BarangayClearanceForm.class);
                startActivity(intent);
            }
        });

        Button bsclearbtn = (Button) context.findViewById(R.id.businessclearancebtn);
        bsclearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BusinessClearanceMain.class);
                startActivity(intent);
            }
        });

        Button residencybtn = (Button) context.findViewById(R.id.residencybtn);
        residencybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, residencyform.class);
                startActivity(intent);
            }
        });

        Button otherDocsbtn = (Button) context.findViewById(R.id.otherDocbtn);
        otherDocsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, otherDocsForm.class);
                startActivity(intent);
            }
        });
    }
}