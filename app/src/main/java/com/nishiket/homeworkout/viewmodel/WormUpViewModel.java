package com.nishiket.homeworkout.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nishiket.homeworkout.model.WormUpModel;
import com.nishiket.homeworkout.repository.WormUpRepository;

import java.util.List;

public class WormUpViewModel extends AndroidViewModel implements WormUpRepository.OnComplete {
    private WormUpRepository wormUpRepository;
    private MutableLiveData<List<WormUpModel>> wormUpMutableLiveData= new MutableLiveData<>();

    public MutableLiveData<List<WormUpModel>> getWormUpMutableLiveData() {
        return wormUpMutableLiveData;
    }

    public WormUpViewModel(@NonNull Application application) {
        super(application);
        wormUpRepository = new WormUpRepository(application,this);
    }

    public void getWormups(int api_key,String id){
        wormUpRepository.getWormUps(api_key, id);
    }

    @Override
    public void onComplete(List<WormUpModel> wormUpModelList) {
        wormUpMutableLiveData.setValue(wormUpModelList);
    }
}
