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
    TextView techUsed2TextView,techUsed1TextView,
            titleTextView,techUsed3TextView,techUsed4TextView,
            step1TextView,step2TextView,step3TextView,step4TextView;
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
        techUsed3TextView=getView().findViewById(R.id.techUsed3);
        techUsed4TextView=getView().findViewById(R.id.techUsed4);
        step1TextView=getView().findViewById(R.id.longDescription);
        step2TextView=getView().findViewById(R.id.longDescription1);
        step3TextView=getView().findViewById(R.id.longDescription2);
        step4TextView=getView().findViewById(R.id.longDescription3);

        step1TextView.setText(intent.getExtras().getString("step 1"));
        step2TextView.setText(intent.getExtras().getString("step 2"));
        step3TextView.setText(intent.getExtras().getString("step 3"));
        step4TextView.setText(intent.getExtras().getString("step 4"));
        titleTextView.setText(intent.getExtras().getString("title"));
        techUsed1TextView.setText(intent.getExtras().getString("tech used 1"));
        techUsed2TextView.setText(intent.getExtras().getString("tech used 2"));
        techUsed3TextView.setText(intent.getExtras().getString("tech used 3"));
        techUsed4TextView.setText(intent.getExtras().getString("tech used 4"));
    }
}
