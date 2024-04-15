package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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
import android.widget.LinearLayout;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.TrainingRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentTrainingsBinding;
import com.nishiket.homeworkout.model.PersonalTrainingModel;
import com.nishiket.homeworkout.viewmodel.WorkoutViewModel;

import java.util.ArrayList;
import java.util.List;

public class TrainingsFragment extends Fragment implements TrainingRecyclerViewAdapter.OnItemClickedListiner {
    private FragmentTrainingsBinding trainingsBinding;
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

        WorkoutViewModel workoutViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(WorkoutViewModel.class);
        workoutViewModel.getData(123);
        workoutViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<PersonalTrainingModel>>() {
            @Override
            public void onChanged(List<PersonalTrainingModel> personalTrainingModelList) {
                TrainingRecyclerViewAdapter trainingRecyclerViewAdapter = new TrainingRecyclerViewAdapter(getActivity());
                trainingsBinding.trainingRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                trainingsBinding.trainingRecyclerView.setAdapter(trainingRecyclerViewAdapter);
                trainingRecyclerViewAdapter.setTrainingModelList(personalTrainingModelList);
                trainingRecyclerViewAdapter.notifyDataSetChanged();
//                trainingsBinding.trainingRecyclerView.setNestedScrollingEnabled(false);
                trainingsBinding.scroll.stopNestedScroll();
                trainingRecyclerViewAdapter.setOnItemClickedListiner(TrainingsFragment.this);

            }
        });

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
                fragmentTransaction.setCustomAnimations(R.anim.fragment_enter,R.anim.fragment_exit);
                fragmentTransaction.add(R.id.homeFrameLayout,new FilterFragment(),"filter").addToBackStack("filter").commit();
            }
        });
    }

    @Override
    public void onItemClicked(int position, PersonalTrainingModel personalTrainingModel) {
        WorkoutDetailsFragment workoutDetailsFragment = new WorkoutDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",personalTrainingModel.getId());
        bundle.putString("title",personalTrainingModel.getWorkout());
        bundle.putString("level",personalTrainingModel.getLevel());
        bundle.putString("time",personalTrainingModel.getTime());
        bundle.putString("image",personalTrainingModel.getImage());
        workoutDetailsFragment.setArguments(bundle);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeFrameLayout,workoutDetailsFragment,"workout").addToBackStack("workout").commit();
    }
}