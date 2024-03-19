package com.ambiongltb.fulldashboardededdneddy

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ReportDetailsActivity : AppCompatActivity() {

    private lateinit var tvResId: TextView
    private lateinit var tvResName: TextView
    private lateinit var tvCurLoc: TextView
    private lateinit var tvReport: TextView

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

    }

    private fun setValuesToViews(){

        tvResId.text = intent.getStringExtra("resId")
        tvResName.text = intent.getStringExtra("resName")
        tvCurLoc.text = intent.getStringExtra("resCurLoc")
        tvReport.text = intent.getStringExtra("resReport")
    }
}