package com.nishiket.homeworkout.retrofit;

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
}
