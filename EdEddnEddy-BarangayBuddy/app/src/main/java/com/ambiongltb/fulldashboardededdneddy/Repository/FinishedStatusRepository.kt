package com.ambiongltb.fulldashboardededdneddy.Repository

import androidx.lifecycle.MutableLiveData
import com.ambiongltb.fulldashboardededdneddy.model.FinishedStatusDocuments
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class FinishedStatusRepository {

    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("FinishedDocuments")

    fun getInstance(): FinishedStatusRepository {
        return this
    }

    fun loadAllFinishedDocuments(uid: String, liveData: MutableLiveData<List<FinishedStatusDocuments>>) {
        val documentList = mutableListOf<FinishedStatusDocuments>()

        val query: Query = databaseReference.child(uid)

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                documentList.clear()
                for (documentSnapshot in snapshot.children.reversed()) {
                    val document = documentSnapshot.getValue(FinishedStatusDocuments::class.java)
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
