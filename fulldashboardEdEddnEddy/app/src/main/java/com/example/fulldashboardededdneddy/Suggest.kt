package com.example.fulldashboardededdneddy

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Suggest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggest)


        val backbutton = findViewById<ImageButton>(R.id.goback1)
        backbutton.setOnClickListener {
            val intent = Intent(this, reportAct::class.java)
            startActivity(intent)
        }

        val proceedSuggest1 = findViewById<ImageButton>(R.id.suggest_proceed)
        proceedSuggest1.setOnClickListener {
            val Intent = Intent(this, SuggestNext::class.java)
            startActivity(Intent)
        }
    }
}