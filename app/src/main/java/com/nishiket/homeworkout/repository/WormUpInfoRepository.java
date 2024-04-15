package com.nishiket.homeworkout.repository;

import android.app.Application;

import com.nishiket.homeworkout.model.WormUpInfoModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WormUpInfoRepository {
    private Application application;
    private OnComplete onComplete;
    private Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);

    public WormUpInfoRepository(Application application, OnComplete onComplete) {
        this.application = application;
        this.onComplete = onComplete;
    }
    public void getWormUpInfo(int api_key,int id){
        Call<WormUpInfoModel> call = retrofit.getWormUpInfo(api_key, id);
        call.enqueue(new Callback<WormUpInfoModel>() {
            @Override
            public void onResponse(Call<WormUpInfoModel> call, Response<WormUpInfoModel> response) {
                if(response.isSuccessful()){
                    if(onComplete!=null){
                        onComplete.onComplete(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<WormUpInfoModel> call, Throwable t) {

            }
        });
    }

    public interface OnComplete{
        void onComplete(WormUpInfoModel wormUpInfoModel);
    }
}
