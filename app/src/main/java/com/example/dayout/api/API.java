package com.example.dayout.api;

import com.example.dayout.models.authModels.LoginModel;

import com.example.dayout.models.notification.NotificationModel;
import com.example.dayout.models.poll.PollsPaginationModel;
import com.example.dayout.models.popualrPlace.PlaceData;
import com.example.dayout.models.profile.ProfileData;
import com.example.dayout.models.profile.ProfileModel;
import com.example.dayout.models.popualrPlace.PlaceModel;

import com.example.dayout.models.profile.organizer.OrganizersModel;
import com.example.dayout.models.trip.BookTripModel;
import com.example.dayout.models.trip.TripPaginationModel;
import com.example.dayout.models.trip.place.PlaceDetailsModel;
import com.example.dayout.models.trip.roadMap.RoadMapModel;
import com.example.dayout.models.trip.TripDetailsModel;
import com.example.dayout.models.trip.TripListModel;
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
    Call<PlaceModel> getPopularPlaces(@Path("id") int id);

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
    Call<TripPaginationModel> getTripPost(@Query("page") int page);

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
    Call<PollsPaginationModel> getPolls();

    @GET("api/favorites/places")
    Call<PlaceModel> getFavoritePlace();

    @GET("api/organizer/index")
    Call<OrganizersModel> getAllOrganizers();

    @GET("api/followers")
    Call<OrganizersModel> getAllFollowedOrganizers();


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
    Call<TripPaginationModel> searchForTrip(@Body JsonObject searchObject);

    @POST("api/trip/rate")
    Call<ResponseBody> rateTrip(@Body JsonObject rate);

    @POST("api/bookings/book")
    Call<ResponseBody> bookTrip(@Body BookTripModel model);




    /**
     * Put Request
     */

    @POST("api/user/profile/customer/edit")
    Call<ProfileModel> editProfile(@Body JsonObject model);

    @PUT("api/user/mobile-token")
    Call<ResponseBody> sendFirebaseToken(@Body JsonObject firebaseObj);

    @PUT("api/polls/vote/{pollId}/{choiceId}")
    Call<ResponseBody> voteOnPoll(@Path("pollId")int pollId,@Path("choiceId") int choiceId);

    @PUT("api/bookings/{trip_id}/user/cancel")
    Call<ResponseBody> cancelBooking(@Path("trip_id") int tripId);


    /**
     * Delete Request
     */
}