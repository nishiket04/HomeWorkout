package com.nishiket.homeworkout.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nishiket.homeworkout.model.ExercisesInfoModel;
import com.nishiket.homeworkout.repository.ExerciesInfoRepository;

public class ExerciesInfoViewModel extends AndroidViewModel implements ExerciesInfoRepository.OnComplete {

    private ExerciesInfoRepository exerciesInfoRepository;
    private MutableLiveData<ExercisesInfoModel> exercisesInfoModelMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<ExercisesInfoModel> getExercisesInfoModelMutableLiveData() {
        return exercisesInfoModelMutableLiveData;
    }

    public ExerciesInfoViewModel(@NonNull Application application) {
        super(application);
        exerciesInfoRepository = new ExerciesInfoRepository(application,this);
    }

    public void getExerciesInfo(int api_key,int id){
        exerciesInfoRepository.getExerciseInfo(api_key, id);
    }

    @Override
    public void onComplete(ExercisesInfoModel exercisesInfoModel) {
            exercisesInfoModelMutableLiveData.setValue(exercisesInfoModel);
    }
}
