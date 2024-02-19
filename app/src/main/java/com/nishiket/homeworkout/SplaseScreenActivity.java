package com.nishiket.homeworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.nishiket.homeworkout.databinding.ActivitySplaseScreenBinding;
import com.nishiket.homeworkout.onborading.WelcomeScreenActivity;

import java.util.ArrayList;
import java.util.List;

public class SplaseScreenActivity extends AppCompatActivity {
    private ActivitySplaseScreenBinding splaseScreenBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splaseScreenBinding = ActivitySplaseScreenBinding.inflate(getLayoutInflater());
        setContentView(splaseScreenBinding.getRoot());

        Intent i = new Intent(this, WelcomeScreenActivity.class);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        },3000);
    }
}