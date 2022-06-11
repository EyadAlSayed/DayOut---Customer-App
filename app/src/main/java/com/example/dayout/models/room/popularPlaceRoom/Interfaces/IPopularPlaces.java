package com.example.dayout.models.room.popularPlaceRoom.Interfaces;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dayout.models.popualrPlace.PlaceData;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

import static com.example.dayout.config.AppConstants.POPULAR_PLACE_DATA;


@Dao
public interface IPopularPlaces {


    @Insert
    Completable insertPopularPlace(PlaceData popularPlaceDB);


    @Query("select * from "+ POPULAR_PLACE_DATA)
    Single<List<PlaceData>>getPopularPlace();
}
