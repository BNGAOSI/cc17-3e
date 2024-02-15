package com.example.fulldashboardededdneddy.reportactvities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulldashboardededdneddy.R
import com.example.fulldashboardededdneddy.ReportDetailsActivity
import com.example.fulldashboardededdneddy.adapter.ResAdapter
import com.example.fulldashboardededdneddy.model.ResidentModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class FetchingActivity : AppCompatActivity() {

    private lateinit var resRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var repList: ArrayList<ResidentModel>
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        resRecyclerView = findViewById(R.id.rvRes)
        resRecyclerView.layoutManager = LinearLayoutManager(this)
        resRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        repList = arrayListOf()

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()

        getReportData()

    }

    private fun getReportData() {
        resRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        databaseReference = FirebaseDatabase.getInstance().getReference("Resident_Reports")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                repList.clear()
                for (repSnap in snapshot.children) {
                    val repData = repSnap.getValue(ResidentModel::class.java)
                    if (repData != null) {
                        repList.add(repData)
                    }
                }

                val mAdapter = ResAdapter(repList)
                resRecyclerView.adapter = mAdapter

                mAdapter.setOnItemClickListener(object : ResAdapter.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        val intent = Intent(this@FetchingActivity, ReportDetailsActivity::class.java)

                        //put extras
                        intent.putExtra("resId", repList[position].resId)
                        intent.putExtra("resName", repList[position].resName)
                        intent.putExtra("resCurLoc", repList[position].resCurLoc)
                        intent.putExtra("resReport", repList[position].resReport)
                        startActivity(intent)
                    }
                })

                resRecyclerView.visibility = View.VISIBLE
                tvLoadingData.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled if needed
            }
        })
    }



}
