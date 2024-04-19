package com.nishiket.homeworkout.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<List<Integer>> liveData=new MutableLiveData<>();
    public void setLiveData(List<Integer> integerList) {
        liveData.setValue(integerList);
    }

    public MutableLiveData<List<Integer>> getLiveData() {
        return liveData;
    }
}
