package com.nishiket.homeworkout.SignInUp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.GoalWeightViewPagerAdapter;
import com.nishiket.homeworkout.databinding.FragmentGoalWeightBinding;
import com.nishiket.homeworkout.model.UserGoalWeightModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoalWeightFragment extends Fragment {
    private FragmentGoalWeightBinding goalWeightBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        goalWeightBinding = FragmentGoalWeightBinding.inflate(inflater,container,false);
        return goalWeightBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AuthViewModel authViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);
        GoalWeightViewPagerAdapter goalWeightViewPagerAdapter = new GoalWeightViewPagerAdapter(getParentFragmentManager());
        goalWeightBinding.goalWeightViewPager.setAdapter(goalWeightViewPagerAdapter);
        goalWeightBinding.goalWeightTabLayout.setupWithViewPager(goalWeightBinding.goalWeightViewPager);

//        goalWeightBinding.goalWeightContinueBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}