package com.example.fulldashboardededdneddy

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.fulldashboardededdneddy.model.SuggestionModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Suggest : AppCompatActivity() {

    private lateinit var etSugName: EditText
    private lateinit var etSuggest: EditText
    private lateinit var btnSaveData: Button

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggest)


        val backbutton = findViewById<ImageButton>(R.id.goback1)
        backbutton.setOnClickListener {
            val intent = Intent(this, reportAct::class.java)
            startActivity(intent)
        }

        val proceedSuggest1 = findViewById<Button>(R.id.btnSaveData)
        proceedSuggest1.setOnClickListener {
            val intent = Intent(this, SuggestNext::class.java)
            startActivity(intent)
        }

        etSugName = findViewById(R.id.etSugName)
        etSuggest = findViewById(R.id.etSuggest)
        btnSaveData = findViewById(R.id.btnSaveData)

        databaseReference = FirebaseDatabase.getInstance().getReference("Suggestions")

        btnSaveData.setOnClickListener {
            saveSuggestionData()
        }

    }

    private fun saveSuggestionData() {
        //getting values
        val sugName = etSugName.text.toString()
        val suggest = etSuggest.text.toString()

        if (suggest.isEmpty()) {
            etSuggest.error = "Please Enter Suggestion"
            return
        }

        val sugId = databaseReference.push().key!!

        val suggestion = SuggestionModel(sugId, sugName, suggest)

        databaseReference.child(sugId).setValue(suggestion)
            .addOnCompleteListener{
                Toast.makeText(this, "Suggestion sent successfully" , Toast.LENGTH_LONG).show()

                etSugName.text.clear()
                etSuggest.text.clear()

            }.addOnFailureListener{
                err ->
                Toast.makeText(this, "Error ${err.message}" , Toast.LENGTH_LONG).show()
            }



    }
}