package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.MyWorkoutParentRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentMyWorkoutHistoryBinding;
import com.nishiket.homeworkout.model.MyWorkoutChildModel;
import com.nishiket.homeworkout.model.MyWorkoutParentModel;

import java.util.ArrayList;
import java.util.List;

public class MyWorkoutHistoryFragment extends Fragment {

    private FragmentMyWorkoutHistoryBinding myWorkoutHistoryBinding;
    private List<MyWorkoutChildModel> myWorkoutChildModelsList = new ArrayList<>();
    private List<MyWorkoutParentModel> myWorkoutParentModelList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myWorkoutHistoryBinding = FragmentMyWorkoutHistoryBinding.inflate(inflater,container,false);
        return myWorkoutHistoryBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyWorkoutChildModel c1 = new MyWorkoutChildModel();
        MyWorkoutChildModel c2 = new MyWorkoutChildModel();

        c1.setImage(R.drawable.sumo_squat);
        c2.setImage(R.drawable.back);

        c1.setWokrout("Full Body Yoga");
        c2.setWokrout("Glutes & Abs");

        c1.setTime("08:30 - 09:15");
        c2.setTime("08:30 - 09:15");

        MyWorkoutParentModel p1 = new MyWorkoutParentModel();
        MyWorkoutParentModel p2 = new MyWorkoutParentModel();

        p1.setDate("November, 15 2021");
        p2.setDate("November, 19 2021");

        p1.setWorkout("1 workout, 32 minutes");
        p2.setWorkout("2 workout, 32 minutes");

        myWorkoutChildModelsList.add(c1);
        myWorkoutChildModelsList.add(c2);

        p1.setMyWorkoutChildModelsList(myWorkoutChildModelsList);
        p2.setMyWorkoutChildModelsList(myWorkoutChildModelsList);

        myWorkoutParentModelList.add(p1);
        myWorkoutParentModelList.add(p2);

        MyWorkoutParentRecyclerViewAdapter myWorkoutParentRecyclerViewAdapter = new MyWorkoutParentRecyclerViewAdapter(getActivity());
        myWorkoutHistoryBinding.myWorkoutHistoryParentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        myWorkoutHistoryBinding.myWorkoutHistoryParentRecyclerView.setAdapter(myWorkoutParentRecyclerViewAdapter);
        myWorkoutParentRecyclerViewAdapter.setMyWorkoutParentModelList(myWorkoutParentModelList);
        myWorkoutParentRecyclerViewAdapter.notifyDataSetChanged();
    }
}