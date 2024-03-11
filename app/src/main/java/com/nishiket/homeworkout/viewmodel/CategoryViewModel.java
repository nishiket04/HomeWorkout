package com.nishiket.homeworkout.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nishiket.homeworkout.model.CategoryModel;
import com.nishiket.homeworkout.repository.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel implements CategoryRepository.OnComplte{
    private Application application;
    private CategoryRepository categoryRepository;

    public MutableLiveData<List<CategoryModel>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    private MutableLiveData<List<CategoryModel>> listMutableLiveData = new MutableLiveData<>();
    public CategoryViewModel(@NonNull Application application) {
        super(application);
        categoryRepository = new CategoryRepository(application,this);
    }

    public void getData(int api_key){
        categoryRepository.getCategory(api_key);
    }

    @Override
    public void onComplte(List<CategoryModel> categoryModelList) {
        listMutableLiveData.setValue(categoryModelList);
    }
}
