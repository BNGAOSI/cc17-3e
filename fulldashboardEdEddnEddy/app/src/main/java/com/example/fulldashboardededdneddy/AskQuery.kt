package com.example.fulldashboardededdneddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class AskQuery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_query)


        val backbutton = findViewById<ImageButton>(R.id.askback)
        backbutton.setOnClickListener {
            val intent = Intent(this, reportAct::class.java)
            startActivity(intent)
        }

        val proceedAsk = findViewById<ImageButton>(R.id.askproceed)
        proceedAsk.setOnClickListener {
            val Intent = Intent(this, AskQueryDONE::class.java)
            startActivity(Intent)
        }
    }
}