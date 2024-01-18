package com.nishiket.homeworkout.SignInUp;

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

import com.facebook.CallbackManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.nishiket.homeworkout.R;

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

    private EditText phoneEdt,fullNameEdt,passwordEdt,emailEdt;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // to assignId
        assignId(view);

        // parent fragment manager
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

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
                        ft.add(R.id.frame, new GoalWeightFragment(), "verification").commit(); //TODO: chnge it to verifiaction

                    }else {
                        Toast.makeText(getContext(), "Please Accept Privacy and Policy", Toast.LENGTH_SHORT).show();
                    }
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
}