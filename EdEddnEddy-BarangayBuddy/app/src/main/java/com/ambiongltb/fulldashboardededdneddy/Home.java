package com.ambiongltb.fulldashboardededdneddy;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ambiongltb.fulldashboardededdneddy.reportactvities.InsertionActivity;
import com.ambiongltb.fulldashboardededdneddy.R;


public class Home extends Fragment {

    Activity context;

    View view;

    private final double destinationLat = 16.433671;
    private final double destinationLng = 120.606401;

    public Home() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        context = getActivity();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);


        // Enables an image to be clickable
        ImageView emergencyContactsImageView = view.findViewById(R.id.emergency_contacts);
        emergencyContactsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EmergencyContacts.class);
                startActivity(intent);
            }
        });

        return view;


    }


    public void onStart() {
        super.onStart();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set click listeners for buttons
        Button openMapsButton = view.findViewById(R.id.locate_button);
        openMapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoogleMapsWithDirections();
            }
        });

        Button securedocs = view.findViewById(R.id.appointment_button);
        securedocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), SecureDocument.class);
                startActivity(intent);
            }
        });

        Button rprtsuggbtn = view.findViewById(R.id.report_button);
        rprtsuggbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), InsertionActivity.class);
                startActivity(intent);
            }
        });

        Button announcementbtn = view.findViewById(R.id.announcement_button);
        announcementbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), announcementsRefinedScreen.class);
                startActivity(intent);
            }
        });

        Button suggestbtn = view.findViewById(R.id.suggestButton);
        suggestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), Suggest.class);
                startActivity(intent);
            }
        });
    }

    private void openGoogleMapsWithDirections() {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + destinationLat + "," + destinationLng);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(requireContext(), "Google Maps is not installed", Toast.LENGTH_SHORT).show();
        }
    }
}