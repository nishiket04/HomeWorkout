package com.nishiket.homeworkout.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.SignInUp.SignInUpActivity;
import com.nishiket.homeworkout.databinding.FragmentProfileBinding;
import com.nishiket.homeworkout.model.ImageModel;
import com.nishiket.homeworkout.model.UserDetailModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;
import com.nishiket.homeworkout.viewmodel.UserDetailViewModel;

import java.time.LocalDate;
import java.time.Period;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding profileBinding;
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
        AuthViewModel authViewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);

        // Add your API key and email
        int apiKey = 123;
        String email = authViewModel.getCurrentUser().getEmail();

        UserDetailViewModel viewModel =new ViewModelProvider((ViewModelStoreOwner) this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(UserDetailViewModel.class);
        // Call method to fetch image
        fetchImage(apiKey, email,viewModel);
        viewModel.getData(apiKey,email);
        viewModel.getUserDetailModelMutableLiveData().observe(getViewLifecycleOwner(), new Observer<UserDetailModel>() {
            @Override
            public void onChanged(UserDetailModel userDetailModel) {
                profileBinding.userName.setText(Character.toUpperCase(userDetailModel.getName().charAt(0)) + userDetailModel.getName().substring(1).toLowerCase());
                profileBinding.weight.setText(""+userDetailModel.getWeightKg()+" kg");
                profileBinding.height.setText(""+userDetailModel.getHeightcm()+" cm");
                LocalDate today = LocalDate.now();
                Log.d("age", "onChanged: "+ today);
                LocalDate birth = LocalDate.parse(userDetailModel.getBirth());
                Log.d("age", "onChanged: "+ birth);
                Period age = Period.between(birth,today);
                Log.d("age", "onChanged: "+ age);
                profileBinding.age.setText(""+age.getYears()+" Years");
            }
        });


        profileBinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken("514357553984-6jdqglnfnb99dmg2jmkhc4pjl8rojm35.apps.googleusercontent.com")
                        .requestEmail()
                        .build();
                GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
                googleSignInClient.revokeAccess();
                authViewModel.signOut();
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
    private void fetchImage(int apiKey, String email, UserDetailViewModel viewModel) {
        viewModel.getImage(apiKey,email);
        viewModel.getImageModelMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ImageModel>() {
            @Override
            public void onChanged(ImageModel imageModel) {
                Glide.with(getContext()).load(imageModel.getURL()).into(profileBinding.profileImage);
            }
        });
    }
}