package com.nishiket.homeworkout.repository;

import android.app.Application;

import com.nishiket.homeworkout.model.EquipmentModel;
import com.nishiket.homeworkout.retrofit.Retrofit;
import com.nishiket.homeworkout.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EquipmentRepository {
    private Application application;
    private Retrofit retrofit = RetrofitClient.getRetrofitInstance().create(Retrofit.class);

    private OnComplete onComplete;

    public EquipmentRepository(Application application, OnComplete onComplete) {
        this.application = application;
        this.onComplete = onComplete;
    }

    public void getEquipment(int api_key,String id){
        Call<List<EquipmentModel>> call = retrofit.getEquipment(api_key, id);
        call.enqueue(new Callback<List<EquipmentModel>>() {
            @Override
            public void onResponse(Call<List<EquipmentModel>> call, Response<List<EquipmentModel>> response) {
                if(response.isSuccessful()){
                    if(onComplete!=null){
                        onComplete.onComplete(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<EquipmentModel>> call, Throwable t) {

            }
        });
    }

    public interface OnComplete{
        void onComplete(List<EquipmentModel> equipmentModels);
    }
}
