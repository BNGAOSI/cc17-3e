package com.example.fulldashboardededdneddy.adapter

import android.icu.text.CaseMap.Title
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fulldashboardededdneddy.Announcement
import com.example.fulldashboardededdneddy.R

class AnnouncementAdapter(private val announcementList : ArrayList<Announcement>): RecyclerView.Adapter<AnnouncementAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnnouncementAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.announcement_list, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnnouncementAdapter.MyViewHolder, position: Int) {
        val announcement : Announcement = announcementList[position]
        holder.Title.text = announcement.Title
        holder.Announcement.text = announcement.Announcement
    }

    override fun getItemCount(): Int {

       return announcementList.size
    }
    public class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val Title : TextView = itemView.findViewById(R.id.tvTitle)
        val Announcement : TextView = itemView.findViewById(R.id.tvAnnouncement)

    }

}