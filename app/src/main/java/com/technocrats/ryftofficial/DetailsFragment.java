package com.technocrats.ryftofficial;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DetailsFragment extends Fragment {
    TextView techUsed2TextView,techUsed1TextView,descriptionTextView,titleTextView;
    Intent intent;
    Button enrollNowButton;

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
        final String projectId=intent.getExtras().getString("projectId");
        titleTextView=getView().findViewById(R.id.dProjectTitle);
        techUsed1TextView=getView().findViewById(R.id.dtechUsed1);
        techUsed2TextView=getView().findViewById(R.id.dtechUsed2);
        imageView=getView().findViewById(R.id.projectImg);
        FirebaseAuth mauth=FirebaseAuth.getInstance();
        final FirebaseUser user=mauth.getCurrentUser();
        String userEmail= null;
        String userId = null;
        if (user != null) {
            userEmail = user.getEmail();
            userId=user.getUid();
        }
        enrollNowButton=getView().findViewById(R.id.enroll);
        final String finalUserEmail = userEmail;
        final String finalUserId=userId;
        enrollNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user!=null)
                {   final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

                    Query query=reference.child("enrollments").child(projectId)
                            .child(finalUserId).
                            equalTo(finalUserEmail);
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(!snapshot.exists())
                            {
                                HashMap<String,String> enrollment=new HashMap<>();
                                enrollment.put("user email", finalUserEmail);

                                reference.child("enrollments")
                                        .child(projectId)
                                        .child(finalUserId)
                                        .setValue(enrollment);
                                Toast.makeText(getContext(),"you have enrolled successfully"
                                        ,Toast.LENGTH_SHORT).show();
                            }
                            else
                                {
                                    Toast.makeText(getContext(),"you are already enrolled"
                                            ,Toast.LENGTH_SHORT).show();
                                }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
                else
                    {
                        Intent intent=new Intent(getContext(),MainActivity.class);
                        startActivity(intent);
                    }
            }
        });

        descriptionTextView.setText(intent.getExtras().getString("description"));
        titleTextView.setText(intent.getExtras().getString("title"));
        techUsed1TextView.setText(intent.getExtras().getString("tech used 1"));
        techUsed2TextView.setText(intent.getExtras().getString("tech used 2"));
        //imageView.setImageBitmap((Bitmap) intent.getExtras().getParcelable("image"));

    }
}
