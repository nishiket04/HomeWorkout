package com.nishiket.homeworkout.onborading;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentWelcomeScreenOneBinding;

public class WelcomeScreenOneFragment extends Fragment {

    private FragmentWelcomeScreenOneBinding fragmentWelcomeScreenOneBinding;
    public WelcomeScreenOneFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentWelcomeScreenOneBinding = FragmentWelcomeScreenOneBinding.inflate(inflater,container,false);
        return fragmentWelcomeScreenOneBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}