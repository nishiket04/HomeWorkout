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
import com.google.firebase.messaging.FirebaseMessaging;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener {

    private ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        FirebaseMessaging.getInstance().subscribeToTopic("all_users");

        activityMainBinding.bottomNavigationView.setOnNavigationItemSelectedListener(this);
        activityMainBinding.bottomNavigationView.setSelectedItemId(R.id.home);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            if(item.getItemId() == R.id.home) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.homeFrameLayout, new HomeFragment())
                        .commit();
                item.setIcon(R.drawable.home_selected);
                activityMainBinding.bottomNavigationView.getMenu().findItem(R.id.traing).setIcon(R.drawable.training);
                activityMainBinding.bottomNavigationView.getMenu().findItem(R.id.activity).setIcon(R.drawable.activity);
                activityMainBinding.bottomNavigationView.getMenu().findItem(R.id.user).setIcon(R.drawable.user);
                return true;
            } else if (item.getItemId() == R.id.traing) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.homeFrameLayout, new TrainingsFragment())
                        .commit();
                item.setIcon(R.drawable.trainign_selected);
                activityMainBinding.bottomNavigationView.getMenu().findItem(R.id.home).setIcon(R.drawable.home);
                activityMainBinding.bottomNavigationView.getMenu().findItem(R.id.activity).setIcon(R.drawable.activity);
                activityMainBinding.bottomNavigationView.getMenu().findItem(R.id.user).setIcon(R.drawable.user);
                return true;
            } else if (item.getItemId()==R.id.activity) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.homeFrameLayout, new ActivityFragment())
                        .commit();
                item.setIcon(R.drawable.activity_selected);
                activityMainBinding.bottomNavigationView.getMenu().findItem(R.id.user).setIcon(R.drawable.user);
                activityMainBinding.bottomNavigationView.getMenu().findItem(R.id.traing).setIcon(R.drawable.training);
                activityMainBinding.bottomNavigationView.getMenu().findItem(R.id.home).setIcon(R.drawable.home);
                return true;
            } else if (item.getItemId() == R.id.user) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.homeFrameLayout, new ProfileFragment())
                        .commit();
                item.setIcon(R.drawable.profile_selected);
                activityMainBinding.bottomNavigationView.getMenu().findItem(R.id.traing).setIcon(R.drawable.training);
                activityMainBinding.bottomNavigationView.getMenu().findItem(R.id.activity).setIcon(R.drawable.activity);
                activityMainBinding.bottomNavigationView.getMenu().findItem(R.id.home).setIcon(R.drawable.home);
                return true;
            }

        return false;
        }
}