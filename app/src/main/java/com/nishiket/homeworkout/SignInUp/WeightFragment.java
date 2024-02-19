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
import com.nishiket.homeworkout.adapter.WeigthViewPagerAdapter;
import com.nishiket.homeworkout.databinding.FragmentWeightBinding;

public class WeightFragment extends Fragment {

    private FragmentWeightBinding weightBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        weightBinding = FragmentWeightBinding.inflate(inflater,container,false);
        return weightBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WeigthViewPagerAdapter weigthViewPagerAdapter = new WeigthViewPagerAdapter(getParentFragmentManager());
        weightBinding.weightViewPager.setAdapter(weigthViewPagerAdapter);
        weightBinding.weightTabLayout.setupWithViewPager(weightBinding.weightViewPager);

        weightBinding.weightContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new GoalWeightFragment()).commit();
            }
        });
    }
}