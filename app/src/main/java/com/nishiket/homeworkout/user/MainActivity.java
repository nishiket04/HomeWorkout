package com.nishiket.homeworkout.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nishiket.homeworkout.R;

public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener {

    private FrameLayout frameLayout;
    private BottomNavigationView bottom_navigation_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_navigation_view = findViewById(R.id.bottom_navigation_view);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.add(R.id.homeFrameLayout,new TrainingsFragment()).commit();

        bottom_navigation_view.setOnNavigationItemSelectedListener(this);
        bottom_navigation_view.setSelectedItemId(R.id.home);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            if(item.getItemId() == R.id.home) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.homeFrameLayout, new HomeFragment())
                        .commit();
                return true;
            } else if (item.getItemId() == R.id.traing) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.homeFrameLayout, new TrainingsFragment())
                        .commit();
                return true;
            } else if (item.getItemId()==R.id.activity) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.homeFrameLayout, new ActivityFragment())
                        .commit();
                return true;
            } else if (item.getItemId() == R.id.user) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.homeFrameLayout, new ProfileFragment())
                        .commit();
                return true;
            }

        return false;
        }
}