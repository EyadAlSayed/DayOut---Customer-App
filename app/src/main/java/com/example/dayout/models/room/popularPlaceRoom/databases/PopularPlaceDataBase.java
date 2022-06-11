package com.example.dayout.models.room.popularPlaceRoom.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dayout.models.popualrPlace.PlaceData;
import com.example.dayout.models.popualrPlace.PopularPlaceModel;
import com.example.dayout.models.popualrPlace.PopularPlacePhoto;
import com.example.dayout.models.room.popularPlaceRoom.Interfaces.IPopularPlaces;

import static com.example.dayout.config.AppConstants.POPULAR_PLACE_DB;


@Database(
        entities = {PopularPlaceModel.class, PlaceData.class, PopularPlacePhoto.class}
        ,version = 1)
abstract public class PopularPlaceDataBase extends RoomDatabase {


    private static PopularPlaceDataBase instance;

    public abstract IPopularPlaces iPopularPlaces();


    public static  synchronized PopularPlaceDataBase getINSTANCE(Context context){
        if (instance == null){
            instance = Room.
                    databaseBuilder(context.getApplicationContext()
                            ,PopularPlaceDataBase.class, POPULAR_PLACE_DB)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;

    }
}
