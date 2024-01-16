package com.nishiket.homeworkout.viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthViewModel extends AndroidViewModel {
    public AuthViewModel(@NonNull Application application) {
        super(application);
    }
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private MutableLiveData<FirebaseUser> userLiveData = new MutableLiveData<>();

    public LiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public void signInWithGoogle(Activity activity, String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        userLiveData.setValue(user);
                    } else {
                        // Handle the failure
                    }
                });
    }
}
