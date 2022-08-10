package com.example.dayout.models.room.tripsRoom.converters;

import androidx.room.TypeConverter;

import com.example.dayout.models.popualrPlace.PlaceData;
import com.example.dayout.models.trip.place.PlaceTripData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class PlaceTripDataListConverter implements Serializable {

    @TypeConverter
    public String fromPlace(ArrayList<PlaceTripData> placeTrip) {

        if (placeTrip == null)
            return null;

        Type type = new TypeToken<ArrayList<PlaceTripData>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.toJson(placeTrip, type);
    }


    @TypeConverter
    public ArrayList<PlaceTripData> toPlace(String data) {


        if (data == null)
            return null;

        Type type = new TypeToken<ArrayList<PlaceTripData>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(data, type);

    }
}
