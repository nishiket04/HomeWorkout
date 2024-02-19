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
import com.nishiket.homeworkout.databinding.FragmentWeightTrackingBinding;
import com.nishiket.homeworkout.model.WeightTrackingModel;

import java.util.ArrayList;
import java.util.List;

public class WeightTrackingFragment extends Fragment {
    private List<WeightTrackingModel> weightTrackingModelList = new ArrayList<>();
    private FragmentWeightTrackingBinding weightTrackingBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        weightTrackingBinding = FragmentWeightTrackingBinding.inflate(inflater,container,false);
        return weightTrackingBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        weightTrackingBinding.weightTrackingRecyclerView.setNestedScrollingEnabled(false);
        WeightTrackingRecyclerViewAdapter weightTrackingRecyclerViewAdapter = new WeightTrackingRecyclerViewAdapter(getActivity());
        weightTrackingBinding.weightTrackingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        weightTrackingBinding.weightTrackingRecyclerView.setAdapter(weightTrackingRecyclerViewAdapter);
        weightTrackingRecyclerViewAdapter.setWeightTrackingModelList(weightTrackingModelList);
        weightTrackingRecyclerViewAdapter.notifyDataSetChanged();
    }
}