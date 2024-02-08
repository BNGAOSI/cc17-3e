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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DocumentStatusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private lateinit var viewModel: StatusViewModel
private lateinit var documentStatusRecyclerView: RecyclerView
lateinit var adapter: StatusAdapter

class DocumentStatusFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_document_status, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DocumentStatusFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DocumentStatusFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
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