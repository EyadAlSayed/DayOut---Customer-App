package com.example.dayout.models.room.tripsRoom.converters;

import androidx.room.TypeConverter;

import com.example.dayout.models.trip.TripPhotoData;
import com.example.dayout.models.trip.tripType.TripType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class TripPhotoDataConverter implements Serializable {

    @TypeConverter
    public String fromPhoto(TripPhotoData tripPhoto) {

        if (tripPhoto == null)
            return null;

        Type type = new TypeToken<TripPhotoData>() {
        }.getType();
        Gson gson = new Gson();

        return gson.toJson(tripPhoto, type);
    }


    @TypeConverter
    public TripPhotoData toPhoto(String data) {


        if (data == null)
            return null;

        Type type = new TypeToken<List<TripPhotoData>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(data, type);

    }
}
