package com.nishiket.homeworkout.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nishiket.homeworkout.model.PersonalTrainingModel;
import com.nishiket.homeworkout.repository.WorkoutRepository;

import java.util.List;

public class WorkoutViewModel extends AndroidViewModel implements WorkoutRepository.OnComplete {
    private Application application;
    private WorkoutRepository workoutRepository;
    private MutableLiveData<List<PersonalTrainingModel>> listMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<PersonalTrainingModel>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public WorkoutViewModel(@NonNull Application application) {
        super(application);
        workoutRepository = new WorkoutRepository(application,this);
    }

    public void getData(int api_key){
        workoutRepository.getWorkouts(api_key);
    }

    @Override
    public void onComplete(List<PersonalTrainingModel> personalTrainingModelList) {
        listMutableLiveData.setValue(personalTrainingModelList);
    }
}
