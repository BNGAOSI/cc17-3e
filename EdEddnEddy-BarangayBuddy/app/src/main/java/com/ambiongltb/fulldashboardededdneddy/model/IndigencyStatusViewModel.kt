package com.ambiongltb.fulldashboardededdneddy.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ambiongltb.fulldashboardededdneddy.Repository.IndigencyStatusRepository
import com.google.firebase.auth.FirebaseAuth

class IndigencyStatusViewModel: ViewModel() {
    private val repository: IndigencyStatusRepository = IndigencyStatusRepository().getInstance()
    private val _allDocuments = MutableLiveData<List<StatusDocuments>>()
    val allDocuments: LiveData<List<StatusDocuments>> = _allDocuments

    init {
        loadDocuments()
    }

    private fun loadDocuments() {
        // Get the UID of the currently logged-in user
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid

        // If the UID is not null, proceed to load documents
        uid?.let {
            val documentType = "Indigency"
            repository.loadDocumentsByType(documentType, uid, _allDocuments)
        }
    }
}