package com.nishiket.homeworkout.SignInUp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.TraingListRecyclerViewAdapter;
import com.nishiket.homeworkout.model.TraingListModel;

import java.util.ArrayList;
import java.util.List;

public class TrainingLevelFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training_level, container, false);
    }
    private RecyclerView traingLevelRecyclerView;
    private List<TraingListModel> traingListModelList =  new ArrayList<>();
    private AppCompatButton trainingLevelContinueBtn;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        traingLevelRecyclerView = view.findViewById(R.id.traingLevelRecylerView);
        trainingLevelContinueBtn = view.findViewById(R.id.trainingLevelContinueBtn);

        TraingListModel t1 = new TraingListModel();
        TraingListModel t2 = new TraingListModel();
        TraingListModel t3 = new TraingListModel();
        TraingListModel t4 = new TraingListModel();

        t1.setMainTxt("Medium");
        t2.setMainTxt("Irregular training");
        t3.setMainTxt("Medium");
        t4.setMainTxt("Advanced");

        t1.setSubTxt("I train 1-2 times a week");
        t2.setSubTxt("I train 2-3 times a week");
        t3.setSubTxt("I train 3-5 times a week");
        t4.setSubTxt("I train more than 5 times a week");

        traingListModelList.add(t1);
        traingListModelList.add(t2);
        traingListModelList.add(t3);
        traingListModelList.add(t4);


        TraingListRecyclerViewAdapter traingListRecyclerViewAdapter = new TraingListRecyclerViewAdapter(getActivity());
        traingLevelRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        traingLevelRecyclerView.setAdapter(traingListRecyclerViewAdapter);
        traingListRecyclerViewAdapter.setTraingListModelList(traingListModelList);
        traingListRecyclerViewAdapter.notifyDataSetChanged();

        trainingLevelContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new ActivitiesInterestFragment()).commit();
            }
        });

    }
}