package com.nishiket.homeworkout.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nishiket.homeworkout.SignInUp.GoalWeightInKgFragment;
import com.nishiket.homeworkout.user.BlankFragment;

public class ProgressViewPagerAdapter extends FragmentPagerAdapter {
    public ProgressViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new BlankFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "Week";
        }
        else if(position == 1){
            return "Month";
        }
        else {
            return "Year";
        }
    }
}
