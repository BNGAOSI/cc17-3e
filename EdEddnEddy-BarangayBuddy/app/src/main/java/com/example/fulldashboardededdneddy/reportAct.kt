package com.example.fulldashboardededdneddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.fulldashboardededdneddy.reportactvities.ReportActivity

class reportAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        val dashboardButton = findViewById<ImageButton>(R.id.backButton)
        dashboardButton.setOnClickListener {
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        }

        val Click = findViewById<ImageButton>(R.id.reportButton1)
        Click.setOnClickListener {
            val Intent = Intent(this, ReportActivity::class.java)
            startActivity(Intent)
        }

        val click1 = findViewById<ImageButton>(R.id.suggestButton)
        click1.setOnClickListener {
            val Intent = Intent(this, Suggest::class.java)
            startActivity(Intent)
        }

        val click2 = findViewById<ImageButton>(R.id.askButton)
        click2.setOnClickListener {
            val Intent = Intent(this, AskQuery::class.java)
            startActivity(Intent)
        }

    }
}