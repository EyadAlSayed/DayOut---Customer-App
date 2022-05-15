package com.example.dayout.models.room.popularPlaceRoom.converters;

import androidx.room.TypeConverter;

import com.example.dayout.models.popualrPlace.PopularPlaceData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.List;
import java.lang.reflect.Type;

public class PopularDataConverter implements Serializable {

    @TypeConverter
    public String fromPopularPlaceDataToJson(List<PopularPlaceData> popularPlaceData) {

        if (popularPlaceData == null)
            return null;

        Type type = new TypeToken<List<PopularPlaceData>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.toJson(popularPlaceData, type);
    }


    @TypeConverter
    public List<PopularPlaceData> fromJsonToPopularPlaceList(String popularObject) {


        if (popularObject == null)
            return null;

        Type type = new TypeToken<List<PopularPlaceData>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(popularObject, type);

    }
}



