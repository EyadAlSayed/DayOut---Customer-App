package com.example.dayout.models.room.tripsRoom.converters;

import androidx.room.TypeConverter;

import com.example.dayout.models.trip.CustomerTrip;
import com.example.dayout.models.trip.place.PlaceTripData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CustomerTripConverter implements Serializable {

    @TypeConverter
    public String fromCustomer(ArrayList<CustomerTrip> customerTrip) {

        if (customerTrip == null)
            return null;

        Type type = new TypeToken<ArrayList<CustomerTrip>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.toJson(customerTrip, type);
    }


    @TypeConverter
    public ArrayList<CustomerTrip> toCustomer(String data) {


        if (data == null)
            return null;

        Type type = new TypeToken<ArrayList<CustomerTrip>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(data, type);

    }
}
