package com.nishiket.homeworkout.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.nishiket.homeworkout.R;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.homeFrameLayout,new HomeFragment()).commit();

    }
}