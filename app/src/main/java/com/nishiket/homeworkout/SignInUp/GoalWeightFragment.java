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
import com.nishiket.homeworkout.databinding.FragmentGoalWeightBinding;

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

        GoalWeightViewPagerAdapter goalWeightViewPagerAdapter = new GoalWeightViewPagerAdapter(getParentFragmentManager());
        goalWeightBinding.goalWeightViewPager.setAdapter(goalWeightViewPagerAdapter);
        goalWeightBinding.goalWeightTabLayout.setupWithViewPager(goalWeightBinding.goalWeightViewPager);

        goalWeightBinding.goalWeightContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new TrainingLevelFragment()).commit();
            }
        });
    }
}