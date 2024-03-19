package com.ambiongltb.fulldashboardededdneddy.adapter

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ambiongltb.fulldashboardededdneddy.R
import com.ambiongltb.fulldashboardededdneddy.model.StatusDocuments

class ResidencyStatusAdapter : RecyclerView.Adapter<ResidencyStatusAdapter.MyViewHolder>(){
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

        holder.fullName.text = currentitem.fullName
        holder.docType.text = currentitem.documentType
        holder.dateTime.text = currentitem.time.toString()
        holder.status.text = currentitem.status

        val timestamp = currentitem.time
        val timeAgo = DateUtils.getRelativeTimeSpanString(timestamp, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS)
        holder.dateTime.text = timeAgo.toString()
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
        val status: TextView = itemView.findViewById(R.id.document_status)

    }
}