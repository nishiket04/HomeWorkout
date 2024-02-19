package com.nishiket.homeworkout.SignInUp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentWeightInPoundBinding;

public class WeightInPoundFragment extends Fragment {
    private FragmentWeightInPoundBinding weightInPoundBinding;
    public WeightInPoundFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        weightInPoundBinding = FragmentWeightInPoundBinding.inflate(inflater,container,false);
        return weightInPoundBinding.getRoot();
    }
}