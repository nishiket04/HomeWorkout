package com.nishiket.homeworkout.SignInUp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.HeightViewPagerAdapter;

public class HeightFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_height, container, false);
    }
    private TabLayout heightTabLayout;
    private ViewPager heightViewPager;
    private AppCompatButton heightContinueBtn;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        heightTabLayout = view.findViewById(R.id.heightTabLayout);
        heightViewPager = view.findViewById(R.id.heightViewPager);
        heightContinueBtn = view.findViewById(R.id.heightContinueBtn);

        HeightViewPagerAdapter heightViewPagerAdapter = new HeightViewPagerAdapter(getParentFragmentManager());
        heightViewPager.setAdapter(heightViewPagerAdapter);
        heightTabLayout.setupWithViewPager(heightViewPager);

        heightContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new WeightFragment()).commit();
            }
        });
    }
}