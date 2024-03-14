package com.example.fulldashboardededdneddy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.fulldashboardededdneddy.adapter.AnnouncementAdapterSecond;
import com.example.fulldashboardededdneddy.data.AnnouncementDataClass;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class announcementsRefinedScreen extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    RecyclerView recyclerView;
    Toolbar toolbar;
    List<AnnouncementDataClass> datalist;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    AnnouncementAdapterSecond adapter;

    // Method to filter announcements based on category
    private void filterByCategory(String category) {
        List<AnnouncementDataClass> filteredList = new ArrayList<>();
        if (category.equals("All")) {
            // If "All" category is selected, add all announcements to the filtered list
            filteredList.addAll(datalist);
        } else {
            // Otherwise, filter announcements based on the selected category
            for (AnnouncementDataClass announcement : datalist) {
                if (announcement.getCategory().equals(category)) {
                    filteredList.add(announcement);
                }
            }
        }
        adapter.setData(filteredList); // Update RecyclerView with filtered list
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements_refined_screen);

        DatabaseReference announcementsRef = FirebaseDatabase.getInstance().getReference().child("announcements");

        announcementsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // Trigger notification for each new announcement added to the database
                sendNotification("New Announcement", "A new announcement has been published.");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // Handle changes to existing announcements (if needed)
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                // Handle removal of announcements (if needed)
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // Handle moving of announcements (if needed)
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors (if needed)
            }
        });



//============================Toolbar Settings================================

        toolbar = findViewById(R.id.appToolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Announcements");
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.announcement_recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(announcementsRefinedScreen.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(announcementsRefinedScreen.this);
        builder.setCancelable(true);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        datalist = new ArrayList<>();

        adapter = new AnnouncementAdapterSecond(announcementsRefinedScreen.this, datalist);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("announcements");
        dialog.show();


        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                datalist.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    AnnouncementDataClass dataClass = itemSnapshot.getValue(AnnouncementDataClass.class);
                    datalist.add(0, dataClass);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        // Filter by category when a button is clicked

        Button allCatBtn = findViewById(R.id.AllCatBtn);
        allCatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterByCategory("All");
            }
        });

        Button ProgramCatBtn = findViewById(R.id.ProgramsCatBtn);
        ProgramCatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterByCategory("Programs");
            }
        });

        Button ProjectsCatBtn = findViewById(R.id.ProjectsCatBtn);
        ProjectsCatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterByCategory("Projects");
            }
        });

        Button ActivitiesCatBtn = findViewById(R.id.ActivitiesCatBtn);
        ActivitiesCatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterByCategory("Activities");
            }
        });

    }
    private void sendNotification(String title, String message) {
        // Use Firebase Cloud Messaging to send a push notification
        Map<String, String> notification = new HashMap<>();
        notification.put("title", title);
        notification.put("body", message);

        // Send notification using FCM
        FirebaseMessaging.getInstance().send(new RemoteMessage.Builder("107104492368")
                .setMessageId(Integer.toString(new Random().nextInt(9999)))
                .setData(notification)
                .build());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            // Handle back button press
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Close the drawer if it is open, otherwise, perform the default back button behavior
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}