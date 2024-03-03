package com.nishiket.homeworkout.retrofit;

import com.nishiket.homeworkout.model.ImageModel;
import com.nishiket.homeworkout.model.UserDetailModel;
import com.nishiket.homeworkout.model.UserInsertModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
}
