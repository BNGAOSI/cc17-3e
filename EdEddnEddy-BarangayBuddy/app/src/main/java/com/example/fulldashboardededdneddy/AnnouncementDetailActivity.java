package com.example.fulldashboardededdneddy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class AnnouncementDetailActivity extends BaseActivity {

    TextView detailDesc, detailTitle;
    Toolbar toolbar;
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_detail);

        toolbar = findViewById(R.id.appToolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Announcement Detail");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        detailDesc = findViewById(R.id.announcement_detailDesc);
        detailImage = findViewById(R.id.announcement_detailImage);
        detailTitle = findViewById(R.id.announcement_detailTitle);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }

    }
}