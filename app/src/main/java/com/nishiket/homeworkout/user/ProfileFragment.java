package com.nishiket.homeworkout.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.SignInUp.SignInUpActivity;
import com.nishiket.homeworkout.SplaseScreenActivity;
import com.nishiket.homeworkout.databinding.FragmentPersonalTrainingBinding;
import com.nishiket.homeworkout.databinding.FragmentProfileBinding;
import com.nishiket.homeworkout.model.ImageModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding profileBinding;
    private Retrofit apiService;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        profileBinding = FragmentProfileBinding.inflate(inflater,container,false);
        return profileBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiService = RetrofitClient.getRetrofitInstance().create(Retrofit.class);

        // Add your API key and email
        int apiKey = 123;
        String email = "nishiket04@gmail.com";

        // Call method to fetch image
        fetchImage(apiKey, email);


        profileBinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken("514357553984-6jdqglnfnb99dmg2jmkhc4pjl8rojm35.apps.googleusercontent.com")
                        .requestEmail()
                        .build();
                GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
                googleSignInClient.revokeAccess();
                Intent i = new Intent(getActivity(), SignInUpActivity.class);
                startActivity(i);
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.finish();
            }
        });
        profileBinding.goPrimiumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileBinding.premium.setVisibility(View.VISIBLE);
                profileBinding.goPrimiumBtn.setVisibility(View.GONE);
            }
        });

        profileBinding.premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileBinding.premium.setVisibility(View.GONE);
                profileBinding.goPrimiumBtn.setVisibility(View.VISIBLE);
            }
        });

        profileBinding.accountInformationProfileLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFrameLayout,new AccountInformationFragment()).addToBackStack("accountInformation").commit();
            }
        });

        profileBinding.myWorkoutProfileLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentManager fragmentManager = getParentFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.homeFrameLayout,new WeightTrackingFragment() ).addToBackStack("accountInformation").commit();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFrameLayout,new MyWorkoutFragment()).addToBackStack("myworkout").commit();
            }
        });

        profileBinding.workoutReminderProfileLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFrameLayout,new WorkoutRemindersFragment()).addToBackStack("accountInformation").commit();
            }
        });


    }
    private void fetchImage(int apiKey, String email) {
        Call<ImageModel> call = apiService.getImage(apiKey, email);
        call.enqueue(new Callback<ImageModel>() {
            @Override
            public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Image data fetched successfully
                    // Load image into ImageView using Glide
                    Glide.with(getContext()).load(response.body().getURL()).into(profileBinding.profileImage);
                } else {
                }
            }

            @Override
            public void onFailure(Call<ImageModel> call, Throwable t) {
            }
        });
    }
}