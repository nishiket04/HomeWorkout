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
import com.nishiket.homeworkout.adapter.CardListRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentGenderBinding;
import com.nishiket.homeworkout.model.CardList;

import java.util.ArrayList;
import java.util.List;

public class GenderFragment extends Fragment {
    private List<CardList> cardListList = new ArrayList<>();
    private FragmentGenderBinding genderBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        genderBinding = FragmentGenderBinding.inflate(inflater,container,false);
        return genderBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CardList c1 = new CardList();
        c1.setImage(R.drawable.man);
        c1.setGender("Man");
        CardList c2 = new CardList();
        c2.setImage(R.drawable.woman);
        c2.setGender("Woman");
        CardList c3 = new CardList();
        c3.setImage(R.drawable.other);
        c3.setGender("Other");
        cardListList.add(c1);
        cardListList.add(c2);
        cardListList.add(c3);

        CardListRecyclerViewAdapter cardListRecyclerViewAdapter = new CardListRecyclerViewAdapter(getActivity());
        genderBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        genderBinding.recyclerView.setAdapter(cardListRecyclerViewAdapter);
        cardListRecyclerViewAdapter.setActiveComplainList(cardListList);

        genderBinding.genderContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new GoalFragment()).commit();
            }
        });

    }
}