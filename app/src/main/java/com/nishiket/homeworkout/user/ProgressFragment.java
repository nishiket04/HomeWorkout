package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.ProgressViewPagerAdapter;

public class ProgressFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }
    private TabLayout progressTab;
    private ViewPager progressViewPager;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assignId(view);

        ProgressViewPagerAdapter progressViewPagerAdapter = new ProgressViewPagerAdapter(getParentFragmentManager());
        progressViewPager.setAdapter(progressViewPagerAdapter);
        progressTab.setupWithViewPager(progressViewPager);

    }

    private void assignId(View view) {
        progressViewPager = view.findViewById(R.id.progressViewPager);
        progressTab = view.findViewById(R.id.progressTabLayout);
    }
}