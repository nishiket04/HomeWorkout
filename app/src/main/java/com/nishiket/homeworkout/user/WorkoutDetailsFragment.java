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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.WorkoutDetailsEquipmentRecylerViewAdapter;
import com.nishiket.homeworkout.adapter.WorkoutDetailsWarmUpRecyclerViewAdapter;
import com.nishiket.homeworkout.adapter.WorkoutDetailsWorkoutRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentWorkoutDetailsBinding;
import com.nishiket.homeworkout.model.EquipmentModel;
import com.nishiket.homeworkout.model.ExercisesModel;
import com.nishiket.homeworkout.model.WorkoutInfoModel;
import com.nishiket.homeworkout.model.WormUpModel;
import com.nishiket.homeworkout.viewmodel.EquipmentViewModel;
import com.nishiket.homeworkout.viewmodel.ExerciseViewModel;
import com.nishiket.homeworkout.viewmodel.WorkoutInfoViewModel;
import com.nishiket.homeworkout.viewmodel.WormUpViewModel;

import java.util.List;

public class WorkoutDetailsFragment extends Fragment implements WorkoutDetailsWorkoutRecyclerViewAdapter.OnClickedItem,WorkoutDetailsWarmUpRecyclerViewAdapter.OnClickedItem {
    private FragmentWorkoutDetailsBinding workoutDetailsBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        workoutDetailsBinding = FragmentWorkoutDetailsBinding.inflate(inflater,container,false);
        return workoutDetailsBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WorkoutInfoViewModel workoutInfoViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(WorkoutInfoViewModel.class);
        EquipmentViewModel equipmentViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(EquipmentViewModel.class);
        ExerciseViewModel exerciseViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(ExerciseViewModel.class);
        WormUpViewModel wormUpViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(WormUpViewModel.class);
        Bundle bundle = getArguments();
        workoutDetailsBinding.title.setText(bundle.getString("title"));
        workoutDetailsBinding.time.setText(bundle.getString("time")+" min");
        workoutDetailsBinding.level.setText(bundle.getString("level"));
        Glide.with(this).load(bundle.getString("image")).into(workoutDetailsBinding.imageView3);

