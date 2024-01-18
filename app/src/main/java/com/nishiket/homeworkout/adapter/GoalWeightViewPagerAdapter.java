package com.nishiket.homeworkout.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nishiket.homeworkout.SignInUp.GoalWeightInKgFragment;
import com.nishiket.homeworkout.SignInUp.GoalWeightInPoundFragment;

public class GoalWeightViewPagerAdapter extends FragmentPagerAdapter {
    public GoalWeightViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return  new GoalWeightInPoundFragment();
        }
        else {
            return new GoalWeightInKgFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "Pound";
        }else {
            return "Kilogram";
        }
    }
}
