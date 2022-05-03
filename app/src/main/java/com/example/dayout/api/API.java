package com.example.dayout.api;

import androidx.cardview.widget.CardView;

import com.example.dayout.models.LoginModel;

import com.example.dayout.models.ProfileModel;
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



    /**
     * Post Request
     */

    @POST("api/user/login")
    Call<LoginModel> login(@Body JsonObject loginReqBody);


    @POST("api/user/profile/customer")
    Call<ProfileModel> addPassenger(@Body ProfileModel profile);

    @POST("api/place/favorite")
    Call<ResponseBody> addToFavorite(@Body JsonObject favoritePlace);



    /**
     * Put Request
     */




    /**
     * Delete Request
     */
}
