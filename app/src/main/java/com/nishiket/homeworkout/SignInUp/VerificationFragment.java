package com.nishiket.homeworkout.SignInUp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentVerifiactionBinding;

import java.util.concurrent.TimeUnit;


public class VerificationFragment extends Fragment {
    private FragmentVerifiactionBinding verifiactionBinding;
    private String verificationId;
    private String otp;
    private String email,password;

    public VerificationFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        verifiactionBinding = FragmentVerifiactionBinding.inflate(inflater,container,false);
        return verifiactionBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SignInUpActivity signInUpActivity = (SignInUpActivity) getActivity();

        sendVerificationCode(signInUpActivity.phoneNumber);
        verifiactionBinding.phoneNumberTxt.setText(signInUpActivity.phoneNumber);
        email = signInUpActivity.email;
        password = signInUpActivity.password;
        verifiactionBinding.otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 1){
                    verifiactionBinding.otp2.requestFocus();
                }
            }
        });

        verifiactionBinding.otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    verifiactionBinding.otp1.requestFocus();
                }
                else if (editable.length() == 1){
                    verifiactionBinding.otp3.requestFocus();
                }
            }
        });

        verifiactionBinding.otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    verifiactionBinding.otp2.requestFocus();
                }
                else if (editable.length() == 1){
                    verifiactionBinding.otp4.requestFocus();
                }
            }
        });
        verifiactionBinding.otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    verifiactionBinding.otp3.requestFocus();
                }
                else if (editable.length() == 1){
                    verifiactionBinding.otp5.requestFocus();
                }
            }
        });
        verifiactionBinding.otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    verifiactionBinding.otp4.requestFocus();
                }
                else if (editable.length() == 1){
                    verifiactionBinding.otp6.requestFocus();
                }
            }
        });
        verifiactionBinding.otp6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    verifiactionBinding.otp5.requestFocus();
                }else {
                    otp = verifiactionBinding.otp1.getText().toString()+verifiactionBinding.otp2.getText().toString()+verifiactionBinding.otp3.getText().toString()+verifiactionBinding.otp4.getText().toString()+verifiactionBinding.otp5.getText().toString()+verifiactionBinding.otp6.getText().toString();
                    // Check if the entered OTP is valid
                    if (verificationId != null && !verificationId.isEmpty()) {
                        verifyPhoneNumberWithCode(verificationId, otp);
                    }
                }
            }
        });
    }

    private void sendVerificationCode(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                getActivity(),
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Log.e("done", "onVerificationFailed", e);
                        Toast.makeText(getActivity(), "Verification failed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                       verificationId = s;
                    }
                });
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        createAccountWithEmailPassword();
    }

    private void createAccountWithEmailPassword() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("done", "firebasae email: done");
                            FragmentManager fragmentManager = getParentFragmentManager();
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.replace(R.id.frame,new GenderFragment()).commit();
                        }else {
                            Toast.makeText(getContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }
}