        // main all info
        workoutInfoViewModel.getWorkoutInfo(123,bundle.getInt("id"));
        workoutInfoViewModel.getWorkoutInfoModelMutableLiveData().observe(getViewLifecycleOwner(), new Observer<WorkoutInfoModel>() {
            @Override
            public void onChanged(WorkoutInfoModel workoutInfoModel) {
            workoutDetailsBinding.cal.setText(workoutInfoModel.getCal()+" Kcal");
            workoutDetailsBinding.detail.setText(workoutInfoModel.getDetail());

            //equipment
            equipmentViewModel.getEquipment(123, workoutInfoModel.getEquipment().replace("[","").replace("]",""));
            equipmentViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<EquipmentModel>>() {
                @Override
                public void onChanged(List<EquipmentModel> equipmentModels) {
                    workoutDetailsBinding.itemCount.setText(""+equipmentModels.size()+" Items");
                    WorkoutDetailsEquipmentRecylerViewAdapter workoutDetailsEquipmentRecylerViewAdapter = new WorkoutDetailsEquipmentRecylerViewAdapter(getActivity());
                    workoutDetailsBinding.workoutDetailsEquipmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                    workoutDetailsBinding.workoutDetailsEquipmentRecyclerView.setAdapter(workoutDetailsEquipmentRecylerViewAdapter);
                    workoutDetailsEquipmentRecylerViewAdapter.setWorkoutDetailsEquipmentModelList(equipmentModels);
                    workoutDetailsEquipmentRecylerViewAdapter.notifyDataSetChanged();
                }
            });

            //workouts
            exerciseViewModel.getSelectedExercises(123,workoutInfoModel.getWorkouts().replace("[","").replace("]",""));
            exerciseViewModel.getExercisesListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<ExercisesModel>>() {
                @Override
                public void onChanged(List<ExercisesModel> exercisesModels) {
                    workoutDetailsBinding.workoutCount.setText(""+exercisesModels.size()+" Exercises");
                    WorkoutDetailsWorkoutRecyclerViewAdapter workoutDetailsWorkoutRecyclerViewAdapter = new WorkoutDetailsWorkoutRecyclerViewAdapter(getActivity());
                    workoutDetailsBinding.workoutDetailsWorkoutRecyclerView.setNestedScrollingEnabled(false);
                    workoutDetailsBinding.workoutDetailsWorkoutRecyclerView.setAdapter(workoutDetailsWorkoutRecyclerViewAdapter);
                    workoutDetailsBinding.workoutDetailsWorkoutRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                    workoutDetailsWorkoutRecyclerViewAdapter.setWorkoutDetailsWorkoutModelList(exercisesModels);
                    workoutDetailsWorkoutRecyclerViewAdapter.setOnClickedItem(WorkoutDetailsFragment.this);
                    workoutDetailsWorkoutRecyclerViewAdapter.notifyDataSetChanged();
                }
              });

            //wormUp
            wormUpViewModel.getWormups(123,workoutInfoModel.getWarmup().replace("[","").replace("]",""));
            wormUpViewModel.getWormUpMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<WormUpModel>>() {
                @Override
                public void onChanged(List<WormUpModel> wormUpModels) {
                    workoutDetailsBinding.wormUpCount.setText(""+wormUpModels.size()+" Exercises");
                    WorkoutDetailsWarmUpRecyclerViewAdapter workoutDetailsWarmUpRecyclerViewAdapter = new WorkoutDetailsWarmUpRecyclerViewAdapter(getActivity());
                    workoutDetailsBinding.workoutDetailsExercisiesRecyclerView.setNestedScrollingEnabled(false);
                    workoutDetailsBinding.workoutDetailsExercisiesRecyclerView.setLayoutManager( new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                    workoutDetailsBinding.workoutDetailsExercisiesRecyclerView.setAdapter(workoutDetailsWarmUpRecyclerViewAdapter);
                    workoutDetailsWarmUpRecyclerViewAdapter.setWorkoutDetailsWarmUpModelList(wormUpModels);
                    workoutDetailsWarmUpRecyclerViewAdapter.setOnClickedItem(WorkoutDetailsFragment.this);
                    workoutDetailsWarmUpRecyclerViewAdapter.notifyDataSetChanged();
                }
            });

            }
        });

        workoutDetailsBinding.pickaPlayListLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFrameLayout,new MusicProviderFragment()).addToBackStack(null).commit();
            }
        });

        workoutDetailsBinding.startWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFrameLayout,new StartTrainingFragment()).addToBackStack(null).commit();
            }
        });

        workoutDetailsBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            }
        });


    }

    @Override
    public void onClicked(int i, ExercisesModel exercisesModel) {
        ExerciseInfoFragment exerciseInfoFragment = new ExerciseInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",exercisesModel.getid());
        bundle.putString("title",exercisesModel.getExercises());
        bundle.putString("image",exercisesModel.getImage());
        bundle.putString("which","exercises");
        exerciseInfoFragment.setArguments(bundle);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_enter,R.anim.fragment_exit);
        fragmentTransaction.add(R.id.homeFrameLayout,exerciseInfoFragment,"exerciseInfo").addToBackStack("exerciseInfo").commit();
    }

    @Override
    public void onClicked(int i, WormUpModel wormUpModel) {
        ExerciseInfoFragment exerciseInfoFragment = new ExerciseInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",wormUpModel.getId());
        bundle.putString("title", wormUpModel.getName());
        bundle.putString("image", wormUpModel.getImage());
        bundle.putString("which","wormUp");
        exerciseInfoFragment.setArguments(bundle);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_enter,R.anim.fragment_exit);
        fragmentTransaction.add(R.id.homeFrameLayout,exerciseInfoFragment,"exerciseInfo").addToBackStack("exerciseInfo").commit();
    }
}