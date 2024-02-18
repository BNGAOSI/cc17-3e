package com.example.fulldashboardededdneddy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulldashboardededdneddy.adapter.StatusAdapter
import com.example.fulldashboardededdneddy.model.StatusViewModel

private lateinit var viewModel: StatusViewModel
private lateinit var documentStatusRecyclerView: RecyclerView
lateinit var adapter: StatusAdapter

class DocumentStatusFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_document_status, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        documentStatusRecyclerView = view.findViewById(R.id.documentStatusRecyclerView)
        documentStatusRecyclerView.layoutManager = LinearLayoutManager(context)
        documentStatusRecyclerView.setHasFixedSize(true)
        adapter = StatusAdapter()
        documentStatusRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(StatusViewModel::class.java)

        viewModel.allDocuments.observe(viewLifecycleOwner, Observer {
            adapter.updateDocumentList(it)
        })
    }
}