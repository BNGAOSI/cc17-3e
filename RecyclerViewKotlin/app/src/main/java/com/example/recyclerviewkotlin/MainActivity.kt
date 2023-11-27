package com.example.recyclerviewkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

private lateinit var recyclerView: RecyclerView
private lateinit var dataList: ArrayList<DataClass>
lateinit var imageList: Array<Int>
lateinit var titleList: Array<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageList = arrayOf(
            R.drawable.imge,
            R.drawable.imge2,
            R.drawable.imge3,
            R.drawable.imge4
        )

        titleList = arrayOf(
            "Certificate of Residency",
            "Barangay Clearance",
            "Barangay ID card",
            "Barangay Certificate"
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataClass>()
        getData()
    }

    private fun getData(){
        for(i in imageList.indices){
            val dataClass = DataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = AdapterClass (dataList)
    }
}