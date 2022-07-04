package com.example.dayout.models.room.placeRoom.converters;

import androidx.room.TypeConverter;

import com.example.dayout.models.popualrPlace.PlaceData;
import com.example.dayout.models.popualrPlace.PopularPlacePhoto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class PlaceDataConverter implements Serializable {

    @TypeConverter
    public String fromPlace(List<PlaceData> placeData) {

        if (placeData == null)
            return null;

        Type type = new TypeToken<List<PlaceData>>() {}.getType();
        Gson gson = new Gson();

        return gson.toJson(placeData, type);
    }


    @TypeConverter
    public List<PlaceData> fromPlace(String photoObject) {


        if (photoObject == null)
            return null;

        Type type = new TypeToken<List<PlaceData>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(photoObject, type);

    }
}
