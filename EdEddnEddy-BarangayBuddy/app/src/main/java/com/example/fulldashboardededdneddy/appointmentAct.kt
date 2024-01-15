package com.example.fulldashboardededdneddy

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fulldashboardededdneddy.adapter.ItemAdapter
import com.example.fulldashboardededdneddy.data.Datasource

class appointmentAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointmentact)

        val myDataset = Datasource().loadAffirmations()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ItemAdapter(this, myDataset)
        recyclerView.setHasFixedSize(true)


         /*val dashboardButton = findViewById<ImageButton>(R.id.appback)
        dashboardButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) */
        }
    }
