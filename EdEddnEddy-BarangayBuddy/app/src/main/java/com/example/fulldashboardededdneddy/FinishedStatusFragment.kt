package com.example.fulldashboardededdneddy

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
import com.example.fulldashboardededdneddy.adapter.FinishedStatusAdapter
import com.example.fulldashboardededdneddy.model.FinishedStatusViewModel


class FinishedStatusFragment : Fragment() {

    private lateinit var viewModel: FinishedStatusViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FinishedStatusAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_finished_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.documentStatusRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        adapter = FinishedStatusAdapter()
        recyclerView.adapter = adapter

        val noDocumentsMessage = view.findViewById<TextView>(R.id.noDocumentsMessage)

        viewModel = ViewModelProvider(this).get(FinishedStatusViewModel::class.java)

        viewModel.allFinishedDocs.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                noDocumentsMessage.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                noDocumentsMessage.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                adapter.updateDocumentList(it)
            }
        })
    }
}