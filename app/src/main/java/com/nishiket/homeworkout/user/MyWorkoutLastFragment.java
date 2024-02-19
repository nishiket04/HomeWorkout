package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentMyWorkoutLastBinding;

public class MyWorkoutLastFragment extends Fragment {
    private FragmentMyWorkoutLastBinding myWorkoutLastBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       myWorkoutLastBinding = FragmentMyWorkoutLastBinding.inflate(inflater,container,false);
        return myWorkoutLastBinding.getRoot();
    }
}