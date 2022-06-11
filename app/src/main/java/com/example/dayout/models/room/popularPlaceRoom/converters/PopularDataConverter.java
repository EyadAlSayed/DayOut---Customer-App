package com.example.dayout.models.room.popularPlaceRoom.converters;

import androidx.room.TypeConverter;

import com.example.dayout.models.popualrPlace.PlaceData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.List;
import java.lang.reflect.Type;

public class PopularDataConverter implements Serializable {

    @TypeConverter
    public String fromPopularPlaceDataToJson(List<PlaceData> placeData) {

        if (placeData == null)
            return null;

        Type type = new TypeToken<List<PlaceData>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.toJson(placeData, type);
    }


    @TypeConverter
    public List<PlaceData> fromJsonToPopularPlaceList(String popularObject) {


        if (popularObject == null)
            return null;

        Type type = new TypeToken<List<PlaceData>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(popularObject, type);

    }
}



