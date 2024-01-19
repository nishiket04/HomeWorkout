package com.nishiket.homeworkout.SignInUp;

import android.content.Intent;
import android.content.pm.verify.domain.DomainVerificationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.nishiket.homeworkout.R;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class SignUpFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    private TextView signInInSignUpTxt;
    private ImageView logoAppleSignUp,logoFacebookSignUp,logoGoogleSignUp;
    private AppCompatButton signUpBtn;
    private CheckBox privacyCheckBox;
    private CallbackManager callbackManager;
    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth mAuth;

    private EditText phoneEdt,fullNameEdt,passwordEdt,emailEdt;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // to assignId
        assignId(view);

        // parent fragment manager
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        mAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();

        signInInSignUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft.replace(R.id.frame,new SignInFragment()).commit();
            }
        });


            signUpBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(privacyCheckBox.isChecked()) {
                        String phoneNumber = phoneEdt.getText().toString();
                        SignInUpActivity signInUpActivity = (SignInUpActivity) getActivity();
                        signInUpActivity.phoneNumber = "+91"+phoneNumber;
                        ft.add(R.id.frame, new VerificationFragment(), "verification").commit();

                    }else {
                        Toast.makeText(getContext(), "Please Accept Privacy and Policy", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        // google

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("514357553984-6jdqglnfnb99dmg2jmkhc4pjl8rojm35.apps.googleusercontent.com")
                .requestEmail()
                .build();

        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        logoGoogleSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInWithGoogle(mGoogleSignInClient);
            }
        });


        callbackManager = CallbackManager.Factory.create();
        logoFacebookSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleFacebookLogin();
            }
        });
        }



    private void assignId(View view) {

        signInInSignUpTxt = view.findViewById(R.id.SignInInSignUpTxt);

        logoAppleSignUp = view.findViewById(R.id.logoAppleSignUp);
        logoFacebookSignUp = view.findViewById(R.id.logoFacebookSignUp);
        logoGoogleSignUp = view.findViewById(R.id.logoGoogleSignUp);

        signUpBtn = view.findViewById(R.id.signUpBtn);

        privacyCheckBox = view.findViewById(R.id.privacyCheckBox);

        phoneEdt = view.findViewById(R.id.phoneEdt);
        passwordEdt = view.findViewById(R.id.passwordEdt);
        fullNameEdt = view.findViewById(R.id.fullNameEdt);
        emailEdt = view.findViewById(R.id.emailEdt);

    }


    private void handleFacebookLogin() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // Handle successful login
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // Handle canceled login
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("facebook", "handleFacebookAccessToken: fail");
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Executor) this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("facebook", "handleFacebookAccessToken: success ");
                        FragmentManager fragmentManager = getParentFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.frame,new GenderFragment());
                    } else {
                        Log.d("facebook", "handleFacebookAccessToken: fail");
                    }
                });
    }

    private void signInWithGoogle(GoogleSignInClient mGoogleSignInClient) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            try {
                GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w("login", "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Log.d("login", "signInWithCredential:success");
                            // You can get the user information using task.getResult().getUser()
                            // or use mAuth.getCurrentUser() to get the current user.
                            FragmentManager fragmentManager = getParentFragmentManager();
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.replace(R.id.frame,new GenderFragment());

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("login", "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }

}