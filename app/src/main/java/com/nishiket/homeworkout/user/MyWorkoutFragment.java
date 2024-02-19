package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.MyWorkoutViewPagerAdapter;
import com.nishiket.homeworkout.databinding.FragmentMyWorkoutBinding;

public class MyWorkoutFragment extends Fragment {
    private FragmentMyWorkoutBinding myWorkoutBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myWorkoutBinding = FragmentMyWorkoutBinding.inflate(inflater,container,false);
        return myWorkoutBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        MyWorkoutViewPagerAdapter myWorkoutViewPagerAdapter = new MyWorkoutViewPagerAdapter(getParentFragmentManager());
        myWorkoutBinding.myWorkoutViewPager.setAdapter(myWorkoutViewPagerAdapter);
        myWorkoutBinding.myWorkoutTabLayout.setupWithViewPager(myWorkoutBinding.myWorkoutViewPager);

        myWorkoutBinding.backToProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            }
        });
    }
}