package com.example.fulldashboardededdneddy.reportactvities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.fulldashboardededdneddy.R
import com.example.fulldashboardededdneddy.model.ResidentModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var etResName: EditText
    private lateinit var etResCurLoc: EditText
    private lateinit var etResRep: EditText
    private lateinit var etResPhoneNumber: EditText
    private lateinit var btnSubmitReport: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var reportTypeSpinner: Spinner

    private lateinit var dbDatabaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        toolbar = findViewById(R.id.appToolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = "Report"
            setDisplayHomeAsUpEnabled(true)
        }
        toolbar.navigationIcon?.setTint(ContextCompat.getColor(this, R.color.white))


        etResName = findViewById(R.id.etResName)
        etResCurLoc = findViewById(R.id.etResCurLoc)
        reportTypeSpinner = findViewById(R.id.report_type_spinner)
        etResRep = findViewById(R.id.etResRep)
        etResPhoneNumber = findViewById(R.id.etResPhoneNumber)
        btnSubmitReport = findViewById(R.id.btnSave)

        auth = FirebaseAuth.getInstance()


        dbDatabaseReference = FirebaseDatabase.getInstance().getReference("Resident_Reports")

        btnSubmitReport.setOnClickListener {
            saveReportData()
        }

    }

    private fun saveReportData() {
        // Check if the user is authenticated
        val currentUser = auth.currentUser
        if (currentUser == null) {
            // Handle the case when the user is not authenticated
            // You might want to redirect the user to the login screen
            return
        }

        // Getting values
        val resName = etResName.text.toString()
        val resCurLoc = etResCurLoc.text.toString()
        val reportType = reportTypeSpinner.selectedItem.toString()
        val resPhoneNumber = etResPhoneNumber.text.toString()
        val resReport = etResRep.text.toString()

        if (resName.isEmpty()) {
            etResName.error = "Please enter your Name"
            return
        }
        if (resPhoneNumber.isEmpty()) {
            etResPhoneNumber.error = "Please enter your Phone Number"
            return
        }

        if (resCurLoc.isEmpty()) {
            etResCurLoc.error = "Please enter Current Location"
            return
        }

        if (resReport.isEmpty()) {
            etResRep.error = "Please enter your Report"
            return
        }


        val userId = currentUser.uid
        val resId = dbDatabaseReference.child(userId).push().key!!
        val timeStamp = System.currentTimeMillis()

        val resident = ResidentModel(
            resId, resName, resCurLoc, resReport, resPhoneNumber, timeStamp, reportType
        )

        dbDatabaseReference.child(userId).child(resId).setValue(resident).addOnCompleteListener {
            Toast.makeText(this, "Report successfully submitted", Toast.LENGTH_LONG).show()

            etResName.text.clear()
            etResCurLoc.text.clear()
            etResRep.text.clear()
            etResPhoneNumber.text.clear()

        }.addOnFailureListener { err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }
    }


}