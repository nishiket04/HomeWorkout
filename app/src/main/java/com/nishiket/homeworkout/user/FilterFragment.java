package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.FilterDurationRecyclerViewAdapter;
import com.nishiket.homeworkout.adapter.FilterEquipmentRecyclerViewAdapter;
import com.nishiket.homeworkout.adapter.FilterLevelRecyclerViewAdapter;
import com.nishiket.homeworkout.adapter.FilterPriceRecyclerViewAdapter;
import com.nishiket.homeworkout.adapter.FilterRecyclerViewAdapter;
import com.nishiket.homeworkout.model.FilterModel;

import java.util.ArrayList;
import java.util.List;

public class FilterFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }
    private RecyclerView categoryFilterRecyclerView,priceFilterRecyclerView,levelFilterRecyclerView,durationFilterRecyclerView,equipmentFilterRecyclerView;
    private List<FilterModel> filterModelList = new ArrayList<>();
    private List<FilterModel> priceFilterModelList = new ArrayList<>();
    private List<FilterModel> levelFilterModelList = new ArrayList<>();
    private List<FilterModel> durationFilterModelList = new ArrayList<>();
    private List<FilterModel> equipmentFilterModelList = new ArrayList<>();
    private ImageView backToTraining;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assignId(view);

        FilterModel f1 = new FilterModel();
        FilterModel f2 = new FilterModel();
        FilterModel f3 = new FilterModel();
        FilterModel f4 = new FilterModel();
        FilterModel f5 = new FilterModel();
        FilterModel f6 = new FilterModel();
        FilterModel f7 = new FilterModel();
        FilterModel f8 = new FilterModel();
        FilterModel f9 = new FilterModel();
        FilterModel f10 = new FilterModel();
        FilterModel f11 = new FilterModel();
        FilterModel f12 = new FilterModel();
        FilterModel f13 = new FilterModel();
        FilterModel f14 = new FilterModel();
        FilterModel f15 = new FilterModel();
        FilterModel f16 = new FilterModel();
        FilterModel f17 = new FilterModel();
        FilterModel f18 = new FilterModel();

        f1.setFilter("Beginner");
        f2.setFilter("Legs");
        f3.setFilter("Arms");
        f4.setFilter("Yoga");
        f5.setFilter("Boxing");
        f6.setFilter("Personal");
        f7.setFilter("Free");
        f8.setFilter("Premium");
        f9.setFilter("Beginner");
        f10.setFilter("Medium");
        f11.setFilter("Advanced");
        f12.setFilter("10-20 min");
        f13.setFilter("20-30 min");
        f14.setFilter("30-40 min");
        f15.setFilter("Kettlebell");
        f16.setFilter("Dumbbells");
        f17.setFilter("Yoga mat");
        f18.setFilter("Running");

        filterModelList.add(f1);
        filterModelList.add(f2);
        filterModelList.add(f3);
        filterModelList.add(f4);
        filterModelList.add(f5);
        filterModelList.add(f6);
        filterModelList.add(f18);

        priceFilterModelList.add(f7);
        priceFilterModelList.add(f8);

        levelFilterModelList.add(f9);
        levelFilterModelList.add(f10);
        levelFilterModelList.add(f11);

        durationFilterModelList.add(f12);
        durationFilterModelList.add(f13);
        durationFilterModelList.add(f14);

        equipmentFilterModelList.add(f15);
        equipmentFilterModelList.add(f16);
        equipmentFilterModelList.add(f17);

        FilterRecyclerViewAdapter filterRecyclerViewAdapter = new FilterRecyclerViewAdapter(getActivity());
        FilterPriceRecyclerViewAdapter filterPriceRecyclerViewAdapter = new FilterPriceRecyclerViewAdapter(getActivity());
        FilterLevelRecyclerViewAdapter filterLevelRecyclerViewAdapter = new FilterLevelRecyclerViewAdapter(getActivity());
        FilterDurationRecyclerViewAdapter filterDurationRecyclerViewAdapter = new FilterDurationRecyclerViewAdapter(getActivity());
        FilterEquipmentRecyclerViewAdapter filterEquipmentRecyclerViewAdapter = new FilterEquipmentRecyclerViewAdapter(getActivity());

        categoryFilterRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        priceFilterRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        levelFilterRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        durationFilterRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        equipmentFilterRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

        categoryFilterRecyclerView.setNestedScrollingEnabled(false);
        priceFilterRecyclerView.setNestedScrollingEnabled(false);
        levelFilterRecyclerView.setNestedScrollingEnabled(false);
        durationFilterRecyclerView.setNestedScrollingEnabled(false);
        equipmentFilterRecyclerView.setNestedScrollingEnabled(false);

        categoryFilterRecyclerView.setAdapter(filterRecyclerViewAdapter);
        priceFilterRecyclerView.setAdapter(filterPriceRecyclerViewAdapter);
        levelFilterRecyclerView.setAdapter(filterLevelRecyclerViewAdapter);
        durationFilterRecyclerView.setAdapter(filterDurationRecyclerViewAdapter);
        equipmentFilterRecyclerView.setAdapter(filterEquipmentRecyclerViewAdapter);

        filterRecyclerViewAdapter.setFilterModelList(filterModelList);
        filterPriceRecyclerViewAdapter.setFilterModelList(priceFilterModelList);
        filterLevelRecyclerViewAdapter.setFilterModelList(levelFilterModelList);
        filterDurationRecyclerViewAdapter.setFilterModelList(durationFilterModelList);
        filterEquipmentRecyclerViewAdapter.setFilterModelList(equipmentFilterModelList);

        filterRecyclerViewAdapter.notifyDataSetChanged();
        filterPriceRecyclerViewAdapter.notifyDataSetChanged();
        filterLevelRecyclerViewAdapter.notifyDataSetChanged();
        filterDurationRecyclerViewAdapter.notifyDataSetChanged();
        filterEquipmentRecyclerViewAdapter.notifyDataSetChanged();

        backToTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            }
        });
    }

    private void assignId(View view) {
        categoryFilterRecyclerView = view.findViewById(R.id.categoryFilterRecyclerView);
        priceFilterRecyclerView = view.findViewById(R.id.priceFilterRecyclerView);
        levelFilterRecyclerView = view.findViewById(R.id.levelFilterRecyclerView);
        durationFilterRecyclerView = view.findViewById(R.id.durationFilterRecyclerView);
        equipmentFilterRecyclerView = view.findViewById(R.id.equipmentFilterRecyclerView);
        backToTraining = view.findViewById(R.id.backToTraining);
    }
}