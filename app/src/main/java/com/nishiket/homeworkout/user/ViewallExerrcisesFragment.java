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

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.ExercisesRecyclerViewAdapter;
import com.nishiket.homeworkout.adapter.ViewallExercisesRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentViewallExerrcisesBinding;
import com.nishiket.homeworkout.model.ExercisesModel;
import com.nishiket.homeworkout.viewmodel.ExerciseViewModel;

import java.util.List;

public class ViewallExerrcisesFragment extends Fragment implements ViewallExercisesRecyclerViewAdapter.onClickedItem {
    private FragmentViewallExerrcisesBinding viewallExerrcisesBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewallExerrcisesBinding = FragmentViewallExerrcisesBinding.inflate(inflater,container,false);
        return viewallExerrcisesBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ExerciseViewModel exerciseViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(ExerciseViewModel.class);

        exerciseViewModel.getExercises(123);
        exerciseViewModel.getExercisesListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<ExercisesModel>>() {
            @Override
            public void onChanged(List<ExercisesModel> exercisesModelList) {
                ViewallExercisesRecyclerViewAdapter viewallExercisesRecyclerViewAdapter = new ViewallExercisesRecyclerViewAdapter(getActivity());
                viewallExerrcisesBinding.viewallExercisesRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                viewallExerrcisesBinding.viewallExercisesRecyclerView1.setAdapter(viewallExercisesRecyclerViewAdapter);
                viewallExerrcisesBinding.viewallExercisesRecyclerView1.setNestedScrollingEnabled(false);
                viewallExercisesRecyclerViewAdapter.setOnClickedItem(ViewallExerrcisesFragment.this);
                viewallExercisesRecyclerViewAdapter.setViewallExercisesModelList(exercisesModelList);
                viewallExercisesRecyclerViewAdapter.notifyDataSetChanged();
            }
        });


        viewallExerrcisesBinding.backToHomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.homeFrameLayout,new HomeFragment()).commit();
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