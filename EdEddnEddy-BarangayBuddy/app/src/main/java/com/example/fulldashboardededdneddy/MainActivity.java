package com.example.fulldashboardededdneddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.fulldashboardededdneddy.R.id;
import com.example.fulldashboardededdneddy.R.layout;

import org.jetbrains.annotations.Nullable;

public final class MainActivity extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_main);
        ImageButton appointmentButton = (ImageButton)this.findViewById(id.appointment_button);
        appointmentButton.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Intent Intent = new Intent((Context)MainActivity.this, SecureDocument.class);
                MainActivity.this.startActivity(Intent);
            }
        }));
        ImageButton reportButton = (ImageButton)this.findViewById(id.report_button);
        reportButton.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Intent Intent = new Intent((Context)MainActivity.this, reportAct.class);
                MainActivity.this.startActivity(Intent);
            }
        }));
        ImageButton locateButton = (ImageButton)this.findViewById(id.locate_button);
        locateButton.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Intent Intent = new Intent((Context)MainActivity.this, MapActivity.class);
                MainActivity.this.startActivity(Intent);
            }
        }));
        ImageButton announcementButton = (ImageButton)this.findViewById(id.announcement_button);
        announcementButton.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Intent Intent = new Intent((Context)MainActivity.this, announcementsRefinedScreen.class);
                MainActivity.this.startActivity(Intent);
            }
        }));
    }
}
