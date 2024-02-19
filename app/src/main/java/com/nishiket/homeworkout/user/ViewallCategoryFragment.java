package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.ViewallCategoryRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentViewallCategoryBinding;
import com.nishiket.homeworkout.model.ViewallCategoryModel;

import java.util.ArrayList;
import java.util.List;

public class ViewallCategoryFragment extends Fragment {
    private List<ViewallCategoryModel> viewallCategoryModelList = new ArrayList<>();
    private FragmentViewallCategoryBinding viewallCategoryBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewallCategoryBinding = FragmentViewallCategoryBinding.inflate(inflater,container,false);
        return viewallCategoryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ViewallCategoryModel v1 = new ViewallCategoryModel();
        ViewallCategoryModel v2 = new ViewallCategoryModel();
        ViewallCategoryModel v3 = new ViewallCategoryModel();
        ViewallCategoryModel v4 = new ViewallCategoryModel();
        ViewallCategoryModel v5 = new ViewallCategoryModel();
        ViewallCategoryModel v6 = new ViewallCategoryModel();
        ViewallCategoryModel v7 = new ViewallCategoryModel();

        v1.setImage(R.drawable.book);
        v2.setImage(R.drawable.yoga);
        v3.setImage(R.drawable.flip);
        v4.setImage(R.drawable.boxing);
        v5.setImage(R.drawable.runing);
        v6.setImage(R.drawable.bicep);
        v7.setImage(R.drawable.squat);

        v1.setCategory("Personal Trainings");
        v2.setCategory("Yoga");
        v3.setCategory("Stretch");
        v4.setCategory("Boxing");
        v5.setCategory("Running");
        v6.setCategory("Upper Body");
        v7.setCategory("Gym");

        v1.setWorkouts("2 workouts");
        v2.setWorkouts("12 workouts");
        v3.setWorkouts("3 workouts");
        v4.setWorkouts("13 workouts");
        v5.setWorkouts("6 workouts");
        v6.setWorkouts("20 workouts");
        v7.setWorkouts("9 workouts");

        viewallCategoryModelList.add(v1);
        viewallCategoryModelList.add(v2);
        viewallCategoryModelList.add(v3);
        viewallCategoryModelList.add(v4);
        viewallCategoryModelList.add(v5);
        viewallCategoryModelList.add(v6);
        viewallCategoryModelList.add(v7);

        ViewallCategoryRecyclerViewAdapter viewallCategoryRecyclerViewAdapter = new ViewallCategoryRecyclerViewAdapter(getActivity());
        viewallCategoryBinding.viewallCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        viewallCategoryBinding.viewallCategoryRecyclerView.setAdapter(viewallCategoryRecyclerViewAdapter);
        viewallCategoryRecyclerViewAdapter.setViewallCategoryModelList(viewallCategoryModelList);
        viewallCategoryRecyclerViewAdapter.notifyDataSetChanged();

        viewallCategoryBinding.viewallCategoryRecyclerView.setNestedScrollingEnabled(false);

        viewallCategoryBinding.backToHomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFrameLayout,new HomeFragment()).commit();
            }
        });
    }
}