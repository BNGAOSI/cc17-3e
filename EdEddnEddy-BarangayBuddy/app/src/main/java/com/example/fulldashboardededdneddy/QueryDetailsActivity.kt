package com.example.fulldashboardededdneddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class QueryDetailsActivity : AppCompatActivity() {

    private lateinit var tvQueryId: TextView
    private lateinit var tvAskName: TextView
    private lateinit var tvQuery: TextView
    private lateinit var tvReplies: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query_details)
    }

    private fun initView() {
        tvQueryId = findViewById(R.id.tvAskId)
        tvAskName = findViewById(R.id.tvAskName)
        tvQuery = findViewById(R.id.tvQuery)
        tvReplies = findViewById(R.id.tvReplies)

        /*
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete) */
    }

    private fun setValuesToViews(){

        tvQueryId.text = intent.getStringExtra("askId")
        tvAskName.text = intent.getStringExtra("askName")
        tvQuery.text = intent.getStringExtra("askField")
        tvReplies.text = intent.getStringExtra("replies")
    }

}