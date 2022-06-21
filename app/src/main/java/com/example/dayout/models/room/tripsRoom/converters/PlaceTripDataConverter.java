package com.example.dayout.models.room.tripsRoom.converters;

import androidx.room.TypeConverter;

import com.example.dayout.models.trip.place.PlaceTripData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class PlaceTripDataConverter implements Serializable {

    @TypeConverter
    public String fromPlace(PlaceTripData placeTrip) {

        if (placeTrip == null)
            return null;

        Type type = new TypeToken<PlaceTripData>() {
        }.getType();
        Gson gson = new Gson();

        return gson.toJson(placeTrip, type);
    }


    @TypeConverter
    public PlaceTripData toPlace(String data) {


        if (data == null)
            return null;

        Type type = new TypeToken<List<PlaceTripData>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(data, type);

    }
}
