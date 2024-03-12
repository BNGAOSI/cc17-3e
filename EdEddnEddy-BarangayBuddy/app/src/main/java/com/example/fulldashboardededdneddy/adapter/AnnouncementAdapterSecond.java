package com.example.fulldashboardededdneddy.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fulldashboardededdneddy.AnnouncementDetailActivity;
import com.example.fulldashboardededdneddy.R;
import com.example.fulldashboardededdneddy.data.AnnouncementDataClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class AnnouncementAdapterSecond extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<AnnouncementDataClass> dataList;

    public void setData(List<AnnouncementDataClass> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public AnnouncementAdapterSecond(Context context, List<AnnouncementDataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getImageUrl()).into(holder.recImage);
        holder.recTitle.setText(dataList.get(position).getTitle());
        holder.recCategory.setText(dataList.get(position).getCategory());

        // Retrieve timestamp and set the formatted date in recDate TextView
        long timestamp = dataList.get(position).getTimestamp();
        String formattedDate = getTimeAgo(timestamp);
        holder.recDate.setText(formattedDate);


        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AnnouncementDetailActivity.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getImageUrl());
                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("Category", dataList.get(holder.getAdapterPosition()).getCategory());
                intent.putExtra("Date", formattedDate);

                context.startActivity(intent);
            }
        });


    }

    private boolean isAnnouncementNew(long timestamp) {
        long newThreshold = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1); // 1 day
        return timestamp > newThreshold;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private String getTimeAgo(long timestamp) {
        long currentTime = System.currentTimeMillis();

        return DateUtils.getRelativeTimeSpanString(timestamp, currentTime, DateUtils.MINUTE_IN_MILLIS).toString();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView recImage;
    TextView recTitle, recDate, recCategory;
    LinearLayout recCard;
    ImageView newIndicator;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.announcement_recImage);
        recCard = itemView.findViewById(R.id.announcement_recCard);
        recTitle = itemView.findViewById(R.id.announcement_recTitle);
        recDate = itemView.findViewById(R.id.announcement_recDate);
        recCategory = itemView.findViewById(R.id.announcement_recCategory);


    }

    public static String getTimeDate(long timestamp) {
        try {
            Date netDate = (new Date(timestamp));
            SimpleDateFormat sfd = new SimpleDateFormat("MMM d', 'yyyy", Locale.getDefault());
            return sfd.format(netDate);
        } catch (Exception e) {
            return "date";
        }
    }

}

