package com.example.fulldashboardededdneddy.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class StatusDocuments(
    val age: String = "",
    val dateOfBirth: String = "",
    val documentType: String = "",
    val fullName: String = "",
    val gender: String = "",
    val phoneNumber: String = "",
    val presentAddress: String = "",
    val purpose: String = "",
    val status: String = "",
    val time: Long = 0
)


