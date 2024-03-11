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

public class CategoryRepository {
    private Application application;
    private OnComplte onComplte;
    private Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);

    public CategoryRepository(Application application, OnComplte onComplte) {
        this.application = application;
        this.onComplte = onComplte;
    }
    public void getCategory(int api_key){
        Call<List<CategoryModel>> call = retrofit.getCategory(api_key);
        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                if(response.isSuccessful()){
                    if(onComplte!=null) {
                        onComplte.onComplte(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {

            }
        });
    }
    public interface OnComplte{
        void onComplte(List<CategoryModel> categoryModelList);
    }
}
