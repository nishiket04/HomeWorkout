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
import android.widget.ImageView;
import android.widget.TextView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.PersonalTrainingRecyclerViewAdapter;
import com.nishiket.homeworkout.model.PersonalTrainingModel;

import java.util.ArrayList;
import java.util.List;

public class PersonalTrainingFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_training, container, false);
    }
    private RecyclerView personalTrainingRecyclerView;
    private ImageView backToTrainingImage;
    private TextView createPersonalTrainingTxt;
    private List<PersonalTrainingModel> personalTrainingModelList = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assignId(view);
        PersonalTrainingModel p1 = new PersonalTrainingModel();
        PersonalTrainingModel p2 = new PersonalTrainingModel();
        PersonalTrainingModel p3 = new PersonalTrainingModel();
        PersonalTrainingModel p4 = new PersonalTrainingModel();
        PersonalTrainingModel p5 = new PersonalTrainingModel();
        PersonalTrainingModel p6 = new PersonalTrainingModel();
        PersonalTrainingModel p7 = new PersonalTrainingModel();

        p1.setImage(R.drawable.women_workingout);
        p2.setImage(R.drawable.man_workingout);
        p3.setImage(R.drawable.women_workingout);
        p4.setImage(R.drawable.man_workingout);
        p5.setImage(R.drawable.women_workingout);
        p6.setImage(R.drawable.man_workingout);
        p7.setImage(R.drawable.women_workingout);

        p1.setWorkout("Bodyweight Stretch");
        p2.setWorkout("Full Body Fast");
        p3.setWorkout("Express Tabata");
        p4.setWorkout("Express Tabata");
        p5.setWorkout("Glutes & Abs");
        p6.setWorkout("Interval Pilates");
        p7.setWorkout("Bodyweight Stretch");

        p1.setLevel("Beginner");
        p2.setLevel("Beginner");
        p3.setLevel("Beginner");
        p4.setLevel("Beginner");
        p5.setLevel("Beginner");
        p6.setLevel("Beginner");
        p7.setLevel("Beginner");

        p1.setTime("32 min");
        p2.setTime("32 min");
        p3.setTime("32 min");
        p4.setTime("32 min");
        p5.setTime("32 min");
        p6.setTime("32 min");
        p7.setTime("32 min");

        personalTrainingModelList.add(p1);
        personalTrainingModelList.add(p2);
        personalTrainingModelList.add(p3);
        personalTrainingModelList.add(p4);
        personalTrainingModelList.add(p5);
        personalTrainingModelList.add(p6);
        personalTrainingModelList.add(p7);

        PersonalTrainingRecyclerViewAdapter personalTrainingRecyclerViewAdapter = new PersonalTrainingRecyclerViewAdapter(getActivity());
        personalTrainingRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        personalTrainingRecyclerView.setNestedScrollingEnabled(false);
        personalTrainingRecyclerView.setAdapter(personalTrainingRecyclerViewAdapter);
        personalTrainingRecyclerViewAdapter.setPersonalTrainingModelList(personalTrainingModelList);
        personalTrainingRecyclerViewAdapter.notifyDataSetChanged();

        backToTrainingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFrameLayout,new TrainingsFragment()).commit();
            }
        });

        createPersonalTrainingTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFrameLayout,new CreatePersonalTrainingFragment()).addToBackStack("createPersonalTraing").commit();
            }
        });
    }

    private void assignId(View view) {
        personalTrainingRecyclerView = view.findViewById(R.id.personalTrainingRecylerView);
        createPersonalTrainingTxt = view.findViewById(R.id.createPersonalTrainingTxt);
        backToTrainingImage = view.findViewById(R.id.backToTrainingImage);
    }
}