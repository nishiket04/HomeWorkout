package com.nishiket.homeworkout.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.nishiket.homeworkout.model.WormUpInfoModel;
import com.nishiket.homeworkout.repository.WormUpInfoRepository;

public class WormUpInfoViewModel extends AuthViewModel implements WormUpInfoRepository.OnComplete {
    private WormUpInfoRepository wormUpInfoRepository;
    private MutableLiveData<WormUpInfoModel> wormUpInfoMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<WormUpInfoModel> getWormUpInfoMutableLiveData() {
        return wormUpInfoMutableLiveData;
    }

    public WormUpInfoViewModel(@NonNull Application application) {
        super(application);
        wormUpInfoRepository = new WormUpInfoRepository(application,this);
    }

    public void getWormUpInfo(int api_key,int id){
        wormUpInfoRepository.getWormUpInfo(api_key, id);
    }

    @Override
    public void onComplete(WormUpInfoModel wormUpInfoModel) {
        wormUpInfoMutableLiveData.setValue(wormUpInfoModel);
    }
}
