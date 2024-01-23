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
import android.widget.TextView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.CategoryRecyclerViewAdapter;
import com.nishiket.homeworkout.adapter.ExercisesRecyclerViewAdapter;
import com.nishiket.homeworkout.adapter.PopularWorkoutsRecyclerViewAdapter;
import com.nishiket.homeworkout.model.CategoryModel;
import com.nishiket.homeworkout.model.ExercisesModel;
import com.nishiket.homeworkout.model.PopularWorkoutModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private RecyclerView categoryRexcyclerView,exercisesRecyclerView,popularWorkoutRecyclerView;
    private TextView viewallCategoryHomeTxt;
    private List<CategoryModel> categoryModelList = new ArrayList<>();
    private  List<ExercisesModel> exercisesModelsList = new ArrayList<>();
    private List<PopularWorkoutModel> popularWorkoutModelList = new ArrayList<>();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assignId(view);

        CategoryModel c1 = new CategoryModel();
        CategoryModel c2 = new CategoryModel();
        CategoryModel c3 = new CategoryModel();
        CategoryModel c4 = new CategoryModel();

        c1.setImage(R.drawable.runing);
        c2.setImage(R.drawable.yoga);
        c3.setImage(R.drawable.flip);
        c4.setImage(R.drawable.squat);

        c1.setCategory("Cardio");
        c2.setCategory("Yoga");
        c3.setCategory("Stretch");
        c4.setCategory("Gym");

        categoryModelList.add(c1);
        categoryModelList.add(c2);
        categoryModelList.add(c3);
        categoryModelList.add(c4);

        ExercisesModel e1 = new ExercisesModel();
        ExercisesModel e2 = new ExercisesModel();
        ExercisesModel e3 = new ExercisesModel();
        ExercisesModel e4 = new ExercisesModel();

        e1.setExercise("Front and Back Lunge");
        e2.setExercise("Side Plank");
        e3.setExercise("Arm circles");
        e4.setExercise("Sumo Squat");

        e1.setImage(R.drawable.back);
        e2.setImage(R.drawable.plank);
        e3.setImage(R.drawable.lag);
        e4.setImage(R.drawable.sumo_squat);

        e1.setTime("00:30");
        e2.setTime("00:30");
        e3.setTime("00:30");
        e4.setTime("00:30");

        exercisesModelsList.add(e1);
        exercisesModelsList.add(e2);
        exercisesModelsList.add(e3);
        exercisesModelsList.add(e4);

        PopularWorkoutModel p1 = new PopularWorkoutModel();
        PopularWorkoutModel p2 = new PopularWorkoutModel();

        p1.setImage(R.drawable.women_workingout);
        p2.setImage(R.drawable.man_workingout);

        p1.setWorkout("Rapid Lower Body");
        p2.setWorkout("Bodyweight Stretch");

        p1.setType("Beginner");
        p2.setType("Beginner");

        p1.setTime("42 min");
        p2.setTime("25 min");

        popularWorkoutModelList.add(p1);
        popularWorkoutModelList.add(p2);


        CategoryRecyclerViewAdapter categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(getActivity());
        categoryRexcyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        categoryRexcyclerView.setAdapter(categoryRecyclerViewAdapter);
        categoryRecyclerViewAdapter.setCategoryModelList(categoryModelList);
        categoryRecyclerViewAdapter.notifyDataSetChanged();


        ExercisesRecyclerViewAdapter exercisesRecyclerViewAdapter = new ExercisesRecyclerViewAdapter(getActivity());
        exercisesRecyclerView.setLayoutManager( new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        exercisesRecyclerView.setAdapter(exercisesRecyclerViewAdapter);
        exercisesRecyclerViewAdapter.setExercisesModelList(exercisesModelsList);
        exercisesRecyclerViewAdapter.notifyDataSetChanged();

        PopularWorkoutsRecyclerViewAdapter popularWorkoutsRecyclerViewAdapter = new PopularWorkoutsRecyclerViewAdapter(getActivity());
        popularWorkoutRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        popularWorkoutRecyclerView.setAdapter(popularWorkoutsRecyclerViewAdapter);
        popularWorkoutsRecyclerViewAdapter.setPopularWorkoutModelList(popularWorkoutModelList);
        popularWorkoutsRecyclerViewAdapter.notifyDataSetChanged();

        exercisesRecyclerView.setNestedScrollingEnabled(false);

        viewallCategoryHomeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.homeFrameLayout,new ViewallCategoryFragment()).commit();
            }
        });

    }

    private void assignId(View view) {
        categoryRexcyclerView = view.findViewById(R.id.categoryRecyclerView);
        exercisesRecyclerView = view.findViewById(R.id.exercisesRecyclerView);
        popularWorkoutRecyclerView = view.findViewById(R.id.popularWorkoutRecyclerView);
        viewallCategoryHomeTxt = view.findViewById(R.id.viewallCategoryHomeTxt);
    }
}