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
import android.widget.ImageView;
import android.widget.TextView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.StretchingWorkoutRecyclerViewAdapter;
import com.nishiket.homeworkout.adapter.WarmupWorkoutRecyclerViewAdapter;
import com.nishiket.homeworkout.adapter.WorkoutWorkoutRecyclerViewAdapter;
import com.nishiket.homeworkout.model.StretchingWorkoutModel;
import com.nishiket.homeworkout.model.WarmUpWorkoutModel;
import com.nishiket.homeworkout.model.WorkoutWorkoutModel;

import java.util.ArrayList;
import java.util.List;

public class CustomWorkoutDetailsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_workout_details, container, false);
    }
    private RecyclerView warmupWorkoutRecyclerView,workoutWorkoutRecyclerView,stretchingWorkoutRecyclerView;
    private List<WarmUpWorkoutModel> warmUpWorkoutModelsList = new ArrayList<>();
    private  List<WorkoutWorkoutModel> workoutWorkoutModelList = new ArrayList<>();
    private List<StretchingWorkoutModel> stretchingWorkoutModelList = new ArrayList<>();
    private ImageView backToPersonalTrainingImage,editWorkoutNameImage;
    private TextView warmupEditWorkoutTxt;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assigId(view);

        WorkoutWorkoutModel w1 = new WorkoutWorkoutModel();
        WorkoutWorkoutModel w2 = new WorkoutWorkoutModel();

        w1.setWorkout("Low Lunge");
        w2.setWorkout("Chair Pose");

        w1.setTime("0:30");
        w2.setTime("0:30");

        w1.setImage(R.drawable.plank);
        w2.setImage(R.drawable.sumo_squat);

        workoutWorkoutModelList.add(w1);
        workoutWorkoutModelList.add(w2);

        WarmUpWorkoutModel wm1 = new WarmUpWorkoutModel();
        WarmUpWorkoutModel wm2 = new WarmUpWorkoutModel();

        wm1.setWorkout("Cobra Stretch");
        wm2.setWorkout("Extended Side Angle");

        wm1.setTime("0:30");
        wm2.setTime("0:30");

        wm1.setImage(R.drawable.plank);
        wm2.setImage(R.drawable.sumo_squat);

        warmUpWorkoutModelsList.add(wm1);
        warmUpWorkoutModelsList.add(wm2);

        StretchingWorkoutModel s1 = new StretchingWorkoutModel();
        StretchingWorkoutModel s2 = new StretchingWorkoutModel();

        s1.setWorkout("Low Lunge");
        s2.setWorkout("Downward Dog");

        s1.setTime("0:30");
        s2.setTime("0:30");

        s1.setImage(R.drawable.lag);
        s2.setImage(R.drawable.sumo_squat);

        stretchingWorkoutModelList.add(s1);
        stretchingWorkoutModelList.add(s2);

        WarmupWorkoutRecyclerViewAdapter warmupWorkoutRecyclerViewAdapter = new WarmupWorkoutRecyclerViewAdapter(getActivity());
        WorkoutWorkoutRecyclerViewAdapter workoutWorkoutRecyclerViewAdapter = new WorkoutWorkoutRecyclerViewAdapter(getActivity());
        StretchingWorkoutRecyclerViewAdapter stretchingWorkoutRecyclerViewAdapter = new StretchingWorkoutRecyclerViewAdapter(getActivity());
        warmupWorkoutRecyclerView.setNestedScrollingEnabled(false);
        workoutWorkoutRecyclerView.setNestedScrollingEnabled(false);
        stretchingWorkoutRecyclerView.setNestedScrollingEnabled(false);
        warmupWorkoutRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        workoutWorkoutRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        stretchingWorkoutRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        warmupWorkoutRecyclerView.setAdapter(warmupWorkoutRecyclerViewAdapter);
        workoutWorkoutRecyclerView.setAdapter(workoutWorkoutRecyclerViewAdapter);
        stretchingWorkoutRecyclerView.setAdapter(stretchingWorkoutRecyclerViewAdapter);
        warmupWorkoutRecyclerViewAdapter.setWarmUpWorkoutModelList(warmUpWorkoutModelsList);
        workoutWorkoutRecyclerViewAdapter.setWorkoutWorkoutModelList(workoutWorkoutModelList);
        stretchingWorkoutRecyclerViewAdapter.setStretchingWorkoutModelList(stretchingWorkoutModelList);
        workoutWorkoutRecyclerViewAdapter.notifyDataSetChanged();
        warmupWorkoutRecyclerViewAdapter.notifyDataSetChanged();
        stretchingWorkoutRecyclerViewAdapter.notifyDataSetChanged();

        warmupEditWorkoutTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFrameLayout,new WarmUpsEditFragment()).addToBackStack("accountInformation").commit();
            }
        });

    }

    private void assigId(View view) {
        warmupWorkoutRecyclerView = view.findViewById(R.id.warmupWorkoutRecyclerView);
        workoutWorkoutRecyclerView = view.findViewById(R.id.workoutWorkoutRecyclerView);
        stretchingWorkoutRecyclerView = view.findViewById(R.id.stretchingWorkoutRecyclerView);
        backToPersonalTrainingImage = view.findViewById(R.id.backToPersonalTrainingImage);
        editWorkoutNameImage = view.findViewById(R.id.editWorkoutNameImage);
        warmupEditWorkoutTxt = view.findViewById(R.id.warmupEditWorkoutTxt);
    }
}