package com.ambiongltb.fulldashboardededdneddy.Repository

import androidx.lifecycle.MutableLiveData
import com.ambiongltb.fulldashboardededdneddy.model.StatusDocuments
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class IndigencyStatusRepository {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("RequestedDocuments")

    fun getInstance(): IndigencyStatusRepository {
        return this
    }

    fun loadDocumentsByType(documentType: String, userId: String, liveData: MutableLiveData<List<StatusDocuments>>) {
        val documentList = mutableListOf<StatusDocuments>()

        val query: Query = databaseReference.child(documentType).child(userId).orderByChild("time")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                documentList.clear()
                for (documentSnapshot in snapshot.children.reversed()) { // Reversed to get the latest documents first
                    val document = documentSnapshot.getValue(StatusDocuments::class.java)
                    document?.let { documentList.add(it) }
                }
                liveData.postValue(documentList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error
            }
        })
    }
}