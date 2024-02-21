package com.example.fulldashboardededdneddy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulldashboardededdneddy.adapter.ResidencyStatusAdapter
import com.example.fulldashboardededdneddy.model.ResidencyStatusViewModel


class ResidencyStatusFragment : Fragment() {

    private lateinit var viewModel: ResidencyStatusViewModel
    private lateinit var documentStatusRecyclerView: RecyclerView
    private lateinit var adapter: ResidencyStatusAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_residency_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        documentStatusRecyclerView = view.findViewById(R.id.documentStatusRecyclerView)
        documentStatusRecyclerView.layoutManager = LinearLayoutManager(context)
        documentStatusRecyclerView.setHasFixedSize(true)
        adapter = ResidencyStatusAdapter()
        documentStatusRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(ResidencyStatusViewModel::class.java)

        viewModel.allDocuments.observe(viewLifecycleOwner, Observer {
            adapter.updateDocumentList(it)
        })
    }
}