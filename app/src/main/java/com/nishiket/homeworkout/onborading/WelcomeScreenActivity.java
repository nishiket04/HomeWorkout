package com.nishiket.homeworkout.onborading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.ViewPagerAdapter;

public class WelcomeScreenActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView bottomSheetHead,bottomSheetContent;
    private LinearLayout bottomSheetLiner;
    private AppCompatButton bottomSheetBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        bottomSheetBtn = findViewById(R.id.bottomSheeetBtn);
        bottomSheetContent = findViewById(R.id.bottomSheetContenet);
        bottomSheetHead = findViewById(R.id.bottomSheetHead);
        bottomSheetLiner = findViewById(R.id.bottomSheetLinear);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabLayout.getLayoutParams();

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
                }
            else if ( position == 1) {
                bottomSheetHead.setText("Workout Categories");
                bottomSheetContent.setText("Workout categories will help you gain strength, get in better shape and embrace a healthy lifestyle");
                bottomSheetBtn.setText("Start Training");
                bottomSheetLiner.setVisibility(View.GONE);
                int topMarginInPixels = 70; // Adjust this value as needed
                params.setMargins(0, topMarginInPixels, 0, 0);
                tabLayout.setLayoutParams(params);
                }
            else {
                bottomSheetHead.setText("Custom Workouts");
                bottomSheetContent.setText("Create and save your own custom workouts. Name your workouts, save them, and they’ll automatically appear when you’re ready to workout");
                bottomSheetBtn.setText("Start Training");
                bottomSheetLiner.setVisibility(View.GONE);
                int topMarginInPixels = 65; // Adjust this value as needed
                params.setMargins(0, topMarginInPixels, 0, 0);
                tabLayout.setLayoutParams(params);
            }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}