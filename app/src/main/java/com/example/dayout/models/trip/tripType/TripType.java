package com.example.dayout.models.trip.tripType;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.dayout.config.AppConstants.TRIP_TYPE;

@Entity(tableName = TRIP_TYPE)
public class TripType implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;

    public TripType(int id, String name) {
        this.id = id;
        this.name = name;
    }


}
