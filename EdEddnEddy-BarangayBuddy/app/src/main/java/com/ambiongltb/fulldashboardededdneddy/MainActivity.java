package com.ambiongltb.fulldashboardededdneddy;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ambiongltb.fulldashboardededdneddy.reportactvities.InsertionActivity;
import com.ambiongltb.fulldashboardededdneddy.R;
import com.ambiongltb.fulldashboardededdneddy.R.id;
import com.ambiongltb.fulldashboardededdneddy.R.layout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.Nullable;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int RC_NOTIFICATION = 99;
    private DrawerLayout drawerLayout;
    FragmentManager fragmentManager;

    //For navigation feature after opening google maps
    private final double destinationLat = 16.433671;
    private final double destinationLng = 120.606401;

    int id2 = 0;
    DatabaseReference reference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_main);


//=================  Toolbar Settings  ==========================
        Toolbar toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(id.drawer_layout);
        NavigationView navigationView = findViewById(id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new Home()).commit();
            navigationView.setCheckedItem(id.nav_home);
        }

//==============================    Announcement Notification   =====================================

        reference = FirebaseDatabase.getInstance().getReference().child("announcements");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    id2 = (int) dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @androidx.annotation.Nullable String previousChildName) {

                String announcementTitle = snapshot.child("title").getValue(String.class);
                notification(announcementTitle);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @androidx.annotation.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @androidx.annotation.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void notification(String announcementTitle) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        Intent announcementIntent = new Intent(this, announcementsRefinedScreen.class);
        announcementIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, announcementIntent, PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "n")
                .setContentTitle("New Announcement has been posted")
                .setSmallIcon(R.drawable.barngaybuddylogo)
                .setAutoCancel(true)
                .setContentText(announcementTitle)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(999, builder.build());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;

    }


    //======================== Navigation Drawer =================================
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == id.nav_home) {
            replaceFragment(new Home());
        } else if (itemId == id.nav_secure_document) {
            replaceFragment(new SecureDocumentsFragment());
        } else if (itemId == id.nav_suggest) {
            Intent intent = new Intent(this, Suggest.class);
            startActivity(intent);
            return true;
        } else if (itemId == id.nav_report) {
            Intent intent = new Intent(this, InsertionActivity.class);
            startActivity(intent);
            return true;
        }
/*   else if (itemId == id.nav_emergencyHotline) {
            replaceFragment(new EmergencyHotlineFragment());
        }     */
        else if (itemId == id.nav_status) {
            replaceFragment(new DocumentStatusFragment());
        } else if (itemId == id.nav_ResidencyStatus) {
            replaceFragment(new ResidencyStatusFragment());
        } else if (itemId == id.nav_BusinessClearStatus) {
            replaceFragment(new BusinessStatusFragment());
        } else if (itemId == id.nav_IndigencyStatus) {
            replaceFragment(new IndigencyStatusFragment());
        } else if (itemId == id.nav_OtherDocsStatus) {
            replaceFragment(new OtherDocsStatusFragment());
        } else if (itemId == id.nav_FinishedStatus) {
            replaceFragment(new FinishedStatusFragment());
        } else if (itemId == id.nav_logout) {
            FirebaseAuth.getInstance().signOut();

            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
            return true;
        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //=====================  Converts Frame layout to Fragment  ==================
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

/*    FOR FUTURE FEATURE: NOTIFICATION
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == id.notification_bell) {
            Intent intent = new Intent(this, PermissionActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/


}
