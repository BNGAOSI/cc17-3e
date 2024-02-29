package com.example.fulldashboardededdneddy

import StatusViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulldashboardededdneddy.adapter.StatusAdapter

class DocumentStatusFragment : Fragment() {

    private lateinit var viewModel: StatusViewModel
    private lateinit var documentStatusRecyclerView: RecyclerView
    private lateinit var adapter: StatusAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.barangayclear_status_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        documentStatusRecyclerView = view.findViewById(R.id.documentStatusRecyclerView)
        documentStatusRecyclerView.layoutManager = LinearLayoutManager(context)
        documentStatusRecyclerView.setHasFixedSize(true)
        adapter = StatusAdapter()
        documentStatusRecyclerView.adapter = adapter

        val noDocumentsMessage = view.findViewById<TextView>(R.id.noDocumentsMessage)

        viewModel = ViewModelProvider(this).get(StatusViewModel::class.java)

        viewModel.allDocuments.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                noDocumentsMessage.visibility = View.VISIBLE
                documentStatusRecyclerView.visibility = View.GONE
            } else {
                noDocumentsMessage.visibility = View.GONE
                documentStatusRecyclerView.visibility = View.VISIBLE
                adapter.updateDocumentList(it)
            }
        })
    }
}
