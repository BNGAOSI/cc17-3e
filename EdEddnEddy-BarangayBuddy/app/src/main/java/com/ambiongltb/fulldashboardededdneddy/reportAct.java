package com.ambiongltb.fulldashboardededdneddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ambiongltb.fulldashboardededdneddy.reportactvities.InsertionActivity;
import com.ambiongltb.fulldashboardededdneddy.R.id;
import com.ambiongltb.fulldashboardededdneddy.R.layout;
import com.ambiongltb.fulldashboardededdneddy.reportactvities.InsertionActivity;

import org.jetbrains.annotations.Nullable;

public final class reportAct extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_report);
        Button dashboardButton = (Button)this.findViewById(id.backButton);
        dashboardButton.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Intent Intent = new Intent((Context)reportAct.this, MainActivity.class);
                reportAct.this.startActivity(Intent);
            }
        }));
        Button Click = (Button)this.findViewById(id.reportButton1);
        Click.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Intent Intent = new Intent((Context)reportAct.this, InsertionActivity.class);
                reportAct.this.startActivity(Intent);
            }
        }));
        Button click1 = (Button)this.findViewById(id.suggestButton);
        click1.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Intent Intent = new Intent((Context)reportAct.this, Suggest.class);
                reportAct.this.startActivity(Intent);
            }
        }));

    }
}
