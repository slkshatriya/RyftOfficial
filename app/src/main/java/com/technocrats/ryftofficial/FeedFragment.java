package com.technocrats.ryftofficial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class FeedFragment extends Fragment {
    List<Model> ModelList=new ArrayList<>();

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    CustomAdapter adapter;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_feed, container, false);


        List<Model> productsList = new ArrayList<>();
        productsList.add(new Model("android", "java", "Android Develop"));
        productsList.add(new Model("android", "java", "Android Develop"));
        productsList.add(new Model("android", "java", "Android Develop"));
        productsList.add(new Model("android", "java", "Android Develop"));

        setProdItemRecycler(productsList);

        return view;
    }

    private void setProdItemRecycler(List<Model> productsList){

        mRecyclerView= view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new CustomAdapter(this, productsList);
        mRecyclerView.setAdapter(adapter);

    }

}
