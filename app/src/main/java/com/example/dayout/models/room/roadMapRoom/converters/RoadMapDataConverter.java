package com.example.dayout.models.room.roadMapRoom.converters;

import androidx.room.TypeConverter;

import com.example.dayout.models.profile.ProfileData;
import com.example.dayout.models.trip.roadMap.RoadMapData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class RoadMapDataConverter implements Serializable {

    @TypeConverter
    public String fromRoadMap(RoadMapData roadMap) {

        if (roadMap == null)
            return null;

        Type type = new TypeToken<RoadMapData>() {
        }.getType();
        Gson gson = new Gson();

        return gson.toJson(roadMap, type);
    }


    @TypeConverter
    public RoadMapData toRoadMap(String data) {


        if (data == null)
            return null;

        Type type = new TypeToken<RoadMapData>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(data, type);

    }
}
