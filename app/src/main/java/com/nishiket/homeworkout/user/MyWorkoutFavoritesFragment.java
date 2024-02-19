package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentMyWorkoutFavoritesBinding;

public class MyWorkoutFavoritesFragment extends Fragment {
    private FragmentMyWorkoutFavoritesBinding myWorkoutFavoritesBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myWorkoutFavoritesBinding = FragmentMyWorkoutFavoritesBinding.inflate(inflater,container,false);
        return myWorkoutFavoritesBinding.getRoot();
    }
}