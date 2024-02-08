package com.example.fulldashboardededdneddy.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fulldashboardededdneddy.Repository.StatusRepository

class StatusViewModel: ViewModel() {
    private val repository : StatusRepository
    private val _allDocuments = MutableLiveData<List<StatusDocuments>>()
    val allDocuments : LiveData<List<StatusDocuments>> = _allDocuments

    init {
        repository = StatusRepository().getInstance()
        repository.loadDocuments(_allDocuments)
    }
}