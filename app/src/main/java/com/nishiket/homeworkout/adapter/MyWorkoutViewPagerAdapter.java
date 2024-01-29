package com.nishiket.homeworkout.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nishiket.homeworkout.SignInUp.GoalWeightInKgFragment;
import com.nishiket.homeworkout.user.MyWorkoutFavoritesFragment;
import com.nishiket.homeworkout.user.MyWorkoutHistoryFragment;
import com.nishiket.homeworkout.user.MyWorkoutLastFragment;

public class MyWorkoutViewPagerAdapter extends FragmentPagerAdapter {
    public MyWorkoutViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new MyWorkoutHistoryFragment();
        }
        else if(position == 1){
            return new MyWorkoutLastFragment();
        }
        else {
            return new MyWorkoutFavoritesFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "History";
        } else if (position == 1) {
            return "Last";
        }
        else {
            return "Favorites";
        }
    }
}
