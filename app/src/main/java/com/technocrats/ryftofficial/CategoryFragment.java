package com.technocrats.ryftofficial;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class CategoryFragment extends Fragment {
    CardView ml,blockChain,Ios,Game,web,android;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ml=getView().findViewById(R.id.mark);
        blockChain=getView().findViewById(R.id.teach);
        Ios=getView().findViewById(R.id.med);
        Game=getView().findViewById(R.id.eng);
        web=getView().findViewById(R.id.it);
        android=getView().findViewById(R.id.design);

        ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),FeedFragment.class);
                intent.putExtra("category","ml");
                startActivity(intent);
            }
        });
        Ios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),FeedFragment.class);
                intent.putExtra("category","ios");
                startActivity(intent);
            }
        });
        Game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),FeedFragment.class);
                intent.putExtra("category","game");
                startActivity(intent);
            }
        });
        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),FeedFragment.class);
                intent.putExtra("category","android");
                startActivity(intent);
            }
        });
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),FeedFragment.class);
                intent.putExtra("category","web");
                startActivity(intent);
            }
        });
        blockChain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),FeedFragment.class);
                intent.putExtra("category","blockChain");
                startActivity(intent);
            }
        });
    }
}
