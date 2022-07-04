package com.example.dayout.models.room.placeRoom.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dayout.models.popualrPlace.PlaceData;
import com.example.dayout.models.popualrPlace.PopularPlacePhoto;
import com.example.dayout.models.room.placeRoom.interfaces.IPlace;
import com.example.dayout.models.trip.place.PlaceDetailsModel;

import static com.example.dayout.config.AppConstants.PLACES_DB;

@Database(entities = {PlaceDetailsModel.class, PlaceData.class, PopularPlacePhoto.class}, exportSchema = false, version = 1)
abstract public class PlaceDatabase extends RoomDatabase {

    private static PlaceDatabase instance;

    public abstract IPlace iPlace();


    public static synchronized PlaceDatabase getINSTANCE(Context context){
        if (instance == null){
            instance = Room.
                    databaseBuilder(context.getApplicationContext()
                            ,PlaceDatabase.class, PLACES_DB)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;

    }
}
