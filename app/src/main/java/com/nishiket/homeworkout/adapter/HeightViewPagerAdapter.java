package com.nishiket.homeworkout.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nishiket.homeworkout.SignInUp.HeightInCentimterFragment;
import com.nishiket.homeworkout.SignInUp.HeightInFeetFragment;

public class HeightViewPagerAdapter extends FragmentPagerAdapter {
    public HeightViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new HeightInFeetFragment();
        }else {
            return new HeightInCentimterFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Feet";
        } else {
            return "Centimetre";
        }
    }

}
