package com.nishiket.homeworkout.SignInUp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentGoalWeightInPoundBinding;

public class GoalWeightInPoundFragment extends Fragment {
    private FragmentGoalWeightInPoundBinding goalWeightInPoundBinding;
    public GoalWeightInPoundFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        goalWeightInPoundBinding = FragmentGoalWeightInPoundBinding.inflate(inflater,container,false);
        return goalWeightInPoundBinding.getRoot();
    }
}