package com.nishiket.homeworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.widget.Toast;

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
    private static final int REQUEST_PERMISSION_CODE = 123;
    private ActivitySplaseScreenBinding splaseScreenBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splaseScreenBinding = ActivitySplaseScreenBinding.inflate(getLayoutInflater());
        setContentView(splaseScreenBinding.getRoot());
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION_CODE);
        }
        else {

            Intent i = new Intent(this, WelcomeScreenActivity.class);
            Intent i1 = new Intent(this, MainActivity.class);
            AuthViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AuthViewModel.class);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (viewModel.getCurrentUser() != null) { // if there is any user logged In then
                        startActivity(i1); // goto home
                    } else {
                        startActivity(i); // goto login page
                    }
                    finish();
                }
            }, 3000);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            // Check if the permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, WelcomeScreenActivity.class);
                Intent i1 = new Intent(this, MainActivity.class);
                AuthViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AuthViewModel.class);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (viewModel.getCurrentUser() != null) { // if there is any user logged In then
                            startActivity(i1); // goto home
                        } else {
                            startActivity(i); // goto login page
                        }
                        finish();
                    }
                }, 3000);
            } else {
                finish();
            }
        }
    }
}