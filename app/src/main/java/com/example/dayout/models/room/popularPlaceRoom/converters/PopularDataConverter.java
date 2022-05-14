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
    public String fromSpecList(List<PopularPlaceData> specializationList) {

        if (specializationList == null)
            return null;

        Type type = new TypeToken<List<PopularPlaceData>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.toJson(specializationList, type);
    }


    @TypeConverter
    public List<PopularPlaceData> toSpecList(String sspecList) {


        if (sspecList == null)
            return null;

        Type type = new TypeToken<List<PopularPlaceData>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(sspecList, type);

    }


    @TypeConverter
    public String fromSickList(List<String> sickList) {

        if (sickList == null)
            return null;

        Type type = new TypeToken<List<String>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.toJson(sickList, type);
    }


    @TypeConverter
    public List<String> toSickList(String ssickList) {


        if (ssickList == null)
            return null;

        Type type = new TypeToken<List<String>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(ssickList, type);

    }


    @TypeConverter
    public String fromDate(PopularPlaceData date) {
        return date.toString();
    }


}



