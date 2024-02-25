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
import com.nishiket.homeworkout.repository.AuthRepository;

public class AuthViewModel extends AndroidViewModel {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private MutableLiveData<FirebaseUser> userLiveData = new MutableLiveData<>();

    public LiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    private MutableLiveData<FirebaseUser> firebaseUserMutableLiveData; // mutable live data as an firebasuser
    private FirebaseUser currentUser; // variable of firebase current user
    private AuthRepository repository; // object of an database class

    // this is an constructor which accept an aplication context
    public AuthViewModel(@NonNull Application application) {
        super(application);

        repository = new AuthRepository(application); // crate object of an Auth
        currentUser = repository.getCurrentUser(); // get the current user
        firebaseUserMutableLiveData = repository.getFirebaseUserMutableLiveData(); // get the firebase mutable live data
    }

    // when signup is called
    public void signUp(String email,String pass,String name,String addhar,String mobile){
        repository.signUp(email, pass); // in auth call its signUp
    }
    // call the signIn of an Auth class
    public void signIn(String email, String pass){
        repository.signIn(email, pass);;
    }

    // call the SignOut of an Auth class
    public void signOut(){
        repository.signOut();
    }
    // this will return mutable live data to infrom UI that there is an user in list or user is logged in
    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return firebaseUserMutableLiveData;
    }

    // this will return current user means if user loged in before then there is user in currentUser and it will directly go to home page
    public FirebaseUser getCurrentUser() {
        return currentUser;
    }
    }
