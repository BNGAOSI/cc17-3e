package com.example.fulldashboardededdneddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.fulldashboardededdneddy.model.QueriesModel
import com.example.fulldashboardededdneddy.model.UrgentModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Urgent : AppCompatActivity() {

    private lateinit var etUrgName: EditText
    private lateinit var etUrgField: EditText
    private lateinit var btnSaveDataUrg: ImageButton

    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urgent)

        val backbutton = findViewById<ImageButton>(R.id.btnUrgBack)
        backbutton.setOnClickListener {
            val intent = Intent(this, reportAct::class.java)
            startActivity(intent)
        }

        etUrgName = findViewById(R.id.etUrgName)
        etUrgField = findViewById(R.id.etUrgField)
        btnSaveDataUrg = findViewById(R.id.btnSaveDataUrg)

        databaseReference = FirebaseDatabase.getInstance().getReference("Urgent")

        btnSaveDataUrg.setOnClickListener {
            saveUrgent()

        }
    }

    private fun saveUrgent() {
        //getting Values
        val urgName = etUrgName.text.toString()
        val urgField = etUrgField.text.toString()

        if (urgName.isEmpty()) {
            etUrgName.error = "Please Enter Name"
            return
        }
        if (urgField.isEmpty()) {
            etUrgField.error = "Please Enter your urgent message"
            return
        }

        val urgId = databaseReference.push().key!!

        val urgent = UrgentModel(urgId, urgName, urgField)

        databaseReference.child(urgId).setValue(urgent)
            .addOnCompleteListener {
                Toast.makeText(this, "Urgent message sent successfully", Toast.LENGTH_LONG).show()

                etUrgName.text.clear()
                etUrgField.text.clear()

            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()

            }
    }
}