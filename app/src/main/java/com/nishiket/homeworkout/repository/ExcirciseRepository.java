package com.nishiket.homeworkout.repository;

import android.app.Application;

import com.nishiket.homeworkout.model.CategoryModel;
import com.nishiket.homeworkout.model.ExercisesModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExcirciseRepository {
    private Application application;
    private OnComplete onComplete;
    private Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);

    public ExcirciseRepository(Application application,OnComplete onComplete) {
        this.application = application;
        this.onComplete = onComplete;
    }

    public void getExercises(int api_key){
        Call<List<ExercisesModel>> call = retrofit.getExcisemodel(api_key);
        call.enqueue(new Callback<List<ExercisesModel>>() {
            @Override
            public void onResponse(Call<List<ExercisesModel>> call, Response<List<ExercisesModel>> response) {
                if(response.isSuccessful()){
                    if(onComplete!=null){
                        onComplete.onComplete(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ExercisesModel>> call, Throwable t) {

            }
        });
    }

    public void getSelectedExercises(int api_key,String id){
        Call<List<ExercisesModel>> call = retrofit.getSelectedExercies(api_key, id);
        call.enqueue(new Callback<List<ExercisesModel>>() {
            @Override
            public void onResponse(Call<List<ExercisesModel>> call, Response<List<ExercisesModel>> response) {
                if(response.isSuccessful()){
                    if(onComplete!=null){
                        onComplete.onComplete(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ExercisesModel>> call, Throwable t) {

            }
        });
    }
    public interface OnComplete{
        void onComplete(List<ExercisesModel> exercisesModelList);
    }
}
