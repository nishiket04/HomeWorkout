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
import com.nishiket.homeworkout.databinding.FragmentHeightBinding;

public class HeightFragment extends Fragment {

    private FragmentHeightBinding heightBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        heightBinding = FragmentHeightBinding.inflate(inflater,container,false);
        return heightBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HeightViewPagerAdapter heightViewPagerAdapter = new HeightViewPagerAdapter(getParentFragmentManager());
        heightBinding.heightViewPager.setAdapter(heightViewPagerAdapter);
        heightBinding.heightTabLayout.setupWithViewPager(heightBinding.heightViewPager);

        heightBinding.heightContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new WeightFragment()).commit();
            }
        });
    }
}