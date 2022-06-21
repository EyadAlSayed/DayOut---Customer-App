package com.example.dayout.models.room.tripsRoom.converters;

import androidx.room.TypeConverter;

import com.example.dayout.models.trip.tripType.TripType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.io.Serializable;
import java.util.List;

public class TripTypeConverter implements Serializable {

    @TypeConverter
    public String fromType(TripType tripType) {

        if (tripType == null)
            return null;

        Type type = new TypeToken<TripType>() {
        }.getType();
        Gson gson = new Gson();

        return gson.toJson(tripType, type);
    }


    @TypeConverter
    public TripType toType(String data) {


        if (data == null)
            return null;

        Type type = new TypeToken<List<TripType>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(data, type);

    }
}
