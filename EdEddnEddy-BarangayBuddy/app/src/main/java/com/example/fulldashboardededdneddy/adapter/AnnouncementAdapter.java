package com.example.fulldashboardededdneddy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fulldashboardededdneddy.AnnouncementDetailActivity;
import com.example.fulldashboardededdneddy.R;
import com.example.fulldashboardededdneddy.data.AnnouncementDataClass;

import org.w3c.dom.Text;

import java.util.List;

public class AnnouncementAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;

    public AnnouncementAdapter(Context context, List<AnnouncementDataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    private List<AnnouncementDataClass> dataList;

    public AnnouncementAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_recycler_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getImageUrl()).into(holder.recImage);
        holder.recTitle.setText(dataList.get(position).getTitle());
        holder.recDesc.setText(dataList.get(position).getDescription());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AnnouncementDetailActivity.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getImageUrl());
                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getTitle());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recTitle, recDesc, recDate;
    LinearLayout recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.announcement_recImage);
        recCard = itemView.findViewById(R.id.announcement_recCard);
        recDesc = itemView.findViewById(R.id.announcement_recDesc);
        recTitle = itemView.findViewById(R.id.announcement_recTitle);
        recDate = itemView.findViewById(R.id.announcement_recDate);

    }
}
