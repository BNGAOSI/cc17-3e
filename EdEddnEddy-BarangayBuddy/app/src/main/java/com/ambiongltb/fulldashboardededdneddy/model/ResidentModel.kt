package com.ambiongltb.fulldashboardededdneddy.model

data class ResidentModel(
    var resId: String? = null,
    var resName: String? = null,
    var resCurLoc: String? = null,
    var resReport: String? = null,
    val phoneNumber: String = "",
    val timeStamp: Long? = 0,
    var reportType: String? = null
)