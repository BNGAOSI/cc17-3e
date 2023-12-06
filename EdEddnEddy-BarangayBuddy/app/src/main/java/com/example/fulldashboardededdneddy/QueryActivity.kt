package com.example.fulldashboardededdneddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fulldashboardededdneddy.reportactvities.FetchingActivity
import com.example.fulldashboardededdneddy.reportactvities.InsertionActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class QueryActivity : AppCompatActivity() {

    private lateinit var btnInsertQuery: Button
    private lateinit var btnFetchQuery: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query)

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        btnInsertQuery = findViewById(R.id.btnInsertQuery)
        btnFetchQuery = findViewById(R.id.btnFetchQuery)


        btnInsertQuery.setOnClickListener {
            val intent = Intent(this, AskQuery::class.java)
            startActivity(intent)
        }

        btnFetchQuery.setOnClickListener {
            val intent = Intent(this, FetchQuery::class.java)
            startActivity(intent)
        }


    }

}