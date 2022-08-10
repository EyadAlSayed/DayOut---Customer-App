package com.example.dayout.models.room.tripsRoom.converters;

import androidx.room.TypeConverter;

import com.example.dayout.models.poll.PollsData;
import com.example.dayout.models.trip.TripData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class TripDataConverter implements Serializable {

    @TypeConverter
    public String fromTrip(List<TripData> tripData) {

        if (tripData == null)
            return null;

        Type type = new TypeToken<List<TripData>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.toJson(tripData, type);
    }


    @TypeConverter
    public List<TripData> toTrip(String data) {


        if (data == null)
            return null;

        Type type = new TypeToken<List<List<TripData>>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(data, type);

    }
}
