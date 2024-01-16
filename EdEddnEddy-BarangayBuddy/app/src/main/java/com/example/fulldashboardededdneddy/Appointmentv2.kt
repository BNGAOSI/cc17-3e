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
            val Intent = Intent(this, BarangayClearanceForm::class.java)
            startActivity(Intent)
        }
    }
}