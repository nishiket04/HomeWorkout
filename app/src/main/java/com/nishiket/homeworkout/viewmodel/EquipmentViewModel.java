package com.nishiket.homeworkout.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nishiket.homeworkout.model.EquipmentModel;
import com.nishiket.homeworkout.repository.EquipmentRepository;

import java.util.List;

public class EquipmentViewModel extends AndroidViewModel implements EquipmentRepository.OnComplete {
    private EquipmentRepository equipmentRepository;
    private MutableLiveData<List<EquipmentModel>> listMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<EquipmentModel>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public EquipmentViewModel(@NonNull Application application) {
        super(application);
        equipmentRepository = new EquipmentRepository(application,this);
    }

    public void getEquipment(int api_key,String id){
        equipmentRepository.getEquipment(api_key, id);
    }

    @Override
    public void onComplete(List<EquipmentModel> equipmentModels) {
        listMutableLiveData.setValue(equipmentModels);
    }
}
