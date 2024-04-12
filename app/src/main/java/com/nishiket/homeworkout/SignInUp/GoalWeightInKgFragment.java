package com.nishiket.homeworkout.SignInUp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.homeworkout.R;
import com.nishiket.homeworkout.databinding.FragmentGoalWeightInKgBinding;
import com.nishiket.homeworkout.model.UserGoalWeightModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoalWeightInKgFragment extends Fragment {
    private FragmentGoalWeightInKgBinding goalWeightInKgBinding;
    double goal_kg;
    public GoalWeightInKgFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        goalWeightInKgBinding = FragmentGoalWeightInKgBinding.inflate(inflater,container,false);
        return goalWeightInKgBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AuthViewModel authViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);
        goalWeightInKgBinding.goalWeightContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!goalWeightInKgBinding.goalKgEditText.getText().toString().isEmpty()){
                    goal_kg = Float.parseFloat(goalWeightInKgBinding.goalKgEditText.getText().toString());
                }
                UserGoalWeightModel userGoalWeightModel = new UserGoalWeightModel();
                userGoalWeightModel.setEmail(authViewModel.getCurrentUser().getEmail());
                userGoalWeightModel.setGoalWeight(goal_kg);
                Log.d("goalWeight", "onResponse: "+goal_kg);
                Call<ResponseBody> call = retrofit.setGoalWeight(123, userGoalWeightModel);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("goalWeight", "onResponse: "+response.code());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("goalWeight", "onFailure: "+t.getMessage());
                    }
                });
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new TrainingLevelFragment()).commit();
            }
        });
    }
}