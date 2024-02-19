package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentMusicProviderBinding;

public class MusicProviderFragment extends Fragment {
    private FragmentMusicProviderBinding musicProviderBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        musicProviderBinding = FragmentMusicProviderBinding.inflate(inflater,container,false);
        return musicProviderBinding.getRoot();
    }
}