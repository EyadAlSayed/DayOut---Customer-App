package com.example.dayout.models.room.roadMapRoom.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dayout.models.room.roadMapRoom.interfaces.IRoadMap;
import com.example.dayout.models.trip.roadMap.RoadMapData;
import com.example.dayout.models.trip.roadMap.RoadMapModel;

import static com.example.dayout.config.AppConstants.ROAD_MAP_DB;

@Database(entities = {RoadMapModel.class, RoadMapData.class}, exportSchema = false, version = 1)
abstract public class RoadMapDatabase extends RoomDatabase {

    private static RoadMapDatabase instance;

    public abstract IRoadMap iRoadMap();

    public static synchronized RoadMapDatabase getINSTANCE(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), RoadMapDatabase.class, ROAD_MAP_DB)
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
