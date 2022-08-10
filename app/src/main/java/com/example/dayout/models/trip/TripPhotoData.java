package com.example.dayout.models.trip;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static com.example.dayout.config.AppConstants.TRIP_PHOTO_DATA;

@Entity(tableName = TRIP_PHOTO_DATA)
public class TripPhotoData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int trip_id;
    public String path;
}
