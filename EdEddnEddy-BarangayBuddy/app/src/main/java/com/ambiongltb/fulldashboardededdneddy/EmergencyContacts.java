package com.ambiongltb.fulldashboardededdneddy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;

public class EmergencyContacts extends BaseActivity {

    ImageView imageView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contacts);

        toolbar = findViewById(R.id.appToolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Emergency Contacts");
        actionBar.setDisplayHomeAsUpEnabled(true);

        imageView=findViewById(R.id.emergency_contacts);

    }
}