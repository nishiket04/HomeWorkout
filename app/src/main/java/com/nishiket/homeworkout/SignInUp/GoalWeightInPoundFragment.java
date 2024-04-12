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
import com.nishiket.homeworkout.databinding.FragmentGoalWeightInPoundBinding;
import com.nishiket.homeworkout.model.UserGoalWeightModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoalWeightInPoundFragment extends Fragment {
    private FragmentGoalWeightInPoundBinding goalWeightInPoundBinding;
    double goal_pound;
    public GoalWeightInPoundFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        goalWeightInPoundBinding = FragmentGoalWeightInPoundBinding.inflate(inflater,container,false);
        return goalWeightInPoundBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AuthViewModel authViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);
        goalWeightInPoundBinding.goalWeightContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!goalWeightInPoundBinding.goalPoundEditText.getText().toString().isEmpty()){
                    goal_pound = Float.parseFloat(goalWeightInPoundBinding.goalPoundEditText.getText().toString());
                }
                UserGoalWeightModel userGoalWeightModel = new UserGoalWeightModel();
                userGoalWeightModel.setEmail(authViewModel.getCurrentUser().getEmail());
                userGoalWeightModel.setGoalWeight(goal_pound);
                Log.d("goalWeight", "onResponse: "+goal_pound);
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