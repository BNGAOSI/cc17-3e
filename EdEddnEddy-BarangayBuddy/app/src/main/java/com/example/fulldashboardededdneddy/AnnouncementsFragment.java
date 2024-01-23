package com.example.fulldashboardededdneddy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fulldashboardededdneddy.adapter.AnnouncementAdapterSecond;
import com.example.fulldashboardededdneddy.data.AnnouncementDataClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AnnouncementsFragment extends Fragment {

    private RecyclerView recyclerView;
    private AnnouncementAdapterSecond adapterSecond;

    private  List<AnnouncementDataClass> datalist;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    View view;
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_announcements, container, false);
        return view;

    }
}