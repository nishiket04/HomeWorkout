package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.WorkoutDetailsWorkoutRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentStartTrainingBinding;
import com.nishiket.homeworkout.model.ExercisesModel;
import com.nishiket.homeworkout.viewmodel.ExerciseViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class StartTrainingFragment extends Fragment {
    private FragmentStartTrainingBinding startTrainingBinding;
    private List<ExercisesModel> exercisesList = new ArrayList<>();
    private int currentIndex = 0;
    private CountDownTimer countDownTimer;
    private boolean isTimerRunning = false;
    private long timeRemainingMillis;
   private boolean isPaused= false;
   private boolean isLast = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        startTrainingBinding = FragmentStartTrainingBinding.inflate(inflater, container, false);
        return startTrainingBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        ExerciseViewModel exerciseViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(ExerciseViewModel.class);
        exerciseViewModel.getSelectedExercises(123, bundle.getString("list"));
        exerciseViewModel.getExercisesListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<ExercisesModel>>() {
            @Override
            public void onChanged(List<ExercisesModel> exercisesModels) {
                exercisesList = exercisesModels;
                startTrainingBinding.nextBtn.setEnabled(false);
                startExercise();
            }
        });

        startTrainingBinding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!startTrainingBinding.nextBtn.isEnabled()){
                    Toast.makeText(getContext(), "Button is Disabled till countdown is completed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        startTrainingBinding.pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPaused){
                    resumeTimer();
                    startTrainingBinding.pauseBtn.setText("Pause");
                    isPaused = false;
                }
                else {
                    pauseTimer();
                    startTrainingBinding.pauseBtn.setText("Resume");
                    isPaused = true;
                }
            }
        });


        startTrainingBinding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLast){
                    FragmentManager fragmentManager = getParentFragmentManager();
                    fragmentManager.popBackStack();
                }
                else {
                    nextExercise();
                }
            }
        });
    }

    private void startExercise() {
        if (currentIndex < exercisesList.size()) {
            ExercisesModel currentExercise = exercisesList.get(currentIndex);
            if (currentIndex<exercisesList.size()-1){
                ExercisesModel next = exercisesList.get(currentIndex+1);
                startTrainingBinding.workoutDetailsrepsTxt.setText(next.getTime());
                startTrainingBinding.workoutDetailWorkoutTxt.setText(next.getExercises());
                Glide.with(this).load(next.getImage()).into(startTrainingBinding.image);
            }
            else {
                startTrainingBinding.workoutDetailsrepsTxt.setText("00:00");
                startTrainingBinding.workoutDetailWorkoutTxt.setText("No Exercises");
                Glide.with(this).load(R.drawable.no_image).into(startTrainingBinding.image);
            }
            if(currentIndex == exercisesList.size()-1){
                startTrainingBinding.nextBtn.setText("Finish");
                isLast = true;

            }
            startTrainingBinding.textView40.setText(currentExercise.getTime());
            startTrainingBinding.textView20.setText(currentExercise.getExercises());
            Glide.with(this).load(currentExercise.getImage()).into(startTrainingBinding.currentExercisesImage);


            // Start countdown timer
            long millisInFuture =parseTimeString(currentExercise.getTime()); // Convert seconds to milliseconds
            startTimer(millisInFuture);
        }
    }

    private void startTimer(long millisInFuture) {
        countDownTimer = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update time remaining
                timeRemainingMillis = millisUntilFinished;
                long secondsRemaining = millisUntilFinished / 1000;
                startTrainingBinding.textView40.setText(String.format("%02d:%02d", secondsRemaining / 60, secondsRemaining % 60));
            }

            @Override
            public void onFinish() {
                // Enable "Next" button when time is complete
                startTrainingBinding.nextBtn.setEnabled(true);
            }
        }.start();
        isTimerRunning = true;
    }

    private void pauseTimer() {
        if (isTimerRunning) {
            countDownTimer.cancel();
            isTimerRunning = false;
        }
    }
    private void resumeTimer() {
        if (!isTimerRunning) {
            startTimer(timeRemainingMillis);
        }
    }

    private void nextExercise() {
        // Move to the next exercise
        currentIndex++;
        // Reset "Next" button state
        startTrainingBinding.nextBtn.setEnabled(false);
        // Start the next exercise
        startExercise();
    }
    private long parseTimeString(String time) {
        // Split the time string into hours, minutes, and seconds
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        // Calculate total milliseconds
        return (hours * 3600 + minutes * 60 + seconds) * 1000;
    }
}