package com.nishiket.homeworkout.onborading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.nishiket.homeworkout.SignInUp.SignInUpActivity;
import com.nishiket.homeworkout.adapter.ViewPagerAdapter;
import com.nishiket.homeworkout.databinding.ActivityWelcomeScreenBinding;

public class WelcomeScreenActivity extends AppCompatActivity{

    private ActivityWelcomeScreenBinding activityWelcomeScreenBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWelcomeScreenBinding = ActivityWelcomeScreenBinding.inflate(getLayoutInflater());
        setContentView(activityWelcomeScreenBinding.getRoot());

        //all intents
        Intent i = new Intent(this, SignInUpActivity.class);

        // using sharedPrefences to know that user is opening this app for first time or not
        SharedPreferences data = getSharedPreferences("data",MODE_PRIVATE);
        boolean second = data.getBoolean("second",false);

        //if user in not opening this app for first time then redirect to signin
        if (second){
            i.putExtra("signin",false);
            startActivity(i); // passing activity
            finish(); // after reaching sigInUp activity destroy this activity
        }

        // seeting up viewpager on tablayout
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        activityWelcomeScreenBinding.viewPager.setAdapter(viewPagerAdapter);
        activityWelcomeScreenBinding.tabLayout.setupWithViewPager(activityWelcomeScreenBinding.viewPager);

        //for changing tablayout params from java
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) activityWelcomeScreenBinding.tabLayout.getLayoutParams();

        activityWelcomeScreenBinding.bottomSheeetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityWelcomeScreenBinding.tabLayout.selectTab(activityWelcomeScreenBinding.tabLayout.getTabAt(1));
            }
        });

        activityWelcomeScreenBinding.signInTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // passing boolean value to intent so in signin activity can know its signup or signin
                i.putExtra("signin",false);
                startActivity(i); // passing activity
                finish(); // after reaching sigInUp activity destroy this activity
            }
        });

        // this will load content on bottom sheet according to its position
        activityWelcomeScreenBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
            if(position == 0){
                activityWelcomeScreenBinding.bottomSheetHead.setText("Welcome to FitooZone");
                activityWelcomeScreenBinding.bottomSheetContenet.setText("FitooZone has workouts on demand that you can find based on how much time you have");
                activityWelcomeScreenBinding.bottomSheeetBtn.setText("Get Started");
                activityWelcomeScreenBinding.bottomSheetLinear.setVisibility(View.VISIBLE);
                activityWelcomeScreenBinding.bottomSheeetBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activityWelcomeScreenBinding.tabLayout.selectTab(activityWelcomeScreenBinding.tabLayout.getTabAt(1));
                    }
                });
                }
            else if ( position == 1) {
                activityWelcomeScreenBinding.bottomSheetHead.setText("Workout Categories");
                activityWelcomeScreenBinding.bottomSheetContenet.setText("Workout categories will help you gain strength, get in better shape and embrace a healthy lifestyle");
                activityWelcomeScreenBinding.bottomSheeetBtn.setText("Continue");
                activityWelcomeScreenBinding.bottomSheetLinear.setVisibility(View.GONE);
                int topMarginInPixels = 70; // Adjust this value as needed
                params.setMargins(0, topMarginInPixels, 0, 0);
                activityWelcomeScreenBinding.tabLayout.setLayoutParams(params);
                activityWelcomeScreenBinding.bottomSheeetBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activityWelcomeScreenBinding.tabLayout.selectTab(activityWelcomeScreenBinding.tabLayout.getTabAt(2));
                    }
                });
                }
            else {
                activityWelcomeScreenBinding.bottomSheetHead.setText("Custom Workouts");
                activityWelcomeScreenBinding.bottomSheetContenet.setText("Create and save your own custom workouts. Name your workouts, save them, and they’ll automatically appear when you’re ready to workout");
                activityWelcomeScreenBinding.bottomSheeetBtn.setText("Start Training");
                activityWelcomeScreenBinding.bottomSheetLinear.setVisibility(View.GONE);
                int topMarginInPixels = 70; // Adjust this value as needed
                params.setMargins(0, topMarginInPixels, 0, 0);
                activityWelcomeScreenBinding.tabLayout.setLayoutParams(params);
                activityWelcomeScreenBinding.bottomSheeetBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // passing boolean value to intent so in signin activity can know its signup or signin
                        i.putExtra("signin",true);
                        startActivity(i); // passing activity
                        finish(); // after reaching sigInUp activity destroy this activity
                    }
                });
            }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }
}