package com.example.fulldashboardededdneddy;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class announcementsRefinedScreen extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{


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
        builder.setCancelable(false);
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