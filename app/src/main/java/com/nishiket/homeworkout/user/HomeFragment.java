package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.CategoryRecyclerViewAdapter;
import com.nishiket.homeworkout.adapter.ExercisesRecyclerViewAdapter;
import com.nishiket.homeworkout.adapter.PopularWorkoutsRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentHomeBinding;
import com.nishiket.homeworkout.model.CategoryModel;
import com.nishiket.homeworkout.model.ExercisesModel;
import com.nishiket.homeworkout.model.PopularWorkoutModel;
import com.nishiket.homeworkout.model.UserDetailModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;
import com.nishiket.homeworkout.viewmodel.ExerciseViewModel;
import com.nishiket.homeworkout.viewmodel.UserDetailViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private List<CategoryModel> categoryModelList = new ArrayList<>();
    private List<PopularWorkoutModel> popularWorkoutModelList = new ArrayList<>();
    private FragmentHomeBinding homeBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeBinding = FragmentHomeBinding.inflate(inflater,container,false);
        return homeBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AuthViewModel authViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        UserDetailViewModel userDetailViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(UserDetailViewModel.class);

        userDetailViewModel.getData(123,authViewModel.getCurrentUser().getEmail());
        userDetailViewModel.getUserDetailModelMutableLiveData().observe(getViewLifecycleOwner(), new Observer<UserDetailModel>() {
            @Override
            public void onChanged(UserDetailModel userDetailModel) {
                homeBinding.userNameTxt.setText("Hi, "+Character.toUpperCase(userDetailModel.getName().charAt(0)) + userDetailModel.getName().substring(1).toLowerCase());
            }
        });
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

        ExerciseViewModel exerciseViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(ExerciseViewModel.class);

        exerciseViewModel.getExercises(123);
        exerciseViewModel.getExercisesListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<ExercisesModel>>() {
            @Override
            public void onChanged(List<ExercisesModel> exercisesModelList) {
                ExercisesRecyclerViewAdapter exercisesRecyclerViewAdapter = new ExercisesRecyclerViewAdapter(getActivity());
                homeBinding.exercisesRecyclerView.setLayoutManager( new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                homeBinding.exercisesRecyclerView.setAdapter(exercisesRecyclerViewAdapter);
                exercisesRecyclerViewAdapter.setExercisesModelList(exercisesModelList.subList(0,4));
                exercisesRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

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
        homeBinding.categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        homeBinding.categoryRecyclerView.setAdapter(categoryRecyclerViewAdapter);
        categoryRecyclerViewAdapter.setCategoryModelList(categoryModelList);
        categoryRecyclerViewAdapter.notifyDataSetChanged();

        PopularWorkoutsRecyclerViewAdapter popularWorkoutsRecyclerViewAdapter = new PopularWorkoutsRecyclerViewAdapter(getActivity());
        homeBinding.popularWorkoutRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        homeBinding.popularWorkoutRecyclerView.setAdapter(popularWorkoutsRecyclerViewAdapter);
        popularWorkoutsRecyclerViewAdapter.setPopularWorkoutModelList(popularWorkoutModelList);
        popularWorkoutsRecyclerViewAdapter.notifyDataSetChanged();

        homeBinding.exercisesRecyclerView.setNestedScrollingEnabled(false);

        homeBinding.viewallCategoryHomeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.homeFrameLayout,new ViewallCategoryFragment()).commit();
            }
        });

        homeBinding.viewallExercisesHomeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.homeFrameLayout,new ViewallExerrcisesFragment()).commit();
            }
        });

       homeBinding.notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFrameLayout,new NotificationFragment()).addToBackStack("notification").commit();
            }
        });

    }

}