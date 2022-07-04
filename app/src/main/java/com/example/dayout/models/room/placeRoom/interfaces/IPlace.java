package com.example.dayout.models.room.placeRoom.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dayout.models.popualrPlace.PlaceData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

import static com.example.dayout.config.AppConstants.POPULAR_PLACE_DATA;

@Dao
public interface IPlace {

//    @Insert(onConflict =  OnConflictStrategy.REPLACE)
//    Completable insertPlace(PlaceData place);


    @Query("select * from " + POPULAR_PLACE_DATA + " where id = :placeId")
    Single<PlaceData> getPlace(int placeId);
}
