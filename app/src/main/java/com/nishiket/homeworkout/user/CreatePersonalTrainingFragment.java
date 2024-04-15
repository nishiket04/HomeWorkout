package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.TraingListRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentCreatePersonalTrainingBinding;
import com.nishiket.homeworkout.model.TraingListModel;

import java.util.ArrayList;
import java.util.List;

public class CreatePersonalTrainingFragment extends Fragment {

    private FragmentCreatePersonalTrainingBinding createPersonalTrainingBinding;
    private List<TraingListModel> traingListModelList =  new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createPersonalTrainingBinding = FragmentCreatePersonalTrainingBinding.inflate(inflater,container,false);
        return createPersonalTrainingBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TraingListModel t1 = new TraingListModel();
        TraingListModel t3 = new TraingListModel();
        TraingListModel t4 = new TraingListModel();

        t1.setMainTxt("Beginner");
        t3.setMainTxt("Medium");
        t4.setMainTxt("Advanced");

        t1.setSubTxt("I train 1-2 times a week");
        t3.setSubTxt("I train 3-5 times a week");
        t4.setSubTxt("I train more than 5 times a week");

        traingListModelList.add(t1);
        traingListModelList.add(t3);
        traingListModelList.add(t4);


        TraingListRecyclerViewAdapter traingListRecyclerViewAdapter = new TraingListRecyclerViewAdapter(getActivity());
        createPersonalTrainingBinding.levelRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        createPersonalTrainingBinding.levelRecyclerView.setAdapter(traingListRecyclerViewAdapter);
        traingListRecyclerViewAdapter.setTraingListModelList(traingListModelList);
        traingListRecyclerViewAdapter.notifyDataSetChanged();

    }

}