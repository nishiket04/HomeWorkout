package com.nishiket.homeworkout.retrofit;

import com.nishiket.homeworkout.model.ExercisesModel;
import com.nishiket.homeworkout.model.ImageModel;
import com.nishiket.homeworkout.model.UserDetailModel;
import com.nishiket.homeworkout.model.UserInsertModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Retrofit {
        @POST("user.php")
        Call<ResponseBody> sendData(@Query("api_key") int i, @Body UserInsertModel userData);
        @GET("getImage.php")
        Call<ImageModel> getImage(@Query("api_key") int apiKey, @Query("email") String email);

        @GET("getUserDetail.php")
        Call<UserDetailModel> getUserDetail(@Query("api_key") int apiKey, @Query("email") String email);

        @GET("getExercises.php")
        Call<List<ExercisesModel>> getExcisemodel(@Query("api_key") int apiKey);

}
