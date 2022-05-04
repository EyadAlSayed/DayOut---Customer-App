package com.example.dayout.api;

import com.example.dayout.models.LoginModel;

import com.example.dayout.models.ProfileModel;
import com.example.dayout.models.UserRegisterModel;
import com.example.dayout.models.PopularPlace;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {


    /**
     * Get Request
     */

    @GET("api/place/popular")
    Call<PopularPlace> getPopularPlace();

    @GET("api/customer/profile")
    Call<ProfileModel> getPassengerProfile();



    /**
     * Post Request
     */

    @POST("api/user/login")
    Call<LoginModel> login(@Body JsonObject loginReqBody);


    @POST("api/user/profile/customer")
    Call<UserRegisterModel> registerPassenger(@Body UserRegisterModel profile);

    @POST("api/place/favorite")
    Call<ResponseBody> addToFavorite(@Body JsonObject favoritePlace);



    /**
     * Put Request
     */




    /**
     * Delete Request
     */
}
