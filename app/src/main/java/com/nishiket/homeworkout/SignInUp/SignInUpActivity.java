package com.nishiket.homeworkout.SignInUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.ActivitySignInUpBinding;

public class SignInUpActivity extends AppCompatActivity {
    private ActivitySignInUpBinding activitySignInUpBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignInUpBinding = ActivitySignInUpBinding.inflate(getLayoutInflater());
        setContentView(activitySignInUpBinding.getRoot());
        Intent i = getIntent();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        boolean signin = i.getBooleanExtra("signin",false);

        SharedPreferences data = getSharedPreferences("data",MODE_PRIVATE);
        if(signin){
            data.edit().putBoolean("second",true).commit();
            ft.add(R.id.frame,new SignUpFragment()).commit();
        }
        else {
            data.edit().putBoolean("second", true).commit();
            ft.add(R.id.frame, new SignInFragment()).commit();
        }

    }
    String phoneNumber;
    String email;
    String password;
    String name;
}