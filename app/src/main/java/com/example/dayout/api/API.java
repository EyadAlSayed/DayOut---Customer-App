package com.example.dayout.api;

import com.example.dayout.models.NotificationModel;

import com.example.dayout.models.authModels.LoginModel;

import com.example.dayout.models.poll.PollsModel;
import com.example.dayout.models.profile.ProfileData;
import com.example.dayout.models.profile.ProfileModel;
import com.example.dayout.models.popualrPlace.PopularPlaceModel;

import com.example.dayout.models.trip.place.PlaceDetailsModel;
import com.example.dayout.models.trip.roadMap.RoadMapModel;
import com.example.dayout.models.trip.TripDetailsModel;
import com.example.dayout.models.trip.TripListModel;
import com.example.dayout.models.trip.TripPost;
import com.example.dayout.models.trip.tripType.TripTypeModel;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {


    /**
     * Get Request
     */

    @GET("api/place/popular/{id}")
    Call<PopularPlaceModel> getPopularPlaces(@Path("id") int id);

    @GET("api/user/profile/customer/{id}")
    Call<ProfileModel> getPassengerProfile(@Path("id") int id);

    @GET("api/trip/upcoming/customer")
    Call<TripListModel> getUpcomingTrips(@Query("type") String type);

    @GET("api/trip/active/customer")
    Call<TripListModel> getActiveTrips(@Query("type") String type);

    @GET("api/trip/history/customer")
    Call<TripListModel> getHistoryTrips(@Query("type") String type);

    @GET("api/trip/types")
    Call<TripTypeModel> getTripType();

    @GET("api/trip")
    Call<TripPost> getTripPost();

    @GET("api/trip/{id}/details")
    Call<TripDetailsModel> getTripDetails(@Path("id") int id);

    @GET("api/notifications")
    Call<NotificationModel> getNotifications();

    @GET("api/user/logout")
    Call<ResponseBody> logOut();

    @GET("api/trip/road-map/{id}")
    Call<RoadMapModel> getRoadMap(@Path("id") int tripId);

    @GET("api/place/details/{id}")
    Call<PlaceDetailsModel> getPlaceDetails(@Path("id") int id);

    @GET("api/polls")
    Call<PollsModel> getPolls();


    /**
     * Post Request
     */

    @POST("api/user/login")
    Call<LoginModel> login(@Body JsonObject loginReqBody);


    @POST("api/user/register")
    Call<ProfileData> registerPassenger(@Body ProfileData profile);

    @POST("api/place/favorite")
    Call<ResponseBody> addToFavorite(@Body JsonObject favoritePlace);

    @POST("api/search/trip")
    Call<TripPost> searchForTrip(@Body JsonObject searchObject);

    @POST("api/trip/rate")
    Call<ResponseBody> rateTrip(@Body JsonObject rate);



    /**
     * Put Request
     */

    @POST("api/user/profile/customer/edit")
    Call<ProfileModel> editProfile(@Body JsonObject model);

    @PUT("api/user/mobile-token")
    Call<ResponseBody> sendFirebaseToken(@Body JsonObject firebaseObj);

    @PUT("api/polls/vote/{pollId}/{choiceId}")
    Call<ResponseBody> voteOnPoll(@Path("pollId")int pollId,@Path("choiceId") int choiceId);


    /**
     * Delete Request
     */
}