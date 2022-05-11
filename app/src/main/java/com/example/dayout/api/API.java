package com.example.dayout.api;

import com.example.dayout.models.EditProfileModel;
import com.example.dayout.models.LoginModel;

import com.example.dayout.models.ProfileModel;
import com.example.dayout.models.UserRegisterModel;
import com.example.dayout.models.PopualrPlace.PopularPlace;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {


    /**
     * Get Request
     */

    @GET("api/place/popular/{id}")
    Call<PopularPlace> getPopularPlace(@Path("id") int id);

    @GET("api/user/profile/customer/{id}")
    Call<ProfileModel> getPassengerProfile(@Path("id") int id);



    /**
     * Post Request
     */

    @POST("api/user/login")
    Call<LoginModel> login(@Body JsonObject loginReqBody);


    @POST("api/user/register")
    Call<UserRegisterModel> registerPassenger(@Body UserRegisterModel profile);

    @POST("api/place/favorite")
    Call<ResponseBody> addToFavorite(@Body JsonObject favoritePlace);



    /**
     * Put Request
     */

    @POST("api/user/profile/customer/edit/{id}")
    Call<ProfileModel> editProfile(@Path("id") int id, @Body EditProfileModel model);


    /**
     * Delete Request
     */
}