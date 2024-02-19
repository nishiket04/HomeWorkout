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

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.ViewallExercisesRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentViewallExerrcisesBinding;
import com.nishiket.homeworkout.model.ViewallExercisesModel;

import java.util.ArrayList;
import java.util.List;

public class ViewallExerrcisesFragment extends Fragment {
    private FragmentViewallExerrcisesBinding viewallExerrcisesBinding;
    private List<ViewallExercisesModel> viewallExercisesModelList = new ArrayList<>();
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


        ViewallExercisesModel e1 = new ViewallExercisesModel();
        ViewallExercisesModel e2 = new ViewallExercisesModel();
        ViewallExercisesModel e3 = new ViewallExercisesModel();
        ViewallExercisesModel e4 = new ViewallExercisesModel();
        ViewallExercisesModel e5 = new ViewallExercisesModel();
        ViewallExercisesModel e6 = new ViewallExercisesModel();
        ViewallExercisesModel e7 = new ViewallExercisesModel();

        e1.setTitle("Cobra Stretch");
        e2.setTitle("Plank Ups");
        e3.setTitle("Plank Reaches");
        e4.setTitle("Skater Squat Right");
        e5.setTitle("Squat jump");
        e6.setTitle("Lung Jumps Atlernated");
        e7.setTitle("Cobra Stretch");

        e1.setImage(R.drawable.back);
        e2.setImage(R.drawable.plank);
        e3.setImage(R.drawable.lag);
        e4.setImage(R.drawable.sumo_squat);
        e5.setImage(R.drawable.back);
        e6.setImage(R.drawable.back);
        e7.setImage(R.drawable.plank);

        e1.setTime("00:30");
        e2.setTime("00:30");
        e3.setTime("00:30");
        e4.setTime("00:30");
        e5.setTime("00:30");
        e6.setTime("00:30");
        e7.setTime("00:30");

        viewallExercisesModelList.add(e1);
        viewallExercisesModelList.add(e2);
        viewallExercisesModelList.add(e3);
        viewallExercisesModelList.add(e4);
        viewallExercisesModelList.add(e5);
        viewallExercisesModelList.add(e6);
        viewallExercisesModelList.add(e7);

        ViewallExercisesRecyclerViewAdapter viewallExercisesRecyclerViewAdapter = new ViewallExercisesRecyclerViewAdapter(getActivity());
        viewallExerrcisesBinding.viewallExercisesRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        viewallExerrcisesBinding.viewallExercisesRecyclerView1.setAdapter(viewallExercisesRecyclerViewAdapter);
        viewallExerrcisesBinding.viewallExercisesRecyclerView1.setNestedScrollingEnabled(false);
        viewallExercisesRecyclerViewAdapter.setViewallExercisesModelList(viewallExercisesModelList);
        viewallExercisesRecyclerViewAdapter.notifyDataSetChanged();

        viewallExerrcisesBinding.backToHomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.homeFrameLayout,new HomeFragment()).commit();
            }
        });

    }
}