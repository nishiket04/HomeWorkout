package com.nishiket.homeworkout.repository;

import android.app.Activity;
import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.AuthCredential;
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
    public void signInWithGoogle(Activity activity, String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        firebaseUserMutableLiveData.setValue(user);
                    } else {
                        // Handle the failure
                    }
                });
    }


    //logOut method
    public void signOut(){
        firebaseAuth.signOut();
    }
}
