package com.nishiket.homeworkout.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nishiket.homeworkout.onborading.WelcomeScreenOneFragment;
import com.nishiket.homeworkout.onborading.WelcomeScreenThreeFragment;
import com.nishiket.homeworkout.onborading.WelcomeScreenTwoFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            Log.d("position", ""+ position);
            if(position == 0){
                return (new WelcomeScreenOneFragment());
            } else if (position == 1) {
                return (new WelcomeScreenTwoFragment());
            }else{
                return (new WelcomeScreenThreeFragment());
            }
        }

        @Override
        public int getCount() {
            return 3;
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0){
                return "";
            } else if (position == 1) {
                return "";
            }else {
                return "";
            }
        }

}

