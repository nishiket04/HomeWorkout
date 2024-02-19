package com.nishiket.homeworkout.onborading;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentWelcomeScreenTwoBinding;

public class WelcomeScreenTwoFragment extends Fragment {
    private FragmentWelcomeScreenTwoBinding fragmentWelcomeScreenTwoBinding;
    public WelcomeScreenTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentWelcomeScreenTwoBinding = FragmentWelcomeScreenTwoBinding.inflate(inflater,container,false);
        return fragmentWelcomeScreenTwoBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}