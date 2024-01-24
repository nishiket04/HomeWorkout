package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.TrainingRecyclerViewAdapter;
import com.nishiket.homeworkout.model.TrainingModel;

import java.util.ArrayList;
import java.util.List;

public class TrainingsFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trainings, container, false);
    }

    private List<TrainingModel> trainingModelList = new ArrayList<>();
    private RecyclerView trainingRecyclerView;
    private LinearLayout personalTraining;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trainingRecyclerView = view.findViewById(R.id.trainingRecyclerView);
        personalTraining = view.findViewById(R.id.personalTraining);

            TrainingModel t1 = new TrainingModel();
            TrainingModel t2 = new TrainingModel();
            TrainingModel t3= new TrainingModel();
            TrainingModel t4 = new TrainingModel();
            TrainingModel t5 = new TrainingModel();
            TrainingModel t6 = new TrainingModel();
            TrainingModel t7 = new TrainingModel();

            t1.setImage(R.drawable.women_workingout);
            t2.setImage(R.drawable.man_workingout);
            t3.setImage(R.drawable.women_workingout);
            t4.setImage(R.drawable.man_workingout);
            t5.setImage(R.drawable.women_workingout);
            t6.setImage(R.drawable.man_workingout);
            t7.setImage(R.drawable.women_workingout);

            t1.setWorkout("Bodyweight Stretch");
            t2.setWorkout("Full Body Fast");
            t3.setWorkout("Express Tabata");
            t4.setWorkout("Express Tabata");
            t5.setWorkout("Glutes & Abs");
            t6.setWorkout("Interval Pilates");
            t7.setWorkout("Bodyweight Stretch");

            t1.setLevel("Beginner");
            t2.setLevel("Beginner");
            t3.setLevel("Beginner");
            t4.setLevel("Beginner");
            t5.setLevel("Beginner");
            t6.setLevel("Beginner");
            t7.setLevel("Beginner");

            t1.setTime("32 min");
            t2.setTime("32 min");
            t3.setTime("32 min");
            t4.setTime("32 min");
            t5.setTime("32 min");
            t6.setTime("32 min");
            t7.setTime("32 min");

            trainingModelList.add(t1);
            trainingModelList.add(t2);
            trainingModelList.add(t3);
            trainingModelList.add(t4);
            trainingModelList.add(t5);
            trainingModelList.add(t6);
            trainingModelList.add(t7);



        TrainingRecyclerViewAdapter trainingRecyclerViewAdapter = new TrainingRecyclerViewAdapter(getActivity());
        trainingRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        trainingRecyclerView.setAdapter(trainingRecyclerViewAdapter);
        trainingRecyclerViewAdapter.setTrainingModelList(trainingModelList);
        trainingRecyclerViewAdapter.notifyDataSetChanged();

        trainingRecyclerView.setNestedScrollingEnabled(false);

        personalTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.homeFrameLayout,new PersonalTrainingFragment()).addToBackStack("personalTraining").commit();
            }
        });
    }
}