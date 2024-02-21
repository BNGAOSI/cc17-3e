package com.example.fulldashboardededdneddy.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fulldashboardededdneddy.Repository.FinishedStatusRepository
import com.google.firebase.auth.FirebaseAuth

class FinishedStatusViewModel: ViewModel(){

    private val repository: FinishedStatusRepository = FinishedStatusRepository().getInstance()

    private val _allFinishedDocs = MutableLiveData<List<FinishedStatusDocuments>>()
    val allFinishedDocs: LiveData<List<FinishedStatusDocuments>> = _allFinishedDocs



    init {
        loadAllFinishedDocuments()
    }

    private fun loadAllFinishedDocuments() {
        // Get the UID of the currently logged-in user
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid

        // If the UID is not null, proceed to load all finished documents
        uid?.let {
            repository.loadAllFinishedDocuments(uid, _allFinishedDocs)
        }
    }
}