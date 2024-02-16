package com.example.fulldashboardededdneddy.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fulldashboardededdneddy.Repository.StatusRepository
import com.google.firebase.auth.FirebaseAuth

class StatusViewModel : ViewModel() {
    private val repository: StatusRepository
    private val _allDocuments = MutableLiveData<List<StatusDocuments>>()
    val allDocuments: LiveData<List<StatusDocuments>> = _allDocuments

    init {
        repository = StatusRepository().getInstance()

        // Get the UID of the currently logged-in user
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid

        // If the UID is not null, proceed to load documents
        uid?.let {
            // Replace "Barangay Clearance" with the actual document type
            val documentType = "Barangay Clearance"
            repository.loadDocuments(uid, documentType, _allDocuments)
        }
    }
}

