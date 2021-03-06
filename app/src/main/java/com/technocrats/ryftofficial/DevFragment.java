package com.technocrats.ryftofficial;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class DevFragment extends Fragment {
    TextView techUsed2TextView,techUsed1TextView,
            titleTextView,techUsed3TextView,techUsed4TextView,
            step1TextView,step2TextView,step3TextView,step4TextView;
    EditText submitLinkEditText;
    Intent intent;
    Button submitButton,requestCertificateButton;
    String userEmail,evaluated;

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
        submitButton=getView().findViewById(R.id.cSubmit);
        requestCertificateButton=getView().findViewById(R.id.rCert);
        submitLinkEditText=getView().findViewById(R.id.submitCodeLink);
        final String projectId=intent.getExtras().getString("projectId");
        FirebaseAuth mauth=FirebaseAuth.getInstance();
        final FirebaseUser user=mauth.getCurrentUser();
        if (user != null) {
            userEmail=user.getEmail();
        } else
            {
                userEmail=null;
            }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link=submitLinkEditText.getText().toString();
                if(link.equals("") )
                {
                    Toast.makeText(getContext(),"Please Provide a link"
                            ,Toast.LENGTH_SHORT).show();
                }
                else if(user!=null)
                    {
                        HashMap<String,String> submission=new HashMap<>();
                        submission.put("link",link);
                        submission.put("user email",userEmail);
                        FirebaseDatabase.getInstance().getReference().child("submissions")
                        .child(projectId).push().setValue(submission);
                        Toast.makeText(getContext(),"Link Submitted",Toast.LENGTH_SHORT).show();
                    }
                else
                    {
                        Intent intent=new Intent(getContext(),MainActivity.class);
                        startActivity(intent);
                    }
            }
        });

        requestCertificateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    FirebaseDatabase.getInstance().getReference().child("submissions").
                            child(projectId).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            String tempEmail=snapshot.child("user email").toString();
                            try {
                                evaluated = snapshot.child("evaluated").toString();
                            } catch (Exception e)
                            {
                                evaluated=null;
                            }
                            if(userEmail.equals(tempEmail)&& evaluated != null)
                            {   //if user is certified
                                Toast.makeText(getContext(),"you are certified",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            }
        });


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
