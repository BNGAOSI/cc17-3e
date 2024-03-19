package com.ambiongltb.fulldashboardededdneddy

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class ReportDONE : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_done)

        val buttonClick = findViewById<ImageButton>(R.id.homereport)
        buttonClick.setOnClickListener {
            val Intent = Intent(this, com.ambiongltb.fulldashboardededdneddy.MainActivity::class.java)
            startActivity(Intent)
        }
    }
}