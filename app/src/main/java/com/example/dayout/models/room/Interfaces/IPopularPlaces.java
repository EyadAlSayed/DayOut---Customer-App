//package com.example.dayout.models.room.Interfaces;
//
//
//
//import androidx.room.Dao;
//import androidx.room.Insert;
//import androidx.room.Query;
//
//import com.example.dayout.models.PopualrPlace.PopularPlace;
//
//
//import java.util.List;
//
//import io.reactivex.Completable;
//import io.reactivex.Single;
//
//
//@Dao
//public interface IPopularPlaces {
//
//
//    @Insert
//    Completable insertPopularPlace(PopularPlace.Data popularPlaceDB);
//
//
//    @Query("select * from popularplace_table")
//    Single<List<PopularPlace.Data>> getPopularPlace();
//}
