package com.nishiket.homeworkout.repository;

import android.app.Application;
import android.util.Log;

import com.nishiket.homeworkout.model.ExercisesInfoModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciesInfoRepository {
    private Application application;
    private OnComplete onComplete;
    private Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);

    public ExerciesInfoRepository(Application application, OnComplete onComplete) {
        this.application = application;
        this.onComplete = onComplete;
    }

    public void getExerciseInfo(int api_key,int id){
        Call<ExercisesInfoModel> call = retrofit.getExerciesInfo(api_key, id);
        call.enqueue(new Callback<ExercisesInfoModel>() {
            @Override
            public void onResponse(Call<ExercisesInfoModel> call, Response<ExercisesInfoModel> response) {
                if(response.isSuccessful()){
                        if(onComplete!=null){
                            Log.d("info", "onResponse: "+response.code());
                            onComplete.onComplete(response.body());
                        }
                }
            }

            @Override
            public void onFailure(Call<ExercisesInfoModel> call, Throwable t) {
                Log.d("info", "onResponse: "+t.getMessage());
            }
        });
    }

    public interface OnComplete{
        void onComplete(ExercisesInfoModel exercisesInfoModel);
    }
}
