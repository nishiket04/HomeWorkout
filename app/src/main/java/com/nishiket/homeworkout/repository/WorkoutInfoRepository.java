package com.nishiket.homeworkout.repository;

import android.app.Application;

import com.nishiket.homeworkout.model.WorkoutInfoModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkoutInfoRepository {
    private Application application;
    private Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);
    private OnComplete onComplete;

    public WorkoutInfoRepository(Application application, OnComplete onComplete) {
        this.application = application;
        this.onComplete = onComplete;
    }
    public void getWorkoutIfo(int api_key,int id){
        Call<WorkoutInfoModel> call = retrofit.getWorkoutInfo(api_key, id);
        call.enqueue(new Callback<WorkoutInfoModel>() {
            @Override
            public void onResponse(Call<WorkoutInfoModel> call, Response<WorkoutInfoModel> response) {
                if(response.isSuccessful()){
                    if(onComplete!=null){
                        onComplete.onComplete(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<WorkoutInfoModel> call, Throwable t) {

            }
        });
    }
    public interface OnComplete{
        void onComplete(WorkoutInfoModel workoutInfoModel);
    }
}
