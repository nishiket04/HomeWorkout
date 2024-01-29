package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.WorkoutDetailsEquipmentRecylerViewAdapter;
import com.nishiket.homeworkout.adapter.WorkoutDetailsWarmUpRecyclerViewAdapter;
import com.nishiket.homeworkout.adapter.WorkoutDetailsWorkoutRecyclerViewAdapter;
import com.nishiket.homeworkout.model.WorkoutDetailsEquipmentModel;
import com.nishiket.homeworkout.model.WorkoutDetailsWarmUpModel;
import com.nishiket.homeworkout.model.WorkoutDetailsWorkoutModel;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDetailsFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_details, container, false);
    }
    private RecyclerView workoutDetailsEquipmentRecyclerView,workoutDetailsWarmupRecyclerView,workoutDetailsWorkoutRecyclerView;
    private List<WorkoutDetailsEquipmentModel> workoutDetailsEquipmentModelList = new ArrayList<>();
    private List<WorkoutDetailsWarmUpModel> workoutDetailsWarmUpModelList = new ArrayList<>();
    private List<WorkoutDetailsWorkoutModel> workoutDetailsWorkoutModelList = new ArrayList<>();
    private LinearLayout schudelWorkoutDetailsLn,pickaPlayListLn;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assignId(view);

        WorkoutDetailsEquipmentModel w1 = new WorkoutDetailsEquipmentModel();
        WorkoutDetailsEquipmentModel w2 = new WorkoutDetailsEquipmentModel();

        w1.setEquipment("2 Dumbells");
        w2.setEquipment("Mat");

        w1.setImage(R.drawable.sumo_squat);
        w2.setImage(R.drawable.back);

        workoutDetailsEquipmentModelList.add(w1);
        workoutDetailsEquipmentModelList.add(w2);

        WorkoutDetailsWorkoutModel ww1 = new WorkoutDetailsWorkoutModel();
        WorkoutDetailsWorkoutModel ww2 = new WorkoutDetailsWorkoutModel();

        ww1.setImage(R.drawable.plank);
        ww2.setImage(R.drawable.lag);

        ww1.setReps("x 20");
        ww2.setReps("x 12");

        ww1.setWorkout("Cobra Stretch");
        ww2.setWorkout("Plank Ups");

        workoutDetailsWorkoutModelList.add(ww1);
        workoutDetailsWorkoutModelList.add(ww2);

        WorkoutDetailsWarmUpModel wm1 = new WorkoutDetailsWarmUpModel();
        WorkoutDetailsWarmUpModel wm2 = new WorkoutDetailsWarmUpModel();

        wm1.setTime("0:40");
        wm2.setTime("0:30");

        wm1.setWorkout("Double Heel Tabs");
        wm2.setWorkout("Lung Jumps Atlernated");

        wm1.setImage(R.drawable.sumo_squat);
        wm2.setImage(R.drawable.plank);

        workoutDetailsWarmUpModelList.add(wm1);
        workoutDetailsWarmUpModelList.add(wm2);

        WorkoutDetailsEquipmentRecylerViewAdapter workoutDetailsEquipmentRecylerViewAdapter = new WorkoutDetailsEquipmentRecylerViewAdapter(getActivity());
        workoutDetailsEquipmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        workoutDetailsEquipmentRecyclerView.setAdapter(workoutDetailsEquipmentRecylerViewAdapter);
        workoutDetailsEquipmentRecylerViewAdapter.setWorkoutDetailsEquipmentModelList(workoutDetailsEquipmentModelList);
        workoutDetailsEquipmentRecylerViewAdapter.notifyDataSetChanged();

        WorkoutDetailsWorkoutRecyclerViewAdapter workoutDetailsWorkoutRecyclerViewAdapter = new WorkoutDetailsWorkoutRecyclerViewAdapter(getActivity());
        workoutDetailsWorkoutRecyclerView.setNestedScrollingEnabled(false);
        workoutDetailsWorkoutRecyclerView.setAdapter(workoutDetailsWorkoutRecyclerViewAdapter);
        workoutDetailsWorkoutRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        workoutDetailsWorkoutRecyclerViewAdapter.setWorkoutDetailsWorkoutModelList(workoutDetailsWorkoutModelList);
        workoutDetailsWorkoutRecyclerViewAdapter.notifyDataSetChanged();

        WorkoutDetailsWarmUpRecyclerViewAdapter workoutDetailsWarmUpRecyclerViewAdapter = new WorkoutDetailsWarmUpRecyclerViewAdapter(getActivity());
        workoutDetailsWarmupRecyclerView.setNestedScrollingEnabled(false);
        workoutDetailsWarmupRecyclerView.setLayoutManager( new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        workoutDetailsWarmupRecyclerView.setAdapter(workoutDetailsWarmUpRecyclerViewAdapter);
        workoutDetailsWarmUpRecyclerViewAdapter.setWorkoutDetailsWarmUpModelList(workoutDetailsWarmUpModelList);
        workoutDetailsWarmUpRecyclerViewAdapter.notifyDataSetChanged();

        schudelWorkoutDetailsLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.homeFrameLayout,new ScheduleWorkoutFragment()).addToBackStack(null).commit();
            }
        });

        pickaPlayListLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFrameLayout,new MusicProviderFragment()).addToBackStack(null).commit();
            }
        });


    }

    private void assignId(View view) {
        workoutDetailsEquipmentRecyclerView = view.findViewById(R.id.workoutDetailsEquipmentRecyclerView);
        workoutDetailsWarmupRecyclerView = view.findViewById(R.id.workoutDetailsExercisiesRecyclerView);
        workoutDetailsWorkoutRecyclerView = view.findViewById(R.id.workoutDetailsWorkoutRecyclerView);
        schudelWorkoutDetailsLn = view.findViewById(R.id.scgedulWorkoutDetailsLn);
        pickaPlayListLn = view.findViewById(R.id.pickaPlayListLn);
    }
}