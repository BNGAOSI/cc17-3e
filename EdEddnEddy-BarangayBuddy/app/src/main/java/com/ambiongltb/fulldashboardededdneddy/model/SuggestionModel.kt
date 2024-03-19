package com.ambiongltb.fulldashboardededdneddy.model

data class SuggestionModel(
    var sugId: String? = null,
    var sugName: String? = null,
    var suggest: String? = null,
    val timeStamp: Map<String, Any>? = null,
    var healthChecked: Boolean = false,
    var educationChecked: Boolean = false,
    var sportsChecked: Boolean = false,
    var barangayImprovementsChecked: Boolean = false,
    var etcChecked: Boolean = false,
    var etcDetails: String? = null
)

