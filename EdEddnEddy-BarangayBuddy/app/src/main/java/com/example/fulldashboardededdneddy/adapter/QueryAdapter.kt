package com.example.fulldashboardededdneddy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fulldashboardededdneddy.R
import com.example.fulldashboardededdneddy.model.QueriesModel
import com.example.fulldashboardededdneddy.model.ResidentModel

class QueryAdapter (private val queryList: ArrayList<QueriesModel>) : RecyclerView.Adapter<QueryAdapter.ViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.query_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentQuery = queryList[position]
        holder.tvAskName.text = currentQuery.askName
    }



    override fun getItemCount(): Int {
        return queryList.size
    }

    class ViewHolder( itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvAskName : TextView = itemView.findViewById(R.id.tvResNameQuery)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}