package com.example.fulldashboardededdneddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ReportDetailsActivity : AppCompatActivity() {

    private lateinit var tvResId: TextView
    private lateinit var tvResName: TextView
    private lateinit var tvCurLoc: TextView
    private lateinit var tvReport: TextView
    private lateinit var tvReplies: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_details)

        initView()
        setValuesToViews()

    }

    private fun initView() {
        tvResId = findViewById(R.id.tvResId)
        tvResName = findViewById(R.id.tvResName)
        tvCurLoc = findViewById(R.id.tvCurLoc)
        tvReport = findViewById(R.id.tvReport)
        tvReplies = findViewById(R.id.tvReplies)

        /*
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete) */
    }

    private fun setValuesToViews(){

        tvResId.text = intent.getStringExtra("resId")
        tvResName.text = intent.getStringExtra("resName")
        tvCurLoc.text = intent.getStringExtra("resCurLoc")
        tvReport.text = intent.getStringExtra("resReport")
        tvReplies.text = intent.getStringExtra("replies")
    }
}