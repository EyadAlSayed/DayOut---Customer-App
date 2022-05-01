package com.example.dayout.api;

import androidx.cardview.widget.CardView;

import com.example.dayout.models.LoginModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {


    /**
     * Get Request
     */



    /**
     * Post Request
     */

    @POST("api/user/login")
    Call<LoginModel> login(@Body JsonObject loginReqBody);


    /**
     * Put Request
     */




    /**
     * Delete Request
     */
}
