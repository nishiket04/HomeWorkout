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

import com.bumptech.glide.Glide;
import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.WorkoutDetailsEquipmentRecylerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentExerciseInfoBinding;
import com.nishiket.homeworkout.model.EquipmentModel;
import com.nishiket.homeworkout.model.ExercisesInfoModel;
import com.nishiket.homeworkout.model.WormUpInfoModel;
import com.nishiket.homeworkout.viewmodel.EquipmentViewModel;
import com.nishiket.homeworkout.viewmodel.ExerciesInfoViewModel;
import com.nishiket.homeworkout.viewmodel.WormUpInfoViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExerciseInfoFragment extends Fragment {
    private FragmentExerciseInfoBinding exerciseInfoBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_exercise_info, container, false);
        exerciseInfoBinding = FragmentExerciseInfoBinding.inflate(inflater,container,false);
        return exerciseInfoBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ExerciesInfoViewModel exerciesInfoViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(ExerciesInfoViewModel.class);
        EquipmentViewModel equipmentViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(EquipmentViewModel.class);
        WormUpInfoViewModel wormUpInfoViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(WormUpInfoViewModel.class);
        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        exerciseInfoBinding.textView45.setText(bundle.getString("title"));
        Glide.with(this).load(bundle.getString("image")).into(exerciseInfoBinding.image);
        String which = bundle.getString("which");

        if(which.equals("exercises")) {
            WorkoutDetailsEquipmentRecylerViewAdapter workoutDetailsEquipmentRecylerViewAdapter = new WorkoutDetailsEquipmentRecylerViewAdapter(getActivity());
            exerciseInfoBinding.recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            exerciseInfoBinding.recyclerView2.setAdapter(workoutDetailsEquipmentRecylerViewAdapter);

            exerciesInfoViewModel.getExerciesInfo(123, id);
            exerciesInfoViewModel.getExercisesInfoModelMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ExercisesInfoModel>() {
                @Override
                public void onChanged(ExercisesInfoModel exercisesInfoModel) {
                    exerciseInfoBinding.textView48.setText(exercisesInfoModel.getDetail());
                    exerciseInfoBinding.textView51.setText(exercisesInfoModel.getTechnique());
                    Log.d("info", "onChanged: " + exercisesInfoModel.getEquipment().replace("[", "").replace("]", ""));
                    equipmentViewModel.getEquipment(123, exercisesInfoModel.getEquipment().replace("[", "").replace("]", ""));
                    equipmentViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<EquipmentModel>>() {
                        @Override
                        public void onChanged(List<EquipmentModel> equipmentModels) {
                            Log.d("info", "onChanged: " + equipmentModels.get(0).getName());
                            workoutDetailsEquipmentRecylerViewAdapter.setWorkoutDetailsEquipmentModelList(equipmentModels);
                            workoutDetailsEquipmentRecylerViewAdapter.notifyDataSetChanged();
                        }
                    });
                }
            });
        } else if (which.equals("wormUp")) {
            wormUpInfoViewModel.getWormUpInfo(123,id);
            wormUpInfoViewModel.getWormUpInfoMutableLiveData().observe(getViewLifecycleOwner(), new Observer<WormUpInfoModel>() {
                @Override
                public void onChanged(WormUpInfoModel wormUpInfoModel) {
                    EquipmentModel equipmentModels = new EquipmentModel();
                    equipmentModels.setName("No Equipment");
                    List<EquipmentModel> equipmentModels1 = new ArrayList<>();
                    equipmentModels1.add(equipmentModels);
                    exerciseInfoBinding.textView48.setText(wormUpInfoModel.getDetail());
                    exerciseInfoBinding.textView51.setText(wormUpInfoModel.getTechnique());
                    WorkoutDetailsEquipmentRecylerViewAdapter workoutDetailsEquipmentRecylerViewAdapter = new WorkoutDetailsEquipmentRecylerViewAdapter(getActivity());
                    exerciseInfoBinding.recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    exerciseInfoBinding.recyclerView2.setAdapter(workoutDetailsEquipmentRecylerViewAdapter);
                    workoutDetailsEquipmentRecylerViewAdapter.setWorkoutDetailsEquipmentModelList(equipmentModels1);
                    workoutDetailsEquipmentRecylerViewAdapter.notifyDataSetChanged();
                }
            });
        }

        exerciseInfoBinding.closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_enter,R.anim.fragment_exit);
                ExerciseInfoFragment fragment = (ExerciseInfoFragment) fragmentManager.findFragmentByTag("exerciseInfo");
                if(fragment !=null){
                    fragmentTransaction.remove(fragment);
                    fragmentTransaction.commit();
                    fragmentManager.popBackStack();
                }
            }
        });
    }
}