package com.nishiket.homeworkout.SignInUp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentHeightInCentimterBinding;

public class HeightInCentimterFragment extends Fragment {
    private FragmentHeightInCentimterBinding heightInCentimterBinding;
    public HeightInCentimterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        heightInCentimterBinding = FragmentHeightInCentimterBinding.inflate(inflater,container,false);
        return heightInCentimterBinding.getRoot();
    }
}