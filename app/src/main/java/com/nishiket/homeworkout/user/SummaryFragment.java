package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentSummaryBinding;

public class SummaryFragment extends Fragment {
    private FragmentSummaryBinding summaryBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        summaryBinding = FragmentSummaryBinding.inflate(inflater,container,false);
        return summaryBinding.getRoot();
    }
}