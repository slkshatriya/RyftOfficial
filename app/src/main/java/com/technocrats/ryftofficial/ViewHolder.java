package com.technocrats.ryftofficial;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    private com.technocrats.ryftofficial.ViewHolder.ClickListener mClickListener;
    public interface  ClickListener{
        void onItemClick();

        void onItemClick(View view, int position);
    }

    public void setOnClickListener(com.technocrats.ryftofficial.ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
