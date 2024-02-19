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
import com.nishiket.homeworkout.adapter.ActivitiesInterestRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentActivitiesInterestBinding;
import com.nishiket.homeworkout.model.ActivitiesInterestModel;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesInterestFragment extends Fragment {

    private FragmentActivitiesInterestBinding activitiesInterestBinding;
    private List<ActivitiesInterestModel> activitiesInterestModelList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activitiesInterestBinding = FragmentActivitiesInterestBinding.inflate(inflater,container,false);
        return activitiesInterestBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActivitiesInterestModel a1 = new ActivitiesInterestModel();
        ActivitiesInterestModel a2 = new ActivitiesInterestModel();
        ActivitiesInterestModel a3 = new ActivitiesInterestModel();
        ActivitiesInterestModel a4 = new ActivitiesInterestModel();
        ActivitiesInterestModel a5 = new ActivitiesInterestModel();

        a1.setActivitie("Keep fit");
        a2.setActivitie("Power training");
        a3.setActivitie("Keep fit");
        a4.setActivitie("Keep fit");
        a5.setActivitie("Yoga");

        a1.setImage(R.drawable.runing);
        a2.setImage(R.drawable.squat);
        a3.setImage(R.drawable.flip);
        a4.setImage(R.drawable.zumba);
        a5.setImage(R.drawable.yoga);

        activitiesInterestModelList.add(a1);
        activitiesInterestModelList.add(a2);
        activitiesInterestModelList.add(a3);
        activitiesInterestModelList.add(a4);
        activitiesInterestModelList.add(a5);


        ActivitiesInterestRecyclerViewAdapter activitiesInterestRecyclerViewAdapter = new ActivitiesInterestRecyclerViewAdapter(getActivity());
        activitiesInterestBinding.activitiesInterestRecylerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        activitiesInterestBinding.activitiesInterestRecylerView.setAdapter(activitiesInterestRecyclerViewAdapter);
        activitiesInterestRecyclerViewAdapter.setActivitiesInterestModelList(activitiesInterestModelList);
        activitiesInterestRecyclerViewAdapter.notifyDataSetChanged();

        activitiesInterestBinding.activitiOfInterestContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new StpesCompleteFragment()).commit();
            }
        });
    }
}