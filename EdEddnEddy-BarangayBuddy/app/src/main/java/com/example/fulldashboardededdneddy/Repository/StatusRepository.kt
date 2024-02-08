package com.example.fulldashboardededdneddy.Repository

import com.example.fulldashboardededdneddy.model.Documents
import com.example.fulldashboardededdneddy.model.StatusDocuments
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StatusRepository() {
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("RequestedDocuments")

    @Volatile
    private var INSTANCE: StatusRepository? = null
    fun getInstance(): StatusRepository {
        return INSTANCE ?: synchronized(this) {
            val instance = StatusRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadDocuments(DocumentList : MutableList<List<StatusDocuments>>) {

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}