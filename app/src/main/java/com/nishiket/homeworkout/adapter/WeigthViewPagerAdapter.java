package com.nishiket.homeworkout.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nishiket.homeworkout.SignInUp.WeightInKgFragment;
import com.nishiket.homeworkout.SignInUp.WeightInPoundFragment;

public class WeigthViewPagerAdapter extends FragmentPagerAdapter {
    public WeigthViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new WeightInPoundFragment();
        }
        else {
            return new WeightInKgFragment();
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
