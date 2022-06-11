package com.example.dayout.models.trip.place;

import com.example.dayout.models.popualrPlace.PlaceData;

import java.io.Serializable;

public class PlaceTripData implements Serializable {

    public int id;
    public int place_id;
    public int trip_id;
    public int order;
    public String description;
    public boolean status;
    public PlaceData place = new PlaceData();

    public PlaceTripData(int place_id, String place_name, int order, String description) {
        this.place_id = place_id;
        this.order = order;
        this.place.name = place_name;
        this.description = description;
    }
}
