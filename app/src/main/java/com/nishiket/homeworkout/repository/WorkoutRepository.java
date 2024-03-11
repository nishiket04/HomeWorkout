package com.nishiket.homeworkout.repository;

import android.app.Application;

import com.nishiket.homeworkout.model.PersonalTrainingModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkoutRepository {
    private Application application;
    private OnComplete onComplete;
    private Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);


    public WorkoutRepository(Application application, OnComplete onComplete) {
        this.application = application;
        this.onComplete = onComplete;
    }

    public void getWorkouts(int api_key){
        Call<List<PersonalTrainingModel>> call = retrofit.getWorkout(api_key);
        call.enqueue(new Callback<List<PersonalTrainingModel>>() {
            @Override
            public void onResponse(Call<List<PersonalTrainingModel>> call, Response<List<PersonalTrainingModel>> response) {
                if(response.isSuccessful()){
                    if(onComplete!=null){
                        onComplete.onComplete(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PersonalTrainingModel>> call, Throwable t) {

            }
        });
    }

    public interface OnComplete{
        void onComplete(List<PersonalTrainingModel> personalTrainingModelList);
    }
}
