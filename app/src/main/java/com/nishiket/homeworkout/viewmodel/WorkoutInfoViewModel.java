package com.nishiket.homeworkout.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nishiket.homeworkout.model.WorkoutInfoModel;
import com.nishiket.homeworkout.repository.WorkoutInfoRepository;

public class WorkoutInfoViewModel extends AndroidViewModel implements WorkoutInfoRepository.OnComplete {
    private WorkoutInfoRepository workoutInfoRepository;
    private MutableLiveData<WorkoutInfoModel> workoutInfoModelMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<WorkoutInfoModel> getWorkoutInfoModelMutableLiveData() {
        return workoutInfoModelMutableLiveData;
    }

    public WorkoutInfoViewModel(@NonNull Application application) {
        super(application);
        workoutInfoRepository = new WorkoutInfoRepository(application,this);
    }

    public void getWorkoutInfo(int api_key,int id){
        workoutInfoRepository.getWorkoutIfo(api_key, id);
    }

    @Override
    public void onComplete(WorkoutInfoModel workoutInfoModel) {
        workoutInfoModelMutableLiveData.setValue(workoutInfoModel);
    }
}
