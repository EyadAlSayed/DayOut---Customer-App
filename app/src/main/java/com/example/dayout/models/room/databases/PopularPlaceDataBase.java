//package com.example.dayout.models.room.databases;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//import com.example.dayout.models.PopualrPlace.PopularPlace;
//
//
//@Database(entities = PopularPlace.class ,version = 1)
//abstract public class PopularPlaceDataBase extends RoomDatabase {
//
//
//    private static PopularPlaceDataBase instance;
//
//    public abstract IPopularPlaces iPopularPlaces();
//
//
//    public static  synchronized PopularPlaceDataBase getINSTANCE(Context context){
//        if (instance == null){
//            instance = Room.
//                    databaseBuilder(context.getApplicationContext()
//                            ,PopularPlaceDataBase.class,"popularplace_database")
//                    .fallbackToDestructiveMigration()
//                    .build();
//        }
//        return instance;
//
//    }
//}
