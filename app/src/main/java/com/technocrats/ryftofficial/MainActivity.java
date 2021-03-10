package com.technocrats.ryftofficial;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    private static final String EMAIL = "email";
    private static final int RC_SIGN_IN =112 ;
    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;
    LoginButton loginButton;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        loginButton=findViewById(R.id.facebookLoginInButton);
        InitializeFacebook();
        InitializeGoogleLogin();
        mAuth=FirebaseAuth.getInstance();
        user =mAuth.getCurrentUser();
    }

    private void InitializeGoogleLogin() {
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

    }

    private void InitializeFacebook() {

        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        facebookHandlerCode(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }


    private void facebookHandlerCode(AccessToken accessToken)
    {
        AuthCredential credential= FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    try{
                    FirebaseDatabase.getInstance()
                            .getReference().child("users").child(task.getResult().getUser().getUid())
                            .child("email").setValue(task.getResult().getUser().getEmail());
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    finish();}
                    catch (Exception e)
                    {
                        Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    }
                } else
                {
                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signIn() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("831473135278-01642e3pthvh8kn13jr8jf7o8cbd42or.apps.googleusercontent.com")
                .requestEmail()
                .requestId()
                .requestProfile()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Check Result come from Google
        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> accountTask=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account=accountTask.getResult(ApiException.class);
                processFirebaseLoginStep(account.getIdToken());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            callbackManager.onActivityResult(requestCode,resultCode,data);
        }
    }
    private void processFirebaseLoginStep(String token){
        AuthCredential authCredential= GoogleAuthProvider.getCredential(token,null);
        mAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user=mAuth.getCurrentUser();
                            SendUserData(user);
                        }
                    }
                });
    }

    private void  SendUserData(FirebaseUser user){

        FirebaseDatabase.getInstance()
                    .getReference().child("users").child(user.getUid())
                    .child("email").setValue(user.getEmail());
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
        finish();
    }

}
