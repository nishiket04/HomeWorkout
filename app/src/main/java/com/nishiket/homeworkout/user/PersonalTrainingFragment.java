package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.PersonalTrainingRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentPersonalTrainingBinding;
import com.nishiket.homeworkout.model.PersonalTrainingModel;
import com.nishiket.homeworkout.viewmodel.WorkoutViewModel;

import java.util.ArrayList;
import java.util.List;

public class PersonalTrainingFragment extends Fragment implements PersonalTrainingRecyclerViewAdapter.OnItemClickListener {
    private FragmentPersonalTrainingBinding personalTrainingBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        personalTrainingBinding = FragmentPersonalTrainingBinding.inflate(inflater,container,false);
        return personalTrainingBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WorkoutViewModel workoutViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(WorkoutViewModel.class);

        workoutViewModel.getData(123);
        workoutViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<PersonalTrainingModel>>() {
            @Override
            public void onChanged(List<PersonalTrainingModel> personalTrainingModelList) {
                PersonalTrainingRecyclerViewAdapter personalTrainingRecyclerViewAdapter = new PersonalTrainingRecyclerViewAdapter(getActivity());
                personalTrainingBinding.personalTrainingRecylerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                personalTrainingBinding.personalTrainingRecylerView.setAdapter(personalTrainingRecyclerViewAdapter);
                personalTrainingRecyclerViewAdapter.setPersonalTrainingModelList(personalTrainingModelList);
                personalTrainingRecyclerViewAdapter.notifyDataSetChanged();
                personalTrainingBinding.scrollView.stopNestedScroll();
                personalTrainingRecyclerViewAdapter.setOnItemClickListener(PersonalTrainingFragment.this);
            }
        });

        personalTrainingBinding.backToTrainingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFrameLayout,new TrainingsFragment()).commit();
            }
        });

        personalTrainingBinding.createPersonalTrainingTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFrameLayout,new CreatePersonalTrainingFragment()).addToBackStack("createPersonalTraing").commit();
            }
        });
    }

    @Override
    public void onItemClick(int position, PersonalTrainingModel personalTrainingModel) {
        // Open the new fragment and pass data
        CustomWorkoutDetailsFragment newFragment = new CustomWorkoutDetailsFragment();
//
//        // Pass data using Bundle
//        Bundle bundle = new Bundle();
//        bundle.putString("workoutName", personalTrainingModel.getWorkout());
//        bundle.putString("level", personalTrainingModel.getLevel());
//        // Add other data as needed
//        newFragment.setArguments(bundle);

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeFrameLayout, newFragment).addToBackStack("accountInformation").commit();
    }
}