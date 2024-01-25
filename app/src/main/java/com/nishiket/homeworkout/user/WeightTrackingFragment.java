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
import com.nishiket.homeworkout.adapter.WeightTrackingRecyclerViewAdapter;
import com.nishiket.homeworkout.model.WeightTrackingModel;

import java.util.ArrayList;
import java.util.List;

public class WeightTrackingFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weight_tracking, container, false);
    }
    private RecyclerView weightTrackingRecyclerView;
    private List<WeightTrackingModel> weightTrackingModelList = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assignId(view);

        WeightTrackingModel w1 = new WeightTrackingModel();
        WeightTrackingModel w2 = new WeightTrackingModel();
        WeightTrackingModel w3 = new WeightTrackingModel();

        w1.setDate("Today");
        w2.setDate("Today");
        w3.setDate("Today");

        w1.setTime("7:45");
        w2.setTime("7:65");
        w3.setTime("7:75");

        w1.setWeight("55.6 Kg");
        w2.setWeight("59.6 Kg");
        w3.setWeight("53.6 Kg");

        w1.setWeightInfo("-0.1 Kg");
        w2.setWeightInfo("-1.1 Kg");
        w3.setWeightInfo("-3.1 Kg");

        w1.setImage(R.drawable.arrow_down_green);
        w2.setImage(R.drawable.arrow_down_red);
        w3.setImage(R.drawable.arrow_up_green);


        weightTrackingModelList.add(w1);
        weightTrackingModelList.add(w2);
        weightTrackingModelList.add(w3);

        weightTrackingRecyclerView.setNestedScrollingEnabled(false);
        WeightTrackingRecyclerViewAdapter weightTrackingRecyclerViewAdapter = new WeightTrackingRecyclerViewAdapter(getActivity());
        weightTrackingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        weightTrackingRecyclerView.setAdapter(weightTrackingRecyclerViewAdapter);
        weightTrackingRecyclerViewAdapter.setWeightTrackingModelList(weightTrackingModelList);
        weightTrackingRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void assignId(View view) {
        weightTrackingRecyclerView = view.findViewById(R.id.weightTrackingRecyclerView);
    }
}