package com.nishiket.homeworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.nishiket.homeworkout.databinding.ActivitySplaseScreenBinding;
import com.nishiket.homeworkout.model.ImageModel;
import com.nishiket.homeworkout.onborading.WelcomeScreenActivity;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.user.MainActivity;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplaseScreenActivity extends AppCompatActivity {
    private ActivitySplaseScreenBinding splaseScreenBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splaseScreenBinding = ActivitySplaseScreenBinding.inflate(getLayoutInflater());
        setContentView(splaseScreenBinding.getRoot());

        Intent i = new Intent(this, WelcomeScreenActivity.class);
        Intent i1 = new Intent(this, MainActivity.class);
        AuthViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AuthViewModel.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewModel.getCurrentUser() != null){ // if there is any user logged In then
                    startActivity(i1); // goto home
                }else {
                    startActivity(i); // goto login page
                }
                finish();
            }
        },3000);
    }
}