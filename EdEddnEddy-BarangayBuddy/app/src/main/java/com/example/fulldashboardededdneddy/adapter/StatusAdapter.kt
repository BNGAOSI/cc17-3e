package com.example.fulldashboardededdneddy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fulldashboardededdneddy.R
import com.example.fulldashboardededdneddy.model.StatusDocuments
import org.w3c.dom.Text

class StatusAdapter : RecyclerView.Adapter<StatusAdapter.MyViewHolder>() {

    private val documentList = ArrayList<StatusDocuments>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.status_item, parent, false
        )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return documentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = documentList[position]

        holder.fullName.text = currentitem.fullname
        holder.docType.text = currentitem.docType
        holder.dateTime.text = currentitem.date.toString()
    }

    fun updateDocumentList(documentList: List<StatusDocuments>) {

        this.documentList.clear()
        this.documentList.addAll(documentList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullName: TextView = itemView.findViewById(R.id.document_name)
        val docType: TextView = itemView.findViewById(R.id.document_type)
        val dateTime: TextView = itemView.findViewById(R.id.document_time)
    }

}