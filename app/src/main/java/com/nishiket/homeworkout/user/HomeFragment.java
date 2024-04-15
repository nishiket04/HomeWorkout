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
import com.nishiket.homeworkout.model.PersonalTrainingModel;
import com.nishiket.homeworkout.model.PopularWorkoutModel;
import com.nishiket.homeworkout.model.UserDetailModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;
import com.nishiket.homeworkout.viewmodel.CategoryViewModel;
import com.nishiket.homeworkout.viewmodel.ExerciseViewModel;
import com.nishiket.homeworkout.viewmodel.UserDetailViewModel;
import com.nishiket.homeworkout.viewmodel.WorkoutViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements ExercisesRecyclerViewAdapter.onClickedItem {
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
                Log.d("name", "onChanged: "+ userDetailModel.getName());
                homeBinding.userNameTxt.setText("Hi, "+Character.toUpperCase(userDetailModel.getName().charAt(0)) + userDetailModel.getName().substring(1).toLowerCase());
            }
        });

        CategoryViewModel categoryViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(CategoryViewModel.class);

        categoryViewModel.getData(123);
        categoryViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<CategoryModel>>() {
            @Override
            public void onChanged(List<CategoryModel> categoryModelList) {
                CategoryRecyclerViewAdapter categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(getActivity());
                homeBinding.categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                homeBinding.categoryRecyclerView.setAdapter(categoryRecyclerViewAdapter);
                categoryRecyclerViewAdapter.setCategoryModelList(categoryModelList.subList(0,4));
                categoryRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

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
                exercisesRecyclerViewAdapter.setOnClickedItem(HomeFragment.this);
                homeBinding.exercisesRecyclerView.setNestedScrollingEnabled(false);
            }
        });

        WorkoutViewModel workoutViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(WorkoutViewModel.class);
        workoutViewModel.getData(123);
        workoutViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<PersonalTrainingModel>>() {
            @Override
            public void onChanged(List<PersonalTrainingModel> personalTrainingModelList) {
                PopularWorkoutsRecyclerViewAdapter popularWorkoutsRecyclerViewAdapter = new PopularWorkoutsRecyclerViewAdapter(getActivity());
                homeBinding.popularWorkoutRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                homeBinding.popularWorkoutRecyclerView.setAdapter(popularWorkoutsRecyclerViewAdapter);
                popularWorkoutsRecyclerViewAdapter.setPopularWorkoutModelList(personalTrainingModelList.subList(0,2));
                popularWorkoutsRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

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

    @Override
    public void onCliced(int i, ExercisesModel exercisesModel) {
        ExerciseInfoFragment exerciseInfoFragment = new ExerciseInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",exercisesModel.getid());
        bundle.putString("title",exercisesModel.getExercises());
        bundle.putString("image",exercisesModel.getImage());
        exerciseInfoFragment.setArguments(bundle);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_enter,R.anim.fragment_exit);
        fragmentTransaction.add(R.id.homeFrameLayout,exerciseInfoFragment,"exerciseInfo").addToBackStack("exerciseInfo").commit();
    }
}