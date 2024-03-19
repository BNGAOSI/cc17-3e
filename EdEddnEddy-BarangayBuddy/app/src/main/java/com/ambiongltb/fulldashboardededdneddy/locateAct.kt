package com.ambiongltb.fulldashboardededdneddy

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.ambiongltb.fulldashboardededdneddy.R

class locateAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locate)

        val dashboardButton = findViewById<ImageButton>(R.id.backButton)
        dashboardButton.setOnClickListener {
            val Intent = Intent(this, com.ambiongltb.fulldashboardededdneddy.MainActivity::class.java)
            startActivity(Intent)
        }
    }
}