package com.nishiket.homeworkout.SignInUp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentHeightInFeetBinding;

public class HeightInFeetFragment extends Fragment {

    private FragmentHeightInFeetBinding heightInFeetBinding;

    public HeightInFeetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        heightInFeetBinding = FragmentHeightInFeetBinding.inflate(inflater,container,false);
        return heightInFeetBinding.getRoot();
    }
}