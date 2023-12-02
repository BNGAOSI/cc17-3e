package com.example.fulldashboardededdneddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.fulldashboardededdneddy.model.QueriesModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AskQuery : AppCompatActivity() {

    private lateinit var etAskName: EditText
    private lateinit var etAskField: EditText
    private lateinit var btnSaveDataAsk: ImageButton

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_query)

        val backbutton = findViewById<ImageButton>(R.id.askback)
        backbutton.setOnClickListener {
            val intent = Intent(this, reportAct::class.java)
            startActivity(intent)
        }

        val proceedAsk = findViewById<ImageButton>(R.id.btnSaveDataAsk)
        proceedAsk.setOnClickListener {
            val intent = Intent(this, AskQueryDONE::class.java)
            startActivity(intent)
        }

        etAskName = findViewById(R.id.etAskName)
        etAskField = findViewById(R.id.etAskField)
        btnSaveDataAsk = findViewById(R.id.btnSaveDataAsk)

        databaseReference = FirebaseDatabase.getInstance().getReference("Queries")

        btnSaveDataAsk.setOnClickListener {
            saveQueries()
        }
    }

    private fun saveQueries() {
        //getting Values
        val askName = etAskName.text.toString()
        val askField = etAskField.text.toString()

        if (askName.isEmpty()){
            etAskName.error = "Please Enter Name"
            return
        }
        if (askField.isEmpty()){
            etAskField.error = "Please Enter Query"
            return
        }

        val askId = databaseReference.push().key!!

        val queries = QueriesModel(askId, askName, askField)

        databaseReference.child(askId).setValue(queries)
            .addOnCompleteListener{
                Toast.makeText(this, "Query sent successfully", Toast.LENGTH_LONG).show()

                etAskName.text.clear()
                etAskField.text.clear()

            }.addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()

            }
    }
}