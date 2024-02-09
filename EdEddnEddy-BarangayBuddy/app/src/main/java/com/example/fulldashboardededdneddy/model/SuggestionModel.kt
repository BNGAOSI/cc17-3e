package com.example.fulldashboardededdneddy.model

data class SuggestionModel(
    var sugId: String? = null,
    var sugName: String? = null,
    var suggest: String? = null,
    val timeStamp: Map<String, String>? = null

)