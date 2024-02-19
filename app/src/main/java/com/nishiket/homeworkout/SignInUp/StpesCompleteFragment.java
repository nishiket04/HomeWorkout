package com.nishiket.homeworkout.SignInUp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentStpesCompleteBinding;
import com.nishiket.homeworkout.user.MainActivity;

public class StpesCompleteFragment extends Fragment {

    private FragmentStpesCompleteBinding stpesCompleteBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        stpesCompleteBinding = FragmentStpesCompleteBinding.inflate(inflater,container,false);
        return stpesCompleteBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stpesCompleteBinding.stepsCompleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
                SignInUpActivity signInUpActivity = (SignInUpActivity) getActivity();
                signInUpActivity.finish();
            }
        });


    }
}