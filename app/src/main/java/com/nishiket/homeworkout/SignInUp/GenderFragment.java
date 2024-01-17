package com.nishiket.homeworkout.SignInUp;

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
import com.nishiket.homeworkout.adapter.CardListRecyclerViewAdapter;
import com.nishiket.homeworkout.model.CardList;

import java.util.ArrayList;
import java.util.List;

public class GenderFragment extends Fragment {
    private List<CardList> cardListList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gender, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CardList c1 = new CardList();
        c1.setImage(R.drawable.man_emoji);
        c1.setGender("Man");
        CardList c2 = new CardList();
        c2.setImage(R.drawable.woman_emoji);
        c2.setGender("Woman");
        CardList c3 = new CardList();
        c3.setImage(R.drawable.other_emoji);
        c3.setGender("Other");
        cardListList.add(c1);
        cardListList.add(c2);
        cardListList.add(c3);

        CardListRecyclerViewAdapter cardListRecyclerViewAdapter = new CardListRecyclerViewAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(cardListRecyclerViewAdapter);
        cardListRecyclerViewAdapter.setActiveComplainList(cardListList);

    }
}