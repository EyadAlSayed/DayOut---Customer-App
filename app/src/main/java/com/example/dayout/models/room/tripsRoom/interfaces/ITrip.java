package com.example.dayout.models.room.tripsRoom.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dayout.models.popualrPlace.PlaceData;
import com.example.dayout.models.trip.TripData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

import static com.example.dayout.config.AppConstants.TRIP_DATA;

@Dao
public interface ITrip {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    Completable insertTrip(TripData tripData);


    @Query("select * from " + TRIP_DATA)
    Single<List<TripData>> getTrips();

    @Query("select * from " + TRIP_DATA +" where isUpcoming = 1")
    Single<List<TripData>> getUpComingTrips();

    @Query("select * from " + TRIP_DATA +" where isActive = 1")
    Single<List<TripData>> getActiveTrips();

    @Query("select * from " + TRIP_DATA +" where isUpcoming = 0 AND isActive = 0")
    Single<List<TripData>> getHistoryTrips();

    @Query("select * from " + TRIP_DATA + " where id = :tripId")
    Single<TripData> getTripById(int tripId);

    @Query("delete from "+TRIP_DATA)
    Single<Void> deleteAll();
}