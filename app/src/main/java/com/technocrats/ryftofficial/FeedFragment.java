package com.technocrats.ryftofficial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class FeedFragment extends Fragment {
    List<Model> ModelList=new ArrayList<>();

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    CustomAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        mRecyclerView= view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        //add data into model here
        Model model=new Model();

        ModelList.add(model);

        adapter=new CustomAdapter(FeedFragment.this,ModelList);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

}
