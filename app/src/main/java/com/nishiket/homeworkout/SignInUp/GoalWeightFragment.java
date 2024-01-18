package com.nishiket.homeworkout.SignInUp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.GoalWeightViewPagerAdapter;

public class GoalWeightFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goal_weight, container, false);
    }

    private TabLayout goalWeightTabLayout;
    private ViewPager goalWeightViewPager;
    private AppCompatButton goalWeigthContinueBtn;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        goalWeightTabLayout = view.findViewById(R.id.goalWeightTabLayout);
        goalWeightViewPager = view.findViewById(R.id.goalWeightViewPager);
        goalWeigthContinueBtn = view.findViewById(R.id.goalWeightContinueBtn);

        GoalWeightViewPagerAdapter goalWeightViewPagerAdapter = new GoalWeightViewPagerAdapter(getParentFragmentManager());
        goalWeightViewPager.setAdapter(goalWeightViewPagerAdapter);
        goalWeightTabLayout.setupWithViewPager(goalWeightViewPager);

        goalWeigthContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new TrainingLevelFragment()).commit();
            }
        });
    }
}