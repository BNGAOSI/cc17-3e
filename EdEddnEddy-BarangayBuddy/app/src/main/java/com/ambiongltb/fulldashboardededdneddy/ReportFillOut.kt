package com.ambiongltb.fulldashboardededdneddy

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class ReportFillOut : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_fill_out)

        val dashboardButton = findViewById<ImageView>(R.id.back_filll)
        dashboardButton.setOnClickListener {
            val intent = Intent(this, ReportNext::class.java)
            startActivity(intent)
        }

        val buttonClick = findViewById<ImageButton>(R.id.fill_proceed)
        buttonClick.setOnClickListener {
            val Intent = Intent(this, ReportDONE::class.java)
            startActivity(Intent)
        }
    }
}