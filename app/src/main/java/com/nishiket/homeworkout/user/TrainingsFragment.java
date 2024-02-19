package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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
import com.nishiket.homeworkout.databinding.FragmentTrainingsBinding;
import com.nishiket.homeworkout.model.TrainingModel;

import java.util.ArrayList;
import java.util.List;

public class TrainingsFragment extends Fragment implements TrainingRecyclerViewAdapter.OnItemClickedListiner {
    private FragmentTrainingsBinding trainingsBinding;
    private List<TrainingModel> trainingModelList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        trainingsBinding = FragmentTrainingsBinding.inflate(inflater,container,false);
        return trainingsBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        trainingsBinding.trainingRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        trainingsBinding.trainingRecyclerView.setAdapter(trainingRecyclerViewAdapter);
        trainingRecyclerViewAdapter.setTrainingModelList(trainingModelList);
        trainingRecyclerViewAdapter.notifyDataSetChanged();

        trainingsBinding.trainingRecyclerView.setNestedScrollingEnabled(false);

        trainingRecyclerViewAdapter.setOnItemClickedListiner(this);

        trainingsBinding.personalTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.homeFrameLayout,new PersonalTrainingFragment()).addToBackStack("personalTraining").commit();
            }
        });

        trainingsBinding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.homeFrameLayout,new FilterFragment()).addToBackStack("filter").commit();
            }
        });
    }

    @Override
    public void onItemClicked(int position, TrainingModel trainingModel) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeFrameLayout, new CustomWorkoutDetailsFragment()).addToBackStack("accountInformation").commit();
    }
}