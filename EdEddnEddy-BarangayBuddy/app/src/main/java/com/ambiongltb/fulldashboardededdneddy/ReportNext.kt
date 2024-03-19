package com.ambiongltb.fulldashboardededdneddy

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class ReportNext : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_next)

        val dashboardButton = findViewById<ImageButton>(R.id.backreport)
        dashboardButton.setOnClickListener {
            val Intent = Intent(this, com.ambiongltb.fulldashboardededdneddy.reportAct::class.java)
            startActivity(Intent)
        }

        val buttonClick = findViewById<ImageButton>(R.id.Fillout1)
        buttonClick.setOnClickListener {
            val Intent = Intent(this, ReportFillOut::class.java)
            startActivity(Intent)
        }
    }
}