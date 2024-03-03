package com.nishiket.homeworkout.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.nishiket.homeworkout.model.ImageModel;
import com.nishiket.homeworkout.model.UserDetailModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailRepository {
    private Application application;
    private Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);
    private FetchedData fetchedData;

    public UserDetailRepository(Application application,FetchedData fetchedData) {
        this.application = application;
        this.fetchedData = fetchedData;
    }

    public void getUserDetails(int api_key, String email){
        Call<UserDetailModel> call = retrofit.getUserDetail(api_key,email);
        call.enqueue(new Callback<UserDetailModel>() {
            @Override
            public void onResponse(Call<UserDetailModel> call, Response<UserDetailModel> response) {
                if(response.isSuccessful()){
                    if(fetchedData !=null){
                        Log.d("userdata", "onResponse: "+response.body().getName());
                        fetchedData.onComplete(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserDetailModel> call, Throwable t) {
                Log.d("userdata", "onResponse: "+t.getMessage());
            }
        });
    }

    public void getImage(int api_key,String email){
        Call<ImageModel> call = retrofit.getImage(api_key,email);
        call.enqueue(new Callback<ImageModel>() {
            @Override
            public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {
                if(response.isSuccessful()){
                    if(fetchedData != null){
                        fetchedData.onCompleteImage(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ImageModel> call, Throwable t) {

            }
        });
    }

    public interface FetchedData{
        void onComplete(UserDetailModel userDetailModel);
        void onCompleteImage(ImageModel imageModel);
    }
}
