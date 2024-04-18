package com.nishiket.homeworkout.user;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.adapter.TraingListRecyclerViewAdapter;
import com.nishiket.homeworkout.databinding.FragmentCreatePersonalTrainingBinding;
import com.nishiket.homeworkout.model.CustomWorkoutModel;
import com.nishiket.homeworkout.model.TraingListModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePersonalTrainingFragment extends Fragment {

    private FragmentCreatePersonalTrainingBinding createPersonalTrainingBinding;
    private List<TraingListModel> traingListModelList =  new ArrayList<>();
    private Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);
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

        AuthViewModel authViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);

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

        createPersonalTrainingBinding.createWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomWorkoutModel customWorkoutModel = new CustomWorkoutModel();
                customWorkoutModel.setEmail(authViewModel.getCurrentUser().getEmail());
                customWorkoutModel.setName(createPersonalTrainingBinding.editText3.getText().toString());
                if(createPersonalTrainingBinding.warmUpSwitch.isChecked()){
                    customWorkoutModel.setWormup(1);
                }
                else {
                    customWorkoutModel.setWormup(-1);
                }
                customWorkoutModel.setEquipment("[3,4]");
                customWorkoutModel.setLevel(traingListRecyclerViewAdapter.getLevel()+1);
                Call<ResponseBody> call = retrofit.setCustomWorkout(123,customWorkoutModel);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Log.d("add", "onResponse: "+response.code());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("add", "onResponse: "+t.getMessage());
                    }
                });
            }
        });

    }

}