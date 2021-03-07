package com.technocrats.ryftofficial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class ChatFragment extends Fragment {
    ArrayAdapter arrayAdapter;
    ArrayList<String> groups;
    ArrayList<String> keys;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        return view;
    }

   /* @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        groups=new ArrayList<String>();
        keys=new ArrayList<String>();
        ListView listView=getView().findViewById(R.id.listView);
        arrayAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,groups);
        listView.setAdapter(arrayAdapter);
        FirebaseAuth mauth=FirebaseAuth.getInstance();
        FirebaseUser user=mauth.getCurrentUser();
        final String userEmail=user.getEmail();

        //get list of enrolled chat groups from firebase

        
    }*/
}
