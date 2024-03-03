package com.nishiket.homeworkout.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nishiket.homeworkout.model.ImageModel;
import com.nishiket.homeworkout.model.UserDetailModel;
import com.nishiket.homeworkout.repository.UserDetailRepository;

public class UserDetailViewModel extends AndroidViewModel implements UserDetailRepository.FetchedData {
    private UserDetailRepository userDetailRepository;
    private MutableLiveData<UserDetailModel> userDetailModelMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ImageModel> imageModelMutableLiveData = new MutableLiveData<>();
    public UserDetailViewModel(@NonNull Application application) {
        super(application);
        userDetailRepository = new UserDetailRepository(application,this);
    }

    public MutableLiveData<UserDetailModel> getUserDetailModelMutableLiveData() {
        return userDetailModelMutableLiveData;
    }

    public MutableLiveData<ImageModel> getImageModelMutableLiveData() {
        return imageModelMutableLiveData;
    }

    public void getData(int api_key, String email){
        userDetailRepository.getUserDetails(api_key,email);
    }

    public void getImage(int api_key,String email){
        userDetailRepository.getImage(api_key, email);
    }

    @Override
    public void onComplete(UserDetailModel userDetailModel) {
        userDetailModelMutableLiveData.setValue(userDetailModel);
    }

    @Override
    public void onCompleteImage(ImageModel imageModel) {
        imageModelMutableLiveData.setValue(imageModel);
    }
}
