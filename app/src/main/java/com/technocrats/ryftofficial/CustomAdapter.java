package com.technocrats.ryftofficial;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
        Bitmap bitmap;
        ImageDownloader task= new ImageDownloader();
        try {
            bitmap=task.execute(modelList.get(position).getProjectImageUrl()).get();
        } catch (ExecutionException | InterruptedException e) {
            bitmap=null;
            e.printStackTrace();
        }
        holder.mTitle.setText(modelList.get(position).getTitle());
        holder.TechUsed1.setText(modelList.get(position).getTechUsed1());
        holder.TechUsed2.setText(modelList.get(position).getTechUsed2());
        holder.description.setText(modelList.get(position).getDescription());
        holder.mProjectImg.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class ImageDownloader extends AsyncTask<String,Void,Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... urls) {
            try
            {
                URL url=new URL(urls[0]);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream=httpURLConnection.getInputStream();
                return BitmapFactory.decodeStream(inputStream);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }

        }

    }
}


