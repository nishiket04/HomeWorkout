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
import com.nishiket.homeworkout.adapter.CardListRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentShowEquipmentBinding;
import com.nishiket.homeworkout.model.EquipmentModel;
import com.nishiket.homeworkout.viewmodel.EquipmentViewModel;
import com.nishiket.homeworkout.viewmodel.SharedViewModel;

import java.util.List;

public class ShowEquipment extends Fragment {

    private FragmentShowEquipmentBinding showEquipmentBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        showEquipmentBinding = FragmentShowEquipmentBinding.inflate(inflater,container,false);
        return showEquipmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EquipmentViewModel equipmentViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(EquipmentViewModel.class);
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        CardListRecyclerViewAdapter cardListRecyclerViewAdapter = new CardListRecyclerViewAdapter(getActivity());
        showEquipmentBinding.recyclerView3.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        showEquipmentBinding.recyclerView3.setAdapter(cardListRecyclerViewAdapter);

        equipmentViewModel.getEquipment(123,"1,2,3,4");
        equipmentViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<EquipmentModel>>() {
            @Override
            public void onChanged(List<EquipmentModel> equipmentModels) {
                cardListRecyclerViewAdapter.setEquipmentModelList(equipmentModels);
                cardListRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        showEquipmentBinding.appCompatButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedViewModel.setLiveData(cardListRecyclerViewAdapter.getSelectedList());
                Log.d("list", "onClick: "+cardListRecyclerViewAdapter.getSelectedList());
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ShowEquipment fragment = (ShowEquipment) fragmentManager.findFragmentByTag("show");
                if(fragment !=null){
                    fragmentManager.popBackStack();
                }
            }
        });
    }
}