package com.example.fulldashboardededdneddy.data

import com.example.fulldashboardededdneddy.R
import com.example.fulldashboardededdneddy.model.Documents

class Datasource {
    fun loadAffirmations(): List<Documents>{
        return  listOf<Documents>(
            Documents(R.string.doc1, R.drawable.imge),
            Documents(R.string.doc2, R.drawable.imge2),
            Documents(R.string.doc3, R.drawable.imge3),
            Documents(R.string.doc4, R.drawable.imge4),
        )
    }
}