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
import com.nishiket.homeworkout.adapter.WarmUpEditRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentWarmUpsEditBinding;
import com.nishiket.homeworkout.model.WarmUpEditModel;

import java.util.ArrayList;
import java.util.List;

public class WarmUpsEditFragment extends Fragment {
    private FragmentWarmUpsEditBinding warmUpsEditBinding;
    private List<WarmUpEditModel> warmUpEditModelList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        warmUpsEditBinding = FragmentWarmUpsEditBinding.inflate(inflater,container,false);
        return warmUpsEditBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        WarmUpEditModel w1 = new WarmUpEditModel();
        WarmUpEditModel w2 = new WarmUpEditModel();
        WarmUpEditModel w3 = new WarmUpEditModel();
        WarmUpEditModel w4 = new WarmUpEditModel();
        WarmUpEditModel w5 = new WarmUpEditModel();
        WarmUpEditModel w6 = new WarmUpEditModel();
        WarmUpEditModel w7 = new WarmUpEditModel();
        WarmUpEditModel w8 = new WarmUpEditModel();

        w1.setImage(R.drawable.back);
        w2.setImage(R.drawable.sumo_squat);
        w3.setImage(R.drawable.plank);
        w4.setImage(R.drawable.back);
        w5.setImage(R.drawable.sumo_squat);
        w6.setImage(R.drawable.plank);
        w7.setImage(R.drawable.back);
        w8.setImage(R.drawable.sumo_squat);


        w1.setWorkout("Low Lunge");
        w2.setWorkout("Extended Side Angle");
        w3.setWorkout("Chair Pose");
        w4.setWorkout("Low Lunge");
        w5.setWorkout("Extended Side Angle");
        w6.setWorkout("Chair Pose");
        w7.setWorkout("Low Lunge");
        w8.setWorkout("Extended Side Angle");

        w1.setTime("0:30");
        w2.setTime("0:30");
        w3.setTime("0:30");
        w4.setTime("0:30");
        w5.setTime("0:30");
        w6.setTime("0:30");
        w7.setTime("0:30");
        w8.setTime("0:30");

        warmUpEditModelList.add(w1);
        warmUpEditModelList.add(w2);
        warmUpEditModelList.add(w3);
        warmUpEditModelList.add(w4);
        warmUpEditModelList.add(w5);
        warmUpEditModelList.add(w6);
        warmUpEditModelList.add(w7);
        warmUpEditModelList.add(w8);

        WarmUpEditRecyclerViewAdapter warmUpEditRecyclerViewAdapter = new WarmUpEditRecyclerViewAdapter(getActivity());
        warmUpsEditBinding.warmupEditRecylerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        warmUpsEditBinding.warmupEditRecylerView.setAdapter(warmUpEditRecyclerViewAdapter);
        warmUpsEditBinding.warmupEditRecylerView.setNestedScrollingEnabled(false);
        warmUpEditRecyclerViewAdapter.setWarmUpEditModelList(warmUpEditModelList);
        warmUpEditRecyclerViewAdapter.notifyDataSetChanged();

        warmUpsEditBinding.backToWorkoutImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFrameLayout,new CustomWorkoutDetailsFragment()).commit();
            }
        });
    }
}