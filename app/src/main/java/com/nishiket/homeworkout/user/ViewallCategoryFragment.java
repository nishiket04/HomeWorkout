package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.ViewallCategoryRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentViewallCategoryBinding;
import com.nishiket.homeworkout.model.CategoryModel;
import com.nishiket.homeworkout.viewmodel.CategoryViewModel;

import java.util.List;

public class ViewallCategoryFragment extends Fragment {
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
        CategoryViewModel categoryViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(CategoryViewModel.class);
        categoryViewModel.getData(123);
        categoryViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<CategoryModel>>() {
            @Override
            public void onChanged(List<CategoryModel> categoryModelList) {
                ViewallCategoryRecyclerViewAdapter viewallCategoryRecyclerViewAdapter = new ViewallCategoryRecyclerViewAdapter(getActivity());
                viewallCategoryBinding.viewallCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                viewallCategoryBinding.viewallCategoryRecyclerView.setAdapter(viewallCategoryRecyclerViewAdapter);
                viewallCategoryRecyclerViewAdapter.setViewallCategoryModelList(categoryModelList);
                Log.d("data", "onChanged: "+categoryModelList.size());
                viewallCategoryRecyclerViewAdapter.notifyDataSetChanged();
                viewallCategoryBinding.scroll.stopNestedScroll();
//                viewallCategoryBinding.viewallCategoryRecyclerView.setNestedScrollingEnabled(false);
            }
        });

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