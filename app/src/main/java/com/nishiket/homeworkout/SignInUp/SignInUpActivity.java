package com.nishiket.homeworkout.SignInUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.nishiket.homeworkout.R;

public class SignInUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_up);
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
}