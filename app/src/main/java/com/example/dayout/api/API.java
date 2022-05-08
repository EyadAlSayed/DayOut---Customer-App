package com.example.dayout.api;

import com.example.dayout.models.EditProfileModel;
import com.example.dayout.models.LoginModel;

import com.example.dayout.models.ProfileModel;
import com.example.dayout.models.UserRegisterModel;
import com.example.dayout.models.PopularPlace;

import com.example.dayout.ui.fragments.profile.EditProfileFragment;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

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


    @POST("api/user/register")
    Call<UserRegisterModel> registerPassenger(@Body UserRegisterModel profile);

    @POST("api/place/favorite")
    Call<ResponseBody> addToFavorite(@Body JsonObject favoritePlace);



    /**
     * Put Request
     */

    @PUT("api/user/profile/customer/edit")
    Call<EditProfileModel> editProfile(@Body EditProfileModel model);


    /**
     * Delete Request
     */
}