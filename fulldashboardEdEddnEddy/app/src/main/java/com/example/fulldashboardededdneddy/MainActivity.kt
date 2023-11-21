package com.example.fulldashboardededdneddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appointmentButton = findViewById<ImageButton>(R.id.appointment_button)
        appointmentButton.setOnClickListener {
            val Intent = Intent(this,appointmentAct::class.java)
            startActivity(Intent)
        }

        val reportButton = findViewById<ImageButton>(R.id.report_button)
        reportButton.setOnClickListener {
            val Intent = Intent(this,reportAct::class.java)
            startActivity(Intent)
        }

        val locateButton = findViewById<ImageButton>(R.id.locate_button)
        locateButton.setOnClickListener {
            val Intent = Intent(this, locateAct::class.java)
            startActivity(Intent)
        }

        val announcementButton = findViewById<ImageButton>(R.id.announcement_button)
        announcementButton.setOnClickListener {
            val Intent = Intent(this, announcementsAct::class.java)
            startActivity(Intent)
        }

        val settingsButton = findViewById<ImageButton>(R.id.settings_button)
        settingsButton.setOnClickListener {
            val Intent = Intent(this, settingsAct::class.java)
            startActivity(Intent)
        }

        val profileButton = findViewById<ImageButton>(R.id.profile_button)
        profileButton.setOnClickListener {
            val Intent = Intent(this, profileAct::class.java)
            startActivity(Intent)
        }

    }
}