package com.nishiket.homeworkout.repository;

import android.app.Application;

import com.nishiket.homeworkout.model.WormUpModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WormUpRepository {
    private Application application;
    private OnComplete oncomplete;
    private Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);

    public WormUpRepository(Application application, OnComplete oncomplete) {
        this.application = application;
        this.oncomplete = oncomplete;
    }

    public void getWormUps(int api_key,String id){
        Call<List<WormUpModel>> call = retrofit.getWormUp(api_key,id);
        call.enqueue(new Callback<List<WormUpModel>>() {
            @Override
            public void onResponse(Call<List<WormUpModel>> call, Response<List<WormUpModel>> response) {
                if(response.isSuccessful()){
                    if(oncomplete!=null){
                        oncomplete.onComplete(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<WormUpModel>> call, Throwable t) {

            }
        });
    }

    public interface OnComplete{
        void onComplete(List<WormUpModel> wormUpModelList);
    }
}
