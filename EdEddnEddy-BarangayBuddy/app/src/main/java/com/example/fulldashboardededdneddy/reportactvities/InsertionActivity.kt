package com.example.fulldashboardededdneddy.reportactvities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fulldashboardededdneddy.R
import com.example.fulldashboardededdneddy.model.ResidentModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class  InsertionActivity : AppCompatActivity() {

    private lateinit var etResName: EditText
    private lateinit var etResCurLoc: EditText
    private lateinit var etResRep: EditText
    private lateinit var btnSubmitReport: Button

    private lateinit var dbDatabaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        etResName = findViewById(R.id.etResName)
        etResCurLoc = findViewById(R.id.etResCurLoc)
        etResRep = findViewById(R.id.etResRep)
        btnSubmitReport = findViewById(R.id.btnSave)

        dbDatabaseReference = FirebaseDatabase.getInstance().getReference("Resident_Reports")

        btnSubmitReport.setOnClickListener {
            saveReportData()
        }

    }

    private fun saveReportData() {
        //getting values
        val resName = etResName.text.toString()
        val resCurLoc = etResCurLoc.text.toString()
        val resReport = etResRep.text.toString()

        if (resName.isEmpty()){
            etResName.error = "Please enter your Name"
        }

        if (resCurLoc.isEmpty()){
            etResCurLoc.error = "Please enter Current Location"
        }

        if (resReport.isEmpty()){
            etResRep.error = "Please enter your Report"
        }

        val resId = dbDatabaseReference.push().key!!

        val resident = ResidentModel(resId, resName, resCurLoc, resReport)

        dbDatabaseReference.child(resId).setValue(resident)
            .addOnCompleteListener{
                Toast.makeText(this, "Report successfully submitted", Toast.LENGTH_LONG).show()

                etResName.text.clear()
                etResCurLoc.text.clear()
                etResRep.text.clear()

            }.addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}