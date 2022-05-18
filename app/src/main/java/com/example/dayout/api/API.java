package com.example.dayout.api;

import com.example.dayout.models.profile.EditProfileModel;
import com.example.dayout.models.authModels.LoginModel;

import com.example.dayout.models.profile.ProfileModel;
import com.example.dayout.models.authModels.UserRegisterModel;
import com.example.dayout.models.popualrPlace.PopularPlace;

import com.example.dayout.models.trip.TripModel;
import com.example.dayout.models.trip.TripPost;
import com.example.dayout.models.trip.Type;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {


    /**
     * Get Request
     */

    @GET("api/place/popular/{id}")
    Call<PopularPlace> getPopularPlace(@Path("id") int id);

    @GET("api/user/profile/customer/{id}")
    Call<ProfileModel> getPassengerProfile(@Path("id") int id);

    @GET("api/trip/upcoming")
    Call<TripModel> getUpcomingTrips();

    @GET("api/trip/active")
    Call<TripModel> getActiveTrips();

    @GET("api/trip/history")
    Call<TripModel> getHistoryTrips();

    @GET("api/trip/types")
    Call<Type> getTripType();

    @GET("api/trip")
    Call<TripPost> getTripPost();

    /**
     * Post Request
     */

    @POST("api/user/login")
    Call<LoginModel> login(@Body JsonObject loginReqBody);


    @POST("api/user/register")
    Call<UserRegisterModel> registerPassenger(@Body UserRegisterModel profile);

    @POST("api/place/favorite")
    Call<ResponseBody> addToFavorite(@Body JsonObject favoritePlace);

    @POST("api/search/trip")
    Call<ResponseBody> searchForTrip(@Body JsonObject searchObject);

    @POST("api/trip/rate")
    Call<TripModel> rateTrip(@Body JsonObject rate);


    /**
     * Put Request
     */

    @POST("api/user/profile/customer/edit/{id}")
    Call<ProfileModel> editProfile(@Path("id") int id, @Body EditProfileModel model);


    /**
     * Delete Request
     */
}