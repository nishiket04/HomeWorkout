package com.nishiket.homeworkout.retrofit;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient{
    // server api =https://fitoozone.000webhostapp.com/API/
    //localhost API = http://192.168.43.48/homeworkout/API/
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://fitoozone.000webhostapp.com/API/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
