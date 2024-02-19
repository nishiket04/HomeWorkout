package com.nishiket.homeworkout.SignInUp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentGoalWeightInKgBinding;

public class GoalWeightInKgFragment extends Fragment {
    private FragmentGoalWeightInKgBinding goalWeightInKgBinding;
    public GoalWeightInKgFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        goalWeightInKgBinding = FragmentGoalWeightInKgBinding.inflate(inflater,container,false);
        return goalWeightInKgBinding.getRoot();
    }
}