package com.example.fulldashboardededdneddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Appointmentv2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointmentv2)

        val barangayClearancbutton = findViewById<Button>(R.id.barangayClearancebtn)
        barangayClearancbutton.setOnClickListener {
            val intent = Intent(this, BarangayClearanceForm::class.java)
            startActivity(intent)
        }

        val businessclearancemainbutton = findViewById<Button>(R.id.businessclearancebtn)
        businessclearancemainbutton.setOnClickListener {
            val intent = Intent(this, BusinessClearanceMain::class.java)
            startActivity(intent)
        }
    }
}