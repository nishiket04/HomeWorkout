package com.nishiket.homeworkout.repository;

import android.app.Activity;
import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthRepository {
    private Application application; // application context variable
    private MutableLiveData<FirebaseUser> firebaseUserMutableLiveData; // mutableLive data of an firebaseUser
    private FirebaseAuth firebaseAuth; // FirebaseAuth variable

    // constructor that accept aplication context and get instance of an database also create mutable live data
    public AuthRepository(Application application){
        this.application=application;
        firebaseUserMutableLiveData= new MutableLiveData<>();
        firebaseAuth=FirebaseAuth.getInstance();
    }

    // to getMutablelivew data
    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return firebaseUserMutableLiveData;
    }

    // to get currentUser
    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }

    // SignIn method
    public void signUp(String email, String pass){
        // signup call firebase method
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            firebaseUserMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                        }else {
                            Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // SignIn method
    public void signIn(String email, String pass){
        firebaseAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            firebaseUserMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                        }else{
                            Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    //logOut method
    public void signOut(){
        firebaseAuth.signOut();
    }
}
