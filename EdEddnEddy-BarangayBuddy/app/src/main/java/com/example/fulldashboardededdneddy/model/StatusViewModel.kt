
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fulldashboardededdneddy.model.StatusDocuments
import com.google.firebase.auth.FirebaseAuth

class StatusViewModel : ViewModel() {
    private val repository: StatusRepository = StatusRepository().getInstance()
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
            val documentType = "Barangay Clearance"
            repository.loadDocumentsByType(documentType, uid, _allDocuments)
        }
    }


}
