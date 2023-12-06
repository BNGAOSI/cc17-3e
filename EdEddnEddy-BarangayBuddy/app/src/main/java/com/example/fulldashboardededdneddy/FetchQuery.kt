package com.example.fulldashboardededdneddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulldashboardededdneddy.adapter.QueryAdapter
import com.example.fulldashboardededdneddy.adapter.ResAdapter
import com.example.fulldashboardededdneddy.model.QueriesModel
import com.example.fulldashboardededdneddy.model.ResidentModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FetchQuery : AppCompatActivity() {

    private lateinit var queryRecyclerView: RecyclerView
    private lateinit var tvLoadingQuery: TextView
    private lateinit var queryList: ArrayList<QueriesModel>
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_query)

        queryRecyclerView = findViewById(R.id.rvQuery)
        queryRecyclerView.layoutManager = LinearLayoutManager(this)
        queryRecyclerView.setHasFixedSize(true)
        tvLoadingQuery = findViewById(R.id.tvLoadingQuery)

        queryList = arrayListOf<QueriesModel>()

        getQueriesData()

    }

    private fun getQueriesData() {
        queryRecyclerView.visibility = View.GONE
        tvLoadingQuery.visibility = View.VISIBLE

        databaseReference = FirebaseDatabase.getInstance().getReference("Queries")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                queryList.clear()
                if (snapshot.exists()){
                    for (repSnap in snapshot.children){
                        val queryData = repSnap.getValue(QueriesModel::class.java)
                        queryList.add(queryData!!)
                    }
                    val mAdapter = QueryAdapter(queryList)
                    queryRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : QueryAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchQuery, QueryDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("askId", queryList[position].askId)
                            intent.putExtra("askName", queryList[position].askName)
                            intent.putExtra("askField", queryList[position].askField)
                            startActivity(intent)
                        }

                    })

                    queryRecyclerView.visibility = View.VISIBLE
                    tvLoadingQuery.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}