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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.MainGoalRecyclerViewAdapter;
import com.nishiket.homeworkout.model.CardList;

import java.util.ArrayList;
import java.util.List;

public class GoalFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goal, container, false);
    }
    private RecyclerView recyclerView;
    private List<CardList> cardListList = new ArrayList<>();
    private AppCompatButton mainGoalContinueBtn;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.goalRecycler);
        mainGoalContinueBtn = view.findViewById(R.id.mainGoalContinuBtn);
            CardList c1 = new CardList();
            CardList c2 = new CardList();
            CardList c3 = new CardList();
            CardList c4 = new CardList();

            c1.setGender("Lose weight");
            c2.setGender("Keep fit");
            c3.setGender("Get stronger");
            c4.setGender("Gain muscle mass");

            c1.setImage(R.drawable.weight_scale);
            c2.setImage(R.drawable.clover);
            c3.setImage(R.drawable.bicep);
            c4.setImage(R.drawable.dumbbell);

            cardListList.add(c1);
            cardListList.add(c2);
            cardListList.add(c3);
            cardListList.add(c4);



        MainGoalRecyclerViewAdapter mainGoalRecyclerViewAdapter = new MainGoalRecyclerViewAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(mainGoalRecyclerViewAdapter);
        mainGoalRecyclerViewAdapter.setCardListList(cardListList);
        mainGoalRecyclerViewAdapter.notifyDataSetChanged();


        mainGoalContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardList c = mainGoalRecyclerViewAdapter.getSelectedData();
                Log.d("c", "onClick: " + c.getGender());
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new BirthDateFragment()).commit();
            }
        });


    }
}