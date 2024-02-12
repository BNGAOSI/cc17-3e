package com.example.fulldashboardededdneddy.model

data class ResidentModel(
    var resId: String? = null,
    var resName: String? = null,
    var resCurLoc: String? = null,
    var resReport: String? = null,
    val timeStamp: Long? = 0,
)