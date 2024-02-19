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
import com.nishiket.homeworkout.adapter.ProgressViewPagerAdapter;
import com.nishiket.homeworkout.databinding.FragmentProgressBinding;

public class ProgressFragment extends Fragment {
    private FragmentProgressBinding progressBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        progressBinding = FragmentProgressBinding.inflate(inflater,container,false);
        return progressBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ProgressViewPagerAdapter progressViewPagerAdapter = new ProgressViewPagerAdapter(getParentFragmentManager());
        progressBinding.progressViewPager.setAdapter(progressViewPagerAdapter);
        progressBinding.progressTabLayout.setupWithViewPager(progressBinding.progressViewPager);

        progressBinding.backToActivityImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            }
        });

    }
}