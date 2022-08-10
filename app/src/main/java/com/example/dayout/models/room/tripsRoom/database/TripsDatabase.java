package com.example.dayout.models.room.tripsRoom.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dayout.models.room.tripsRoom.interfaces.ITrip;
import com.example.dayout.models.trip.CustomerTrip;
import com.example.dayout.models.trip.TripData;
import com.example.dayout.models.trip.TripPaginationData;
import com.example.dayout.models.trip.TripPaginationModel;
import com.example.dayout.models.trip.TripPhotoData;
import com.example.dayout.models.trip.place.PlaceTripData;
import com.example.dayout.models.trip.tripType.TripType;

import static com.example.dayout.config.AppConstants.TRIP_DB;

@Database(entities = {TripPaginationModel.class, TripPaginationData.class, TripData.class, TripType.class,
        PlaceTripData.class, CustomerTrip.class, TripPhotoData.class}, exportSchema = false, version = 1)
abstract public class TripsDatabase extends RoomDatabase {

    private static TripsDatabase instance;

    public abstract ITrip iTrip();


    public static synchronized TripsDatabase getINSTANCE(Context context){
        if (instance == null){
            instance = Room.
                    databaseBuilder(context.getApplicationContext()
                            ,TripsDatabase.class, TRIP_DB)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;

    }
}