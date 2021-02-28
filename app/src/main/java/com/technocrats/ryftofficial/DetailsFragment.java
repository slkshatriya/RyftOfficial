package com.technocrats.ryftofficial;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {
    TextView techUsed2TextView,techUsed1TextView,descriptionTextView,titleTextView;
    Intent intent;

    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        intent=getActivity().getIntent();
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        descriptionTextView=getView().findViewById(R.id.dlongDescription);
        titleTextView=getView().findViewById(R.id.dProjectTitle);
        techUsed1TextView=getView().findViewById(R.id.dtechUsed1);
        techUsed2TextView=getView().findViewById(R.id.dtechUsed2);
        imageView=getView().findViewById(R.id.projectImg);

        descriptionTextView.setText(intent.getExtras().getString("description"));
        titleTextView.setText(intent.getExtras().getString("title"));
        techUsed1TextView.setText(intent.getExtras().getString("tech used 1"));
        techUsed2TextView.setText(intent.getExtras().getString("tech used 2"));
        //imageView.setImageBitmap((Bitmap) intent.getExtras().getParcelable("image"));

    }
}
