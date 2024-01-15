package com.example.fulldashboardededdneddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fulldashboardededdneddy.adapter.AnnouncementAdapter;
import com.example.fulldashboardededdneddy.data.AnnouncementDataClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class announcementsRefinedScreen extends AppCompatActivity {

    RecyclerView recyclerView;
    List<AnnouncementDataClass> datalist;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements_refined_screen);

        recyclerView = findViewById(R.id.announcement_recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(announcementsRefinedScreen.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(announcementsRefinedScreen.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        datalist = new ArrayList<>();

        AnnouncementAdapter adapter = new AnnouncementAdapter(announcementsRefinedScreen.this, datalist);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("announcements");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                datalist.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    AnnouncementDataClass dataClass = itemSnapshot.getValue(AnnouncementDataClass.class);
                    datalist.add(dataClass);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });
    }
}