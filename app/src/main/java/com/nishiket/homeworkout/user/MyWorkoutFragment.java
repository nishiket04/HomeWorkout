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

public class MyWorkoutFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_workout, container, false);
    }

    private TabLayout myWorkoutTabLayout;
    private ViewPager myWorkoutViewPager;
    private ImageView backToProfile;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assignId(view);

        MyWorkoutViewPagerAdapter myWorkoutViewPagerAdapter = new MyWorkoutViewPagerAdapter(getParentFragmentManager());
        myWorkoutViewPager.setAdapter(myWorkoutViewPagerAdapter);
        myWorkoutTabLayout.setupWithViewPager(myWorkoutViewPager);

        backToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            }
        });
    }

    private void assignId(View view) {
        myWorkoutTabLayout = view.findViewById(R.id.myWorkoutTabLayout);
        myWorkoutViewPager = view.findViewById(R.id.myWorkoutViewPager);
        backToProfile = view.findViewById(R.id.backToProfileImage);
    }
}