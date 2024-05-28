package com.nishiket.homeworkout.user;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.WeightTrackingRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentWeightTrackingBinding;
import com.nishiket.homeworkout.model.UserDetailModel;
import com.nishiket.homeworkout.model.WeightTrackingModel;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.sqlite.Helper;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;
import com.nishiket.homeworkout.viewmodel.UserDetailViewModel;

import java.util.ArrayList;
import java.util.List;

public class WeightTrackingFragment extends Fragment {
    private List<WeightTrackingModel> weightTrackingModelList = new ArrayList<>();
    private FragmentWeightTrackingBinding weightTrackingBinding;
    private String gole;
    private float weight;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        weightTrackingBinding = FragmentWeightTrackingBinding.inflate(inflater,container,false);
        return weightTrackingBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AuthViewModel authViewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        UserDetailViewModel userDetailViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(UserDetailViewModel.class);
        // Add your API key and email
        int apiKey = 123;
        String email = authViewModel.getCurrentUser().getEmail();

        userDetailViewModel.getData(apiKey,email);

        Helper helper = new Helper(getActivity());
        userDetailViewModel.getUserDetailModelMutableLiveData().observe(getViewLifecycleOwner(), new Observer<UserDetailModel>() {
            @Override
            public void onChanged(UserDetailModel userDetailModel) {
                gole = userDetailModel.getGoale();
                weight = userDetailModel.getWeightKg();
                if(gole.equals("Lose weight")){
                    ContentValues values = new ContentValues();
                    values.put("weight",-1.2f);
                    values.put("image","green");
                    weight = weight-1.2f;
                    helper.getWritableDatabase().insert("weight_tracking",null,values);
                }else{

                }
                Cursor cursor = helper.getReadableDatabase().query("weight_tracking",null,null,null,null,null,null);
                while (cursor.moveToNext()){
                    WeightTrackingModel w = new WeightTrackingModel();
                    w.setWeight(String.valueOf(weight));
                    w.setDate("Today");
                    w.setTime("7:30");
                    if(cursor.getString(cursor.getColumnIndex("image")).equals("green")){
                        w.setImage(R.drawable.arrow_down_green);
                    }
                    w.setWeightInfo(String.valueOf(cursor.getFloat(cursor.getColumnIndex("weight"))));
                    weightTrackingModelList.add(w);
                }
                weightTrackingBinding.weightTrackingRecyclerView.setNestedScrollingEnabled(false);
                WeightTrackingRecyclerViewAdapter weightTrackingRecyclerViewAdapter = new WeightTrackingRecyclerViewAdapter(getActivity());
                weightTrackingBinding.weightTrackingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                weightTrackingBinding.weightTrackingRecyclerView.setAdapter(weightTrackingRecyclerViewAdapter);
                weightTrackingRecyclerViewAdapter.setWeightTrackingModelList(weightTrackingModelList);
                weightTrackingRecyclerViewAdapter.notifyDataSetChanged();
            }
        });




        weightTrackingBinding.backToHomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentManager.popBackStack();
                fragmentTransaction.commit();
            }
        });
    }
}