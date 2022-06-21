package com.example.dayout.models.trip.place;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.dayout.models.popualrPlace.PlaceData;

import java.io.Serializable;

import static com.example.dayout.config.AppConstants.PLACE_TRIP_DATA;

@Entity(tableName = PLACE_TRIP_DATA)
public class PlaceTripData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int place_id;
    public int trip_id;
    public int order;
    public String description;
    public int status;
    public PlaceData place = new PlaceData();

    public PlaceTripData(int place_id, String place_name, int order, String description) {
        this.place_id = place_id;
        this.order = order;
        this.place.name = place_name;
        this.description = description;
    }
}
