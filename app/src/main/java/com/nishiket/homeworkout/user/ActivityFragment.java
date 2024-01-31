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
import com.nishiket.homeworkout.adapter.ActivityDateRecyclerViewAdapter;
import com.nishiket.homeworkout.model.ActivityDateModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity, container, false);
    }

    private RecyclerView dateActivityRecyclerView;
    private List<ActivityDateModel> activityDateModelList = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assignId(view);

        ActivityDateModel a1 = new ActivityDateModel();
        ActivityDateModel a2 = new ActivityDateModel();
        ActivityDateModel a3 = new ActivityDateModel();
        ActivityDateModel a4 = new ActivityDateModel();
        ActivityDateModel a6 = new ActivityDateModel();
        ActivityDateModel a5 = new ActivityDateModel();
        ActivityDateModel a7 = new ActivityDateModel();

        a1.setDate("12");
        a2.setDate("13");
        a3.setDate("14");
        a4.setDate("15");
        a5.setDate("16");
        a6.setDate("17");
        a7.setDate("18");

        a1.setMonth("Nov");
        a2.setMonth("Nov");
        a3.setMonth("Nov");
        a4.setMonth("Nov");
        a5.setMonth("Nov");
        a6.setMonth("Nov");
        a7.setMonth("Nov");

        activityDateModelList.add(a1);
        activityDateModelList.add(a2);
        activityDateModelList.add(a3);
        activityDateModelList.add(a4);
        activityDateModelList.add(a5);
        activityDateModelList.add(a6);
        activityDateModelList.add(a7);

        ActivityDateRecyclerViewAdapter dateRecyclerViewAdapter = new ActivityDateRecyclerViewAdapter(getActivity());
        dateActivityRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        dateActivityRecyclerView.setAdapter(dateRecyclerViewAdapter);
        dateRecyclerViewAdapter.setActivityDateModelsLst(activityDateModelList);
        dateRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void assignId(View view) {
        dateActivityRecyclerView = view.findViewById(R.id.activityDateRecyclerView);
    }
}