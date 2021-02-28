package com.technocrats.ryftofficial;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class DevFragment extends Fragment {
    TextView techUsed2TextView,techUsed1TextView,titleTextView;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        intent=getActivity().getIntent();
        return inflater.inflate(R.layout.fragment_dev, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleTextView=getView().findViewById(R.id.ProjectTitle);
        techUsed1TextView=getView().findViewById(R.id.techUsed1);
        techUsed2TextView=getView().findViewById(R.id.techUsed2);

        titleTextView.setText(intent.getExtras().getString("title"));
        techUsed1TextView.setText(intent.getExtras().getString("tech used 1"));
        techUsed2TextView.setText(intent.getExtras().getString("tech used 2"));
    }
}
