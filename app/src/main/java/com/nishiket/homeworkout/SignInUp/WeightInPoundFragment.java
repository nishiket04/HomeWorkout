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
import com.nishiket.homeworkout.databinding.FragmentWeightInPoundBinding;
import com.nishiket.homeworkout.model.UserWeightModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;
import com.nishiket.homeworkout.viewmodel.AuthViewModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeightInPoundFragment extends Fragment {
    private FragmentWeightInPoundBinding weightInPoundBinding;
    double weight_pound;

    public WeightInPoundFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        weightInPoundBinding = FragmentWeightInPoundBinding.inflate(inflater,container,false);
        return weightInPoundBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AuthViewModel authViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);
        weightInPoundBinding.weightContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!weightInPoundBinding.poundEditText.getText().toString().isEmpty()){
                    weight_pound = Float.parseFloat(weightInPoundBinding.poundEditText.getText().toString());
                }
                UserWeightModel userWeightModel = new UserWeightModel();
                userWeightModel.setEmail(authViewModel.getCurrentUser().getEmail());
                userWeightModel.setWeight(weight_pound);
                Log.d("weight", "onResponse: "+weight_pound);
                Call<ResponseBody> call = retrofit.setWeight(123,userWeightModel);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("weight", "onResponse: "+response.code());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("weight", "onFailure: "+t.getMessage());
                    }
                });
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.frame,new GoalWeightFragment()).commit();
            }
        });
    }
}