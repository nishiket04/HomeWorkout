package com.nishiket.homeworkout.onborading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.SignInUp.SignInUpActivity;
import com.nishiket.homeworkout.adapter.ViewPagerAdapter;

public class WelcomeScreenActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView bottomSheetHead,bottomSheetContent,signInTxt;
    private LinearLayout bottomSheetLiner;
    private AppCompatButton bottomSheetBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);


        // assigning Ids
        assignId();

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
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //for changing tablayout params from java
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabLayout.getLayoutParams();

        bottomSheetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabLayout.selectTab(tabLayout.getTabAt(1));
            }
        });

        signInTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // passing boolean value to intent so in signin activity can know its signup or signin
                i.putExtra("signin",false);
                startActivity(i); // passing activity
                finish(); // after reaching sigInUp activity destroy this activity
            }
        });

        // this will load content on bottom sheet according to its position
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
            if(position == 0){
                bottomSheetHead.setText("Welcome to FitooZone");
                bottomSheetContent.setText("FitooZone has workouts on demand that you can find based on how much time you have");
                bottomSheetBtn.setText("Get Started");
                bottomSheetLiner.setVisibility(View.VISIBLE);
                bottomSheetBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tabLayout.selectTab(tabLayout.getTabAt(1));
                    }
                });
                }
            else if ( position == 1) {
                bottomSheetHead.setText("Workout Categories");
                bottomSheetContent.setText("Workout categories will help you gain strength, get in better shape and embrace a healthy lifestyle");
                bottomSheetBtn.setText("Continue");
                bottomSheetLiner.setVisibility(View.GONE);
                int topMarginInPixels = 70; // Adjust this value as needed
                params.setMargins(0, topMarginInPixels, 0, 0);
                tabLayout.setLayoutParams(params);
                bottomSheetBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tabLayout.selectTab(tabLayout.getTabAt(2));
                    }
                });
                }
            else {
                bottomSheetHead.setText("Custom Workouts");
                bottomSheetContent.setText("Create and save your own custom workouts. Name your workouts, save them, and they’ll automatically appear when you’re ready to workout");
                bottomSheetBtn.setText("Start Training");
                bottomSheetLiner.setVisibility(View.GONE);
                int topMarginInPixels = 70; // Adjust this value as needed
                params.setMargins(0, topMarginInPixels, 0, 0);
                tabLayout.setLayoutParams(params);
                bottomSheetBtn.setOnClickListener(new View.OnClickListener() {
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
    // assignId function
    private void assignId() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        bottomSheetBtn = findViewById(R.id.bottomSheeetBtn);
        bottomSheetContent = findViewById(R.id.bottomSheetContenet);
        bottomSheetHead = findViewById(R.id.bottomSheetHead);
        bottomSheetLiner = findViewById(R.id.bottomSheetLinear);
        signInTxt = findViewById(R.id.signInTxt);
    }
}