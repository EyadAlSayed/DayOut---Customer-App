package com.example.dayout.models.room.tripsRoom.converters;

import androidx.room.TypeConverter;

import com.example.dayout.models.trip.CustomerTrip;
import com.example.dayout.models.trip.place.PlaceTripData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class CustomerTripConverter implements Serializable {

    @TypeConverter
    public String fromCustomer(CustomerTrip customerTrip) {

        if (customerTrip == null)
            return null;

        Type type = new TypeToken<CustomerTrip>() {
        }.getType();
        Gson gson = new Gson();

        return gson.toJson(customerTrip, type);
    }


    @TypeConverter
    public CustomerTrip toCustomer(String data) {


        if (data == null)
            return null;

        Type type = new TypeToken<List<CustomerTrip>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(data, type);

    }
}
