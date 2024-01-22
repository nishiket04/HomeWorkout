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
import com.nishiket.homeworkout.user.MainActivity;

public class StpesCompleteFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stpes_complete, container, false);
    }

    private ProgressBar progressBar;
    private AppCompatButton stepsCompleteBtn;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stepsCompleteBtn = view.findViewById(R.id.stepsCompleteBtn);
        stepsCompleteBtn.setOnClickListener(new View.OnClickListener() {
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