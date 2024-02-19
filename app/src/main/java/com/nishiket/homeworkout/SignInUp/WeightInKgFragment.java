package com.nishiket.homeworkout.SignInUp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentWeightInKgBinding;

public class WeightInKgFragment extends Fragment {
    private FragmentWeightInKgBinding weightInKgBinding;
    public WeightInKgFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        weightInKgBinding = FragmentWeightInKgBinding.inflate(inflater,container,false);
        return weightInKgBinding.getRoot();
    }
}