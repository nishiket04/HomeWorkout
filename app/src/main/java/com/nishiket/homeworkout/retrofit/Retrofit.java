package com.nishiket.homeworkout.retrofit;

import com.nishiket.homeworkout.model.CategoryModel;
import com.nishiket.homeworkout.model.CustomWorkoutModel;
import com.nishiket.homeworkout.model.EquipmentModel;
import com.nishiket.homeworkout.model.ExercisesInfoModel;
import com.nishiket.homeworkout.model.ExercisesModel;
import com.nishiket.homeworkout.model.ImageModel;
import com.nishiket.homeworkout.model.PersonalTrainingModel;
import com.nishiket.homeworkout.model.UserBirthModel;
import com.nishiket.homeworkout.model.UserDetailModel;
import com.nishiket.homeworkout.model.UserGenderModel;
import com.nishiket.homeworkout.model.UserGoalModel;
import com.nishiket.homeworkout.model.UserGoalWeightModel;
import com.nishiket.homeworkout.model.UserHeightModel;
import com.nishiket.homeworkout.model.UserInsertModel;
import com.nishiket.homeworkout.model.UserLevelModel;
import com.nishiket.homeworkout.model.UserNameModel;
import com.nishiket.homeworkout.model.UserWeightModel;
import com.nishiket.homeworkout.model.WorkoutInfoModel;
import com.nishiket.homeworkout.model.WormUpInfoModel;
import com.nishiket.homeworkout.model.WormUpModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
        @GET("getWorkout.php")
        Call<List<PersonalTrainingModel>> getWorkout(@Query("api_key") int apiKey);

        @GET("getCategory.php")
        Call<List<CategoryModel>> getCategory(@Query("api_key") int apiKey);

        @POST("gender.php")
        Call<ResponseBody> setGender(@Query("api_key") int i,@Body UserGenderModel userGender);
        @POST("goal.php")
        Call<ResponseBody> setGoale(@Query("api_key") int i,@Body UserGoalModel userGoal);
        @POST("birth.php")
        Call<ResponseBody> setBirthDate(@Query("api_key") int i,@Body UserBirthModel userBirth);

        @POST("height.php")
        Call<ResponseBody> setHeight(@Query("api_key") int i, @Body UserHeightModel userHeight );
        @POST("weight.php")
        Call<ResponseBody> setWeight(@Query("api_key") int i, @Body UserWeightModel userWeight);

        @POST("goalWeight.php")
        Call<ResponseBody> setGoalWeight(@Query("api_key") int i, @Body UserGoalWeightModel userGoalWeight);

        @POST("level.php")
        Call<ResponseBody> setLevel(@Query("api_key") int i, @Body UserLevelModel userLevel);
        @Multipart
        @POST("image.php")
        Call<ResponseBody> setImage(@Query("api_key") int i, @Query("email") String email, @Part MultipartBody.Part sendImage);

        @POST("name.php")
        Call<ResponseBody> setName(@Query("api_key") int i, @Body UserNameModel userName);

        @GET("getExerciesInfo.php")
        Call<ExercisesInfoModel> getExerciesInfo(@Query("api_key")int i, @Query("id")int id);

        @GET("getEquipment.php")
        Call<List<EquipmentModel>> getEquipment(@Query("api_key")int i, @Query("id")String id);

        @GET("getWorkoutInfo.php")
        Call<WorkoutInfoModel> getWorkoutInfo(@Query("api_key")int i, @Query("id")int id);

        @GET("getSelectedExercies.php")
        Call<List<ExercisesModel>> getSelectedExercies(@Query("api_key")int i, @Query("id")String id);

        @GET("getWarmUp.php")
        Call<List<WormUpModel>> getWormUp(@Query("api_key") int i, @Query("id") String id);

        @GET("getWarmUpInfo.php")
        Call<WormUpInfoModel> getWormUpInfo(@Query("api_key")int i, @Query("id")int id);

        @POST("insertCustomWorkout.php")
        Call<ResponseBody> setCustomWorkout(@Query("api_key") int i,@Body CustomWorkoutModel customWorkoutModel);

}
