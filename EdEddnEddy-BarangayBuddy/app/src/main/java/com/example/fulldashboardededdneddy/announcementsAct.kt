package com.example.fulldashboardededdneddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulldashboardededdneddy.adapter.AnnouncementAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.toObject

class announcementsAct : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var announcementArrayList: ArrayList<Announcement>
    private lateinit var announcementAdapter: AnnouncementAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_announcements)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        announcementArrayList = arrayListOf()

        announcementAdapter = AnnouncementAdapter(announcementArrayList)

        recyclerView.adapter = announcementAdapter

        EventChangeListener()
        /*
        val dashboardButton = findViewById<ImageButton>(R.id.backButton)
        dashboardButton.setOnClickListener {
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        } */
    }

    private fun EventChangeListener() {

        db = FirebaseFirestore.getInstance()
        db.collection("Announcements").
                addSnapshotListener(object : EventListener<QuerySnapshot>{
                    override fun onEvent(
                        value: QuerySnapshot?,
                        error: FirebaseFirestoreException?
                    ) {

                        if (error != null){

                            Log.e("Firestore Error", error.message.toString())
                            return

                        }
                        for (dc : DocumentChange in value?.documentChanges!!){

                            if (dc.type == DocumentChange.Type.ADDED){

                                announcementArrayList.add(dc.document.toObject(Announcement::class.java))

                            }

                        }

                        announcementAdapter.notifyDataSetChanged()

                    }

                })

    }

}