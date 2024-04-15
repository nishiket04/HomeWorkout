package com.nishiket.homeworkout.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nishiket.homeworkout.model.ExercisesModel;
import com.nishiket.homeworkout.repository.ExcirciseRepository;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel implements ExcirciseRepository.OnComplete {
    private Application application;
    private ExcirciseRepository excirciseRepository;
    private MutableLiveData<List<ExercisesModel>> listMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<List<ExercisesModel>> getExercisesListMutableLiveData() {
        return listMutableLiveData;
    }

    public ExerciseViewModel(Application application) {
        super(application);
        excirciseRepository = new ExcirciseRepository(application,this);
    }
    public void getExercises(int api_key){
        excirciseRepository.getExercises(api_key);
    }

    public void getSelectedExercises(int api_key,String id){
        excirciseRepository.getSelectedExercises(api_key, id);
    }

    @Override
    public void onComplete(List<ExercisesModel> exercisesModelList) {
        listMutableLiveData.setValue(exercisesModelList);
    }
}
