package com.technocrats.ryftofficial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {
    FeedFragment feedItem;
    List<Model> modelList;

    public CustomAdapter(FeedFragment feedItem,List<Model> modelList)
    {
        this.feedItem=feedItem;
        this.modelList=modelList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick() {

            }

            @Override
            public void onItemClick(View view, int position) {

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTitle.setText(modelList.get(position).getTitle());
        holder.TechUsed1.setText(modelList.get(position).getTechUsed1());
        holder.mTechUsed2.setText(modelList.get(position).getTechUsed2());
        holder.mProjectImg.setImageDrawable(null);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

}